package com.finance.winport.home.adapter;
/**
 * Created by liuworkmac on 17/5/15.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.model.Tag;
import com.finance.winport.util.UnitUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TagAdapter extends BaseAdapter {
    private Context mContext;
    private List<Tag> mData;

    public TagAdapter(Context mContext, List<Tag> mData) {
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
    public Tag getItem(int i) {
        Tag ret = null;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_tag, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Tag tag = mData.get(i);
        if (tag != null) {
            if (!TextUtils.isEmpty(tag.getColor())) {
                viewHolder.tvTag.setTextColor(Color.parseColor(tag.getColor()));
                viewHolder.tvTag.setBackgroundDrawable(getDrawable(tag.getColor()));

            }
            viewHolder.tvTag.setText(tag.getName());
        }

        return view;
    }

    private GradientDrawable getDrawable(String strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(UnitUtil.dip2px(mContext, 2));
        gradientDrawable.setStroke(1, Color.parseColor("#ffffff"));
        return gradientDrawable;
    }

    static class ViewHolder {
        @BindView(R.id.tv_tag)
        TextView tvTag;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}