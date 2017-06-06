package com.finance.winport.home.adapter;
/**
 * Created by liuworkmac on 17/5/19.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.model.MetroResponse;
import com.finance.winport.home.model.RegionResponse;
import com.finance.winport.home.tools.QuyuDataManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MetroAdapter extends BaseAdapter {
    private Context mContext;
    private List<MetroResponse.Metro> mData = new ArrayList<>();
    private int selectPosition = -1;

    public MetroAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void initData() {
        mData.addAll(QuyuDataManager.getInstance().getMetros());
        notifyDataSetChanged();
    }

    public void justInitData() {
        if (mData.size() == 0) {
            mData.addAll(QuyuDataManager.getInstance().getMetros());
        }
    }

    public void setSelectPostion(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }

    public void setSelectId(String id) {
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getLineId().equals(id)) {
                setSelectPostion(i);
                break;
            }
        }
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
    public MetroResponse.Metro getItem(int i) {
        MetroResponse.Metro ret = null;
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
        MetroResponse.Metro ret = mData.get(i);
        if (ret != null) {
            viewHolder.tvText.setText(ret.getLineName());
            if ((i == selectPosition) && !"-1".equals(ret.getLineId())) {
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