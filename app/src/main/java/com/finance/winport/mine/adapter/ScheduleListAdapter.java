package com.finance.winport.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.mine.model.ScheduleListResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jge on 17/5/5.
 */
public class ScheduleListAdapter extends BaseAdapter {
    protected Context context;
    private List<ScheduleListResponse.DataBean.ScheduleListBean> baseData;

    public ScheduleListAdapter(Context context, List<ScheduleListResponse.DataBean.ScheduleListBean> baseData) {
        this.baseData = baseData;
        this.context = context;
    }

    public ScheduleListAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return baseData.size();
    }

    @Override
    public Object getItem(int position) {
        return baseData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.schedule_list_item, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.time.setText(baseData.get(position).getOrderTime());
        if (baseData.get(position).getServiceType() == 0) {

            holder.type.setText("旺铺寻租");
        } else if (baseData.get(position).getServiceType() == 1) {

            holder.type.setText("带我踩盘");
        } else if (baseData.get(position).getServiceType() == 2) {

            holder.type.setText("签约租铺");
        }
        holder.address.setText(baseData.get(position).getDistrict() + baseData.get(position).getAddress());
        if (baseData.get(position).getStatus() == 2) {

            holder.status.setVisibility(View.VISIBLE);
            holder.time.setTextColor(Color.parseColor("#999999"));
            holder.type.setTextColor(Color.parseColor("#999999"));
        }
        else{
            holder.status.setVisibility(View.GONE);
            holder.time.setTextColor(Color.parseColor("#333333"));
            holder.type.setTextColor(Color.parseColor("#333333"));
        }
        return convertView;
    }




    static class ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.bottom_divider)
        View bottomDivider;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.address)
        TextView address;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
