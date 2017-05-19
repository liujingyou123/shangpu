package com.finance.winport.home.adapter;
/**
 * Created by liuworkmac on 17/5/19.
 */

import android.content.Context;
import android.util.TypedValue;
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

public class StationAdapter extends BaseAdapter {
    private Context mContext;
    private List<MetroResponse.Metro.Station> mData = new ArrayList<>();
    private int selectPosition = -1;

    public StationAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setSelectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }

    public void setSelectId(String id) {
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getStationId().equals(id)) {
                setSelectPosition(i);
                break;
            }
        }
    }

    public void initDataAndNotify(MetroResponse.Metro metro) {
        mData.clear();
        mData.addAll(QuyuDataManager.getInstance().getStations(metro));
        selectPosition = -1;
        notifyDataSetChanged();
    }

    public void initDatas(MetroResponse.Metro metro) {
        mData.clear();
        mData.addAll(QuyuDataManager.getInstance().getStations(metro));
    }

    public void initDatasWithReiionAndBlockId(String lineId, String stationId) {
        mData.clear();
        mData.addAll(QuyuDataManager.getInstance().getStationByMetroId(lineId));
        setSelectId(stationId);
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
    public MetroResponse.Metro.Station getItem(int i) {
        MetroResponse.Metro.Station ret = null;
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

        MetroResponse.Metro.Station station = mData.get(i);
        if (station != null) {

            String name = station.getStationName();
            if (name.length() > 6) {
                viewHolder.tvText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            } else {
                viewHolder.tvText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            }
            viewHolder.tvText.setText(name);
            if (selectPosition == i && !"-1".equals(station.getStationId())) {
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