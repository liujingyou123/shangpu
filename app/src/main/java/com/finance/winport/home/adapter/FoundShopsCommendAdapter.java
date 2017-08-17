package com.finance.winport.home.adapter;
/**
 * Created by jge on 17/5/3.
 */

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.model.FoundShopDetailResponse;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.image.Batman;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.tagview.TagCloudLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoundShopsCommendAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoundShopDetailResponse.DataBean.ShopListBean> mData;
//    private int viewHeight;

    public FoundShopsCommendAdapter(Context mContext, List<FoundShopDetailResponse.DataBean.ShopListBean> mData) {
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
//        int ret = 0;
//        if (mData != null) {
//            ret = mData.size();
//        }
        return mData.size();
    }

    @Override
    public ShopListResponse.DataBean.Shop getItem(int i) {
//        ShopListResponse.DataBean.Shop ret = null;
//        if (mData != null) {
//            ret = mData.get(i);
//        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_found_shop, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        FoundShopDetailResponse.DataBean.ShopListBean ret = mData.get(i);
        if (ret != null) {
            viewHolder.tvShopname.setText(ret.getTitle());
            viewHolder.tvAddress.setText(ret.getDistrictName() + " " + ret.getBlockName());
//            viewHolder.tvArea.setText(UnitUtil.formatDNum(ret.getArea()) + "㎡");
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
//            viewHolder.tvScanNum.setText(ret.getVisitCount() + "");
//            viewHolder.tvContactNum.setText(ret.getContactCount() + "");

            Batman.getInstance().fromNet(ret.getCoverImg(), viewHolder.imvCover);

            if (ret.getFeatureList() != null && ret.getFeatureList().size() > 0) {
                viewHolder.tgView.setAdapter(new TagAdapter(mContext, ret.getFeatureList()));
                viewHolder.llTag.setVisibility(View.VISIBLE);
            } else {
                viewHolder.llTag.setVisibility(View.GONE);
            }

            viewHolder.recommendContent.setText(ret.getRecommendWord());

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
        @BindView(R.id.recommendContent)
        TextView recommendContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}