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
import com.finance.winport.home.model.Shop;
import com.finance.winport.home.model.Tag;
import com.finance.winport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopsAdapter extends BaseAdapter {
    private Context mContext;
    private List<Shop> mData;

    public ShopsAdapter(Context mContext, List<Shop> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mData != null) {
            ret = mData.size();
        }
        return 100;
    }

    @Override
    public Shop getItem(int i) {
        Shop ret = null;
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
        viewHolder.tvShopname.setText(i + "");

        viewHolder.tgView.setAdapter(new TagAdapter(mContext, new ArrayList<Tag>(10)));
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}