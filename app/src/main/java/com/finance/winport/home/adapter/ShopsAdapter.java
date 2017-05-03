package com.finance.winport.home.adapter;
/**
 * Created by liuworkmac on 17/5/3.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.finance.winport.R;
import com.finance.winport.home.model.Shop;

import java.util.List;

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
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_shop, null);
        } else {

        }
        return view;
    }
}