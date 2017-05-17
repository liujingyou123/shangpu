package com.finance.winport.home.adapter;
/**
 * Created by liuworkmac on 17/5/3.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.image.Batman;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.tagview.TagCloudLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopsAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShopListResponse.DataBean.Shop> mData;

    public ShopsAdapter(Context mContext, List<ShopListResponse.DataBean.Shop> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

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

        ShopListResponse.DataBean.Shop ret = mData.get(i);
        if (ret != null) {
            viewHolder.tvShopname.setText(ret.getAddress()+ret.getRentTypeName());
            viewHolder.tvAddress.setText(ret.getDistrictName()+" "+ret.getBlockName());
            viewHolder.tvArea.setText(ret.getArea()+"㎡");
            viewHolder.tvAverMoney.setText(ret.getRent()+"元／月");
            viewHolder.tvDistance.setText(UnitUtil.mTokm(ret.getDistance()));
            viewHolder.tvChangeMoney.setText("转让费"+UnitUtil.formatNum(ret.getTransferFee()) + "万元");
            viewHolder.tvUpdateTime.setText(ret.getUpdateTime());
            viewHolder.tvScanNum.setText(ret.getVisitCount()+"");
            viewHolder.tvContactNum.setText(ret.getContactCount()+"");

            Batman.getInstance().fromNet(ret.getCoverImg(), viewHolder.imvCover);

            if (ret.getFeatureList() != null && ret.getFeatureList().size() > 0) {
                viewHolder.tgView.setAdapter(new TagAdapter(mContext, ret.getFeatureList()));
                viewHolder.llTag.setVisibility(View.VISIBLE);
            } else {
                viewHolder.llTag.setVisibility(View.GONE);
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}