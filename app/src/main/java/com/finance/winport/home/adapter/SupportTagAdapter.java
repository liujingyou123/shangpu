package com.finance.winport.home.adapter;
/**
 * Created by liuworkmac on 17/5/17.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.model.Tag;
import com.finance.winport.home.tools.SupportListUtil;
import com.finance.winport.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupportTagAdapter extends BaseAdapter {
    private Context mContext;
    private List<Tag> mDataSelect = new ArrayList<>();
    private List<String> selectNames = new ArrayList<>();

    public SupportTagAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setSelectData(List<Tag> data) {
        if (data != null && data.size() > 0) {
            mDataSelect.clear();
            selectNames.clear();
            for (int i = 0; i < data.size(); i++) {
                mDataSelect.add(data.get(i));
                selectNames.add(data.get(i).getName());
            }
        }


    }

    @Override
    public int getCount() {
        return SupportListUtil.names.length;
    }

    @Override
    public Object getItem(int i) {
        return SupportListUtil.names[i];
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

        String tag = SupportListUtil.names[i];
        if (tag != null) {
            if ("三相电380V".equals(tag)) {
                tag = "三相电";
            }
            viewHolder.tvSTag.setText(tag);
            int resId = 0;
            if (mDataSelect != null && selectNames.contains(tag)) {
                resId = SupportListUtil.getResSelctByName(tag);
                viewHolder.tvSTag.setTextColor(Color.parseColor("#333333"));
            } else {
                resId = SupportListUtil.getResNormalByName(tag);
                viewHolder.tvSTag.setTextColor(Color.parseColor("#cccccc"));
            }

            if (resId != -1) {
                Drawable drawable = mContext.getResources().getDrawable(resId);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight() - 1);//必须设置图片大小，否则不显示
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