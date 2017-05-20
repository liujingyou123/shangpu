package com.finance.winport.home.adapter;
/**
 * Created by liuworkmac on 17/5/17.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.model.Tag;
import com.finance.winport.home.tools.SupportListUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupportTagAdapter extends BaseAdapter {
    private Context mContext;
    private List<Tag> mData;

    public SupportTagAdapter(Context mContext, List<Tag> mData) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_support_tag, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Tag tag = mData.get(i);
        if (tag != null) {
            viewHolder.tvSTag.setText(tag.getName());
            int resId = SupportListUtil.getResByName(tag.getName());
            if(resId != -1) {
                Drawable drawable = mContext.getResources().getDrawable(resId);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
                viewHolder.tvSTag.setCompoundDrawables(drawable, null, null, null);
            }

        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_s_tag)
        TextView tvSTag;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}