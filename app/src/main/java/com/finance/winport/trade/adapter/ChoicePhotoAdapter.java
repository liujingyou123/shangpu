package com.finance.winport.trade.adapter;
/**
 * Created by liuworkmac on 17/5/11.
 */

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.finance.winport.R;
import com.finance.winport.image.Batman;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChoicePhotoAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mData;
    private String actionUrl = "action://add";
    private int MAX_NUM = 9;


    public ChoicePhotoAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
        init();
    }

    private void init() {
        if (mData != null && mData.size() == 0) {
            mData.add(actionUrl);
        }
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mData != null) {
            ret = mData.size();
        }
        return ret <= MAX_NUM ? ret : MAX_NUM;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_choice_photo, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String url = mData.get(i);
        if (!TextUtils.isEmpty(url)) {
            if (actionUrl.equals(url)) {
                viewHolder.imvDel.setVisibility(View.GONE);
                viewHolder.imvPhoto.setImageResource(R.mipmap.business_image_add);
            } else {
                viewHolder.imvDel.setVisibility(View.VISIBLE);
                Batman.getInstance().fromNet(url, viewHolder.imvPhoto);
                viewHolder.imvDel.setOnClickListener(new View.OnClickListener() {
                    int index = i;
                    @Override
                    public void onClick(View v) {
                        removeItems(index);
                    }
                });
            }
        }

        return view;
    }

    public void addItems(List<String> urls) {
        mData.addAll(0, urls);
        notifyDataSetChanged();
    }

    public boolean isAddType(int position) {
        boolean ret = false;
        String url = getItem(position);
        if (actionUrl.equals(url)) {
            ret = true;
        }
        return ret;
    }

    public int getRelCount() {
        return MAX_NUM - mData.size() + 1;
    }

    public void removeItems(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.imv_photo)
        ImageView imvPhoto;
        @BindView(R.id.imv_del)
        ImageView imvDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}