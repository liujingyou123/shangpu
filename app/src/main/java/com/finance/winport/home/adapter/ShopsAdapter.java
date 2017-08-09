package com.finance.winport.home.adapter;
/**
 * Created by liuworkmac on 17/5/3.
 */

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.image.Batman;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.tagview.TagCloudLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopsAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShopListResponse.DataBean.Shop> mData;
//    private int viewHeight;

    public ShopsAdapter(Context mContext, List<ShopListResponse.DataBean.Shop> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

//    @Override
//    public void notifyDataSetChanged() {
//        viewHeight = 0;
//        super.notifyDataSetChanged();
//    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mData != null) {
            ret = mData.size();
        }
        return ret;
    }

    @Override
    public ShopListResponse.DataBean.Shop getItem(int i) {
        ShopListResponse.DataBean.Shop ret = null;
        if (mData != null) {
            ret = mData.get(i);
        }
        return ret;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public int getItemType(int position) {
        int ret = 0;
        if (mData != null && mData.size() > position) {
            if (mData.get(position) == null && position == 0) {
                ret = 1;
            } else if (mData.size() < 8) {
                ShopListResponse.DataBean.Shop shop = mData.get(position);
                if (shop == null) {
                    ret = 3;
                } else {
                    ret = 2;
                }
            } else {
                ret = 2;
            }
        }

        return ret;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_shop, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }



        SpannableString builder = new SpannableString("找不到符合当前筛选条件的旺铺\n更改“位置”或“筛选”条件试试");
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor("#999999"));
        builder.setSpan(redSpan, builder.length() - 15, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new AbsoluteSizeSpan((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, mContext.getResources().getDisplayMetrics())), builder.length() - 15, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        viewHolder.tip.setText(builder);
        if (mData != null && i < mData.size()) {
            ShopListResponse.DataBean.Shop ret = mData.get(i);
            int type = getItemType(i);
            if (type == 1) {
                viewHolder.rlData.setVisibility(View.GONE);
                viewHolder.rlTianchong.setVisibility(View.GONE);
//                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UnitUtil.getScreenHeightPixels(mContext)-UnitUtil.getStatusBarHeight(mContext)-UnitUtil.dip2px(mContext, 89));
//                viewHolder.viewTrans.setLayoutParams(lp);
                viewHolder.viewTrans.setVisibility(View.VISIBLE);
            } else if (type == 3) {
                viewHolder.rlData.setVisibility(View.GONE);
                viewHolder.rlTianchong.setVisibility(View.VISIBLE);
                viewHolder.viewTrans.setVisibility(View.GONE);
            } else {
                if (ret != null) {
                    viewHolder.rlData.setVisibility(View.VISIBLE);
                    viewHolder.rlTianchong.setVisibility(View.GONE);
                    viewHolder.viewTrans.setVisibility(View.GONE);
                    viewHolder.tvShopname.setText(ret.getAddress() + ret.getRentTypeName());
                    viewHolder.tvAddress.setText(ret.getDistrictName() + " " + ret.getBlockName());
                    viewHolder.tvArea.setText(UnitUtil.formatDNum(ret.getArea()) + "㎡");
                    viewHolder.tvAverMoney.setText(UnitUtil.limitNum(ret.getRent(), 99999) + "/月");
                    if (TextUtils.isEmpty(ret.getDistance()) || "null".equals(ret.getDistance())) {
                        viewHolder.tvDistance.setText("");
                    } else {

                        viewHolder.tvDistance.setText("距您" + UnitUtil.formatSNum(ret.getDistance()) + "km");
                    }
                    viewHolder.tvChangeMoney.setTextColor(Color.parseColor("#999999"));
                    if (ret.getIsFace() == 1) {
                        viewHolder.tvChangeMoney.setText("转让费面议");
                    } else {
                        if (ret.getTransferFee() == 0) {
                            viewHolder.tvChangeMoney.setText("无转让费");
                        } else {
                            String strT = UnitUtil.formatNum(ret.getTransferFee());
                            viewHolder.tvChangeMoney.setText("转让费" + strT + "万元");
                            TextViewUtil.setPartialColor(viewHolder.tvChangeMoney, 3, 3 + strT.length(), Color.parseColor("#FF7540"));
                        }
                    }
                    viewHolder.tvUpdateTime.setText(ret.getUpdateTime());
                    viewHolder.tvScanNum.setText(ret.getVisitCount() + "");
                    viewHolder.tvContactNum.setText(ret.getContactCount() + "");

                    Batman.getInstance().fromNet(ret.getCoverImg(), viewHolder.imvCover);

                    if (ret.getFeatureList() != null && ret.getFeatureList().size() > 0) {
                        viewHolder.tgView.setAdapter(new TagAdapter(mContext, ret.getFeatureList()));
                        viewHolder.llTag.setVisibility(View.VISIBLE);
                    } else {
                        viewHolder.llTag.setVisibility(View.GONE);
                    }
                }

//                viewHeight += viewHolder.rlData.getMeasuredHeight();
//                XLog.e("viewHeight = " +viewHeight);
            }


        }


        return view;
    }



    static class ViewHolder {
        @BindView(R.id.imv_cover)
        ImageView imvCover;
        @BindView(R.id.tv_area)
        TextView tvArea;
        @BindView(R.id.tv_shopname)
        TextView tvShopname;
        @BindView(R.id.tv_aver_money)
        TextView tvAverMoney;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_distance)
        TextView tvDistance;
        @BindView(R.id.tv_change_money)
        TextView tvChangeMoney;
        @BindView(R.id.tv_update_time)
        TextView tvUpdateTime;
        @BindView(R.id.line_tv)
        View lineTv;
        @BindView(R.id.tg_view)
        TagCloudLayout tgView;
        @BindView(R.id.ll_tag)
        LinearLayout llTag;
        @BindView(R.id.tv_scan_num)
        TextView tvScanNum;
        @BindView(R.id.tv_contact_num)
        TextView tvContactNum;
        @BindView(R.id.rl_data)
        RelativeLayout rlData;
        @BindView(R.id.view_trans)
        View viewTrans;
        @BindView(R.id.rl_tianchong)
        RelativeLayout rlTianchong;
        @BindView(R.id.tip)
        TextView tip;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}