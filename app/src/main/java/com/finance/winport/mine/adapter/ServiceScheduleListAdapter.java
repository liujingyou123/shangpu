package com.finance.winport.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.service.model.CalendarListResponse;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jge on 17/5/5.
 */
public class ServiceScheduleListAdapter extends BaseAdapter {
    protected Context context;
    private List<CalendarListResponse.DataBean.DateListBean.ScheduleListBean> baseData;

    public ServiceScheduleListAdapter(Context context, List<CalendarListResponse.DataBean.DateListBean.ScheduleListBean> baseData) {
        this.baseData = baseData;
        this.context = context;
    }

    public ServiceScheduleListAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return baseData.size();
    }

    @Override
    public CalendarListResponse.DataBean.DateListBean.ScheduleListBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.service_schedule_list_item, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.time.setText(baseData.get(position).getOrderTime().substring(11,16));
        if (baseData.get(position).getServiceType() == 0) {

            holder.type.setText("旺铺寻租");
        } else if (baseData.get(position).getServiceType() == 1) {

            holder.type.setText("预约看铺");
        } else if (baseData.get(position).getServiceType() == 2) {

            holder.type.setText("签约租铺");
        }
        holder.address.setText(baseData.get(position).getAddress());
        return convertView;
    }




    static class ViewHolder {
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.address)
        TextView address;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
