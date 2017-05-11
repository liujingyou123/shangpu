package com.finance.winport.trade.adapter;
/**
 * Created by liuworkmac on 17/5/11.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.finance.winport.R;

import java.util.List;

public class ChoicePhotoAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mData;

    public ChoicePhotoAdapter(Context mContext, List<String> mData) {
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
    public String getItem(int i) {
        String ret = null;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_choice_photo, null);
        } else {

        }
        return view;
    }
}