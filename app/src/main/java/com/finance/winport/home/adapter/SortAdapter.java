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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SortAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mData;
    private int selectPostion = -1;

    public SortAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void setSelectPostion(int position) {
        this.selectPostion = position;
        notifyDataSetChanged();
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
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_sort, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String tmp = mData.get(i);
        if (selectPostion == i && selectPostion != 0) {
            viewHolder.tvText.setSelected(true);
        } else {
            viewHolder.tvText.setSelected(false);
        }
        viewHolder.tvText.setText(tmp);
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