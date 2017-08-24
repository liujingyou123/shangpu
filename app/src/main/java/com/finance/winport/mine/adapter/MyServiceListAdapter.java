package com.finance.winport.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.mine.model.MyServiceListResponse;
import com.finance.winport.mine.model.ScheduleListResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jge on 17/5/5.
 */
public class MyServiceListAdapter extends BaseAdapter {
    protected Context context;
    private List<MyServiceListResponse.DataBeanX.DataBean> baseData;

    public MyServiceListAdapter(Context context, List<MyServiceListResponse.DataBeanX.DataBean> baseData) {
        this.baseData = baseData;
        this.context = context;
    }

    public MyServiceListAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return baseData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.myservice_list_item, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.time.setText(baseData.get(position).getCreateTime());
        if (baseData.get(position).getType() == 0) {

            holder.type.setText("旺铺寻租");
        } else if (baseData.get(position).getType() == 1) {

            holder.type.setText("预约看铺");
        } else if (baseData.get(position).getType() == 2) {

            holder.type.setText("签约租铺");
        }
        holder.address.setText(baseData.get(position).getAddress());
        if (baseData.get(position).getShopStatus() == 0) {

            holder.status.setText("服务受理中");
            holder.status.setTextColor(Color.parseColor("#2ea1f1"));
        }
        else if (baseData.get(position).getShopStatus() == 1) {

            holder.status.setText("服务已完成");
            holder.status.setTextColor(Color.parseColor("#00b886"));
        }
        else if (baseData.get(position).getShopStatus() == 2) {

            holder.status.setText("服务已撤销");
            holder.status.setTextColor(Color.parseColor("#b3bac3"));
        }
        else if (baseData.get(position).getShopStatus() == 3) {

            holder.status.setText("旺铺已发布");
            holder.status.setTextColor(Color.parseColor("#00b886"));
        }
        else if (baseData.get(position).getShopStatus() == 4) {

            holder.status.setText("店铺被废弃");
            holder.status.setTextColor(Color.parseColor("#b3bac3"));
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
