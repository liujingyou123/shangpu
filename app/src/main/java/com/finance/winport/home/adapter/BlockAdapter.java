package com.finance.winport.home.adapter;
/**
 * Created by liuworkmac on 17/5/4.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.model.RegionResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlockAdapter extends BaseAdapter {
    private Context mContext;
    private List<RegionResponse.Region.Block> mData;

    public BlockAdapter(Context mContext, List<RegionResponse.Region.Block> mData) {
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
    public RegionResponse.Region.Block getItem(int i) {
        RegionResponse.Region.Block ret = null;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_location, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        RegionResponse.Region.Block block = mData.get(i);
        if (block != null) {
            viewHolder.tvText.setText(block.getBlockName());
            if (block.isChecked()) {
                viewHolder.tvText.setSelected(true);
            } else {
                viewHolder.tvText.setSelected(false);
            }
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_text)
        TextView tvText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}