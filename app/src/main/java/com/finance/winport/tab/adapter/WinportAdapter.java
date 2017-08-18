package com.finance.winport.tab.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.dialog.NoticeDialog;
import com.finance.winport.dialog.OffShelfDialog;
import com.finance.winport.home.ShopDetailActivity;
import com.finance.winport.image.Batman;
import com.finance.winport.image.BatmanCallBack;
import com.finance.winport.tab.TypeList;
import com.finance.winport.tab.WinportActivity;
import com.finance.winport.tab.model.Lunar;
import com.finance.winport.tab.model.NameValue;
import com.finance.winport.tab.model.WinportList;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.roundview.RoundedImageView;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 2017/5/12.
 */

public class WinportAdapter extends PullBaseAdapter<WinportList.DataBeanX.DataBean> {

    public WinportAdapter(PtrClassicFrameLayout baseView, List<WinportList.DataBeanX.DataBean> baseData, int maxTotal) {
        super(baseView, baseData, maxTotal);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_winport_release_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final WinportList.DataBeanX.DataBean item = baseData.get(position);
        holder.name.setText(item.clerkName);
        setHeadImage(item.headPortrait, holder.img);
        holder.address.setText(item.address + item.rentTypeName);
        holder.scanCount.setText("周浏览" + item.scanCount + "人/次");
        holder.area.setText(UnitUtil.formatArea(item.area) + "㎡");
        holder.releaseTime.setText(item.publishTime);
        //rentStatus 出租状态 0-待出租 1-出租中 2-暂不出租 3-已出租
        if (item.rentStatus == 2 || item.rentStatus == 3) {
            holder.historyCount.setText("历史看铺申请" + item.visitCount + "组");
            setViewAndChildrenEnabled(convertView, false);
            holder.llOnSale.setVisibility(View.GONE);
            holder.release.setEnabled(true);
            holder.release.setVisibility(View.VISIBLE);
            holder.flMark.setVisibility(View.VISIBLE);
            holder.mark.setText(item.rentStatus == 2 ? "该铺暂不出租" : "该铺已出租");
        } else {
            holder.flMark.setVisibility(View.GONE);
            holder.llOnSale.setVisibility(View.VISIBLE);
            holder.release.setVisibility(View.GONE);
            setViewAndChildrenEnabled(convertView, true);
            String vcs = "历史看铺申请" + item.visitCount + "组";
            SpannableString sp = new SpannableString(vcs);
            sp.setSpan(new ForegroundColorSpan(Color.parseColor("#333333"))
                    , vcs.indexOf(item.visitCount)
                    , vcs.indexOf(item.visitCount) + item.visitCount.length()
                    , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.historyCount.setText(sp);

        }
        Batman.getInstance().fromNet(item.coverImg, holder.img);

        //下架
        holder.dropOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "myshop_unpublish");
                showOffShelfAlert(item);

            }
        });
        //联系小二
        holder.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "myshop_callwaiter");
                showContactAlert(item.clerkPhone);
            }
        });

        //重新发布
        holder.release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "myshop_callwaiter");
                showContactAlert(item.clerkPhone);
            }
        });
        //
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "myshop_shop");
                Intent details = new Intent(context, ShopDetailActivity.class);
                details.putExtra("shopId", item.id);
                context.startActivity(details);
            }
        });
        return convertView;
    }

    private void setHeadImage(String headPortrait, final ImageView img) {
        Batman.getInstance().fromNet(headPortrait, new BatmanCallBack() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                img.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(Exception error) {

            }
        });
    }

    String id = "";

    private void showOffShelfAlert(final WinportList.DataBeanX.DataBean item) {
        OffShelfDialog off = new OffShelfDialog(context);
        off.setOnItemClickListener(new OffShelfDialog.OnItemClickListener() {
            @Override
            public void onItemClick(NameValue item, int position) {
                id = item.value;
            }
        }).setOnOkClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offShelfSHop(item, id);
            }
        }).show();
    }

    LoadingDialog loading = new LoadingDialog(context);

    //下架商铺
    private void offShelfSHop(final WinportList.DataBeanX.DataBean item, final String type) {
        loading.show();
        HashMap<String, Object> params = new HashMap<>();
        params.put("shopId", item.id);
        params.put("type", type);
        PersonManager.getInstance().offShelfSHop(params, new NetworkCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse response) {
                item.rentStatus = TextUtils.equals(type, "1") ? 3 : 2;
                notifyDataSetChanged();
                loading.dismiss();
                ToastUtil.show(context, "旺铺已撤下");
            }

            @Override
            public void failure(Throwable throwable) {
                loading.dismiss();
            }
        });
    }

    private static void setViewAndChildrenEnabled(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                setViewAndChildrenEnabled(child, enabled);
            }
        }
    }


    static class ViewHolder {
        @BindView(R.id.headImg)
        RoundedImageView headImg;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.sign)
        TextView sign;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.history_count)
        TextView historyCount;
        @BindView(R.id.scan_count)
        TextView scanCount;
        @BindView(R.id.release_time)
        TextView releaseTime;
        @BindView(R.id.area)
        TextView area;
        @BindView(R.id.contact)
        TextView contact;
        @BindView(R.id.drop_off)
        TextView dropOff;
        @BindView(R.id.release)
        TextView release;
        @BindView(R.id.ll_onSale)
        View llOnSale;
        @BindView(R.id.mark)
        TextView mark;
        @BindView(R.id.fl_mark)
        View flMark;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    void showContactAlert(final String phone) {
        NoticeDialog n = new NoticeDialog(context);
        n.setMessage("直拨小二电话：" + phone);
        n.setPositiveBtn("确认");
        n.setOkClickListener(new NoticeDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                context.startActivity(new Intent().setAction(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + phone)));
            }
        });
        n.show();
    }
}
