package com.finance.winport.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.mine.ScheduleDetailActivity;
import com.finance.winport.tab.adapter.PullBaseAdapter;
import com.finance.winport.tab.model.NotifyList;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jge on 17/5/5.
 * 生意圈通知
 */
public class ServiceNoticeAdapter extends PullBaseAdapter<NotifyList.DataBean.ServiceNoticeBean> {
    protected Context context;

    public ServiceNoticeAdapter(PtrClassicFrameLayout baseView, List<NotifyList.DataBean.ServiceNoticeBean> baseData, int maxTotal) {
        super(baseView, baseData, maxTotal);
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.service_notice_list_item, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final NotifyList.DataBean.ServiceNoticeBean item = baseData.get(position);
        holder.title.setText(item.digest);
        holder.time.setText(item.notifyTime);
        //0：旺铺寻租1：签约租铺2：预约看铺
        holder.serviceType.setText(getType(item.serviceType));
        holder.address.setText(item.shopAddress);
        holder.oldSchedule.setText(item.oldSchedule);
        holder.newSchedule.setText(item.currentSchedule);
        holder.contact.setText(context.getString(R.string.service_contact, item.serviceName, item.serviceTel));
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ScheduleDetailActivity.class).putExtra("scheduleId", item.bussinessId));
            }
        });
        return convertView;
    }

    private String getType(int type) {
        return type == 0 ? "旺铺寻租" : type == 2 ? "签约租铺" : "预约看铺";
    }

    private void readMessage(int messageId, final int position) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("messId", messageId);
    }


    static class ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.service_type)
        TextView serviceType;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.old_schedule)
        TextView oldSchedule;
        @BindView(R.id.new_schedule)
        TextView newSchedule;
        @BindView(R.id.contact)
        TextView contact;
        @BindView(R.id.details)
        RelativeLayout details;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
