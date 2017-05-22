package com.finance.winport.tab.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.home.OrderShopActivity;
import com.finance.winport.home.ShopDetailActivity;
import com.finance.winport.image.Batman;
import com.finance.winport.tab.event.RefreshEvent;
import com.finance.winport.tab.model.AppointShopList;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 2017/5/12.
 * 约看
 */

public class AppointWinportAdapter extends PullBaseAdapter<AppointShopList.DataBeanX.DataBean> {
    LoadingDialog loading;

    public AppointWinportAdapter(PtrClassicFrameLayout baseView, List<AppointShopList.DataBeanX.DataBean> baseData, int maxTotal) {
        super(baseView, baseData, maxTotal);
        loading = new LoadingDialog(context);
    }

    private void removeAndUpdate(int position) {
        baseData.remove(position);
        notifyDataSetChanged();
//        EventBus.getDefault().post(new RefreshEvent());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_winport_appoint_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AppointShopList.DataBeanX.DataBean item = baseData.get(position);
        holder.address.setText(item.address + item.rentTypeName);
        holder.district.setText(item.districtName + " " + item.blockName);
        holder.area.setText(UnitUtil.formatArea(item.area) + "㎡");
        String sRent = Math.round(item.rent) + "";
        String sFee = Math.round(item.transferFee / 10000) + "";
        boolean hasFee = item.transferFee > 0;
        //rentStatus 出租状态 0-待出租 1-出租中 2-已出租  3-已下架（撤下）
        if (item.rentStatus == 3) {
            holder.price.setText(sRent + "元/月");
            if (item.isFace == 0) {// 面议
                holder.fee.setText("面议");
            } else {
                if (hasFee) {
                    holder.fee.setText("转让费" + sFee + "万元");
                } else {
                    holder.fee.setText("无转让费");
                }
            }
            setViewAndChildrenEnabled(convertView, false);
        } else {
            setViewAndChildrenEnabled(convertView, true);
            SpannableString sr = new SpannableString(sRent + "元");
            sr.setSpan(new ForegroundColorSpan(Color.parseColor("#FF7540"))
                    , 0, sr.length()
                    , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.price.setText(sr);
            if (item.isFace == 0) {// 面议
                holder.fee.setText("面议");
            } else {
                if (hasFee) {
                    SpannableString sp = new SpannableString("转让费" + sFee + "万元");
                    sp.setSpan(new ForegroundColorSpan(Color.parseColor("#FF7540"))
                            , sp.toString().indexOf(sFee), sp.toString().indexOf(sFee) + sFee.length()
                            , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    holder.fee.setText(sp);
                } else {
                    holder.fee.setText("无转让费");
                }
            }
        }
        holder.distance.setText("距您" + UnitUtil.mTokm(item.distance + ""));
        holder.updateTime.setText(item.updateTime + "更新");
        holder.appointTime.setText(item.applyTime + "约看");
        holder.scan.setText(item.visitCount + "");
        holder.call.setText(item.contactCount + "");
        Batman.getInstance().fromNet(item.coverImg, holder.img);
        setTag(holder, item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(context, ShopDetailActivity.class);
                details.putExtra("shopId", item.id);
                context.startActivity(details);
            }
        });
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDeleteAlert(item.visitId + "", position);
                return true;
            }
        });
        holder.sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderIntent = new Intent(context, OrderShopActivity.class);
                orderIntent.putExtra("shopId", item.id);
                orderIntent.putExtra("type", 1);
                context.startActivity(orderIntent);
            }
        });
        return convertView;
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


    private void setTag(ViewHolder holder, AppointShopList.DataBeanX.DataBean item) {
        holder.tag.removeAllViews();
        if (item.featureList != null && item.featureList.size() > 0) {
            int count = 0;
            for (int i = 0; i < item.featureList.size(); i++) {
                if (count >= 3) break;
                AppointShopList.DataBeanX.DataBean.FeatureListBean tag = item.featureList.get(i);
                String name = tag.name;
                String color = tag.color;
                if (!TextUtils.isEmpty(name)) {
                    TextView tv = createItem(name, color);
                    holder.tag.addView(tv);
                    count++;
                } else {
                    continue;
                }
            }
            if (count > 0) {
                holder.tag.setVisibility(View.VISIBLE);
                holder.tagDivider.setVisibility(View.VISIBLE);
            } else {
                holder.tag.setVisibility(View.GONE);
                holder.tagDivider.setVisibility(View.INVISIBLE);
            }
        }
    }

    private TextView createItem(String name, String color) {
        TextView tv = new TextView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, UnitUtil.dip2px(context, 14));
        lp.rightMargin = UnitUtil.dip2px(context, 5);
        tv.setLayoutParams(lp);
        tv.setMinWidth(UnitUtil.dip2px(context, 40));
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        tv.setPadding(UnitUtil.dip2px(context, 2), 0, UnitUtil.dip2px(context, 2), 0);
        tv.setText(name);
        if (!TextUtils.isEmpty(color)) {
            tv.setTextColor(Color.parseColor(color));
            tv.setBackgroundDrawable(getDrawable(UnitUtil.dip2px(context, 2), 0, UnitUtil.dip2px(context, 0.5f), Color.parseColor(color)));
        } else {
            tv.setBackgroundDrawable(getDrawable(UnitUtil.dip2px(context, 2), 0, UnitUtil.dip2px(context, 0.5f), Color.parseColor("#e5e5e5")));
        }
        return tv;
    }

    private GradientDrawable getDrawable(int radius, int fillColor, int strokeWidth, int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(radius);
        gradientDrawable.setColor(fillColor);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        return gradientDrawable;
    }

    void showDeleteAlert(final String visitId, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        SpannableString pButton = new SpannableString("确认");
        SpannableString nButton = new SpannableString("取消");
        pButton.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA73B")), 0, pButton.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        nButton.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA73B")), 0, nButton.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AlertDialog dialog = builder.setTitle("提示").setMessage("删除约看")
                .setPositiveButton(pButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAppoint(visitId, position);
                    }
                }).setNegativeButton(nButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        dialog.show();
    }

    private void deleteAppoint(String visitId, final int position) {
        loading.show();
        HashMap<String, Object> params = new HashMap<>();
        params.put("visitId", visitId);
        PersonManager.getInstance().deleteAppoint(params, new NetworkCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse response) {
                loading.dismiss();
                if (response != null && response.isSuccess()) {
                    removeAndUpdate(position);
                    ToastUtil.show(context, "已删除");
                } else {
                    ToastUtil.show(context, response == null ? "null response" : response.errMsg);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                loading.dismiss();
                ToastUtil.show(context, throwable.getMessage());
            }
        });
    }


    static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.mark)
        ImageView mark;
        @BindView(R.id.scan)
        TextView scan;
        @BindView(R.id.call)
        TextView call;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.district)
        TextView district;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.distance)
        TextView distance;
        @BindView(R.id.fee)
        TextView fee;
        @BindView(R.id.update_time)
        TextView updateTime;
        @BindView(R.id.area)
        TextView area;
        @BindView(R.id.tag_divider)
        View tagDivider;
        @BindView(R.id.tag)
        LinearLayout tag;
        @BindView(R.id.appoint_time)
        TextView appointTime;
        @BindView(R.id.sign)
        TextView sign;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
