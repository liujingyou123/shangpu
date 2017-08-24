package com.finance.winport.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.mine.MyScheduleListActivity;
import com.finance.winport.mine.ScheduleDetailActivity;
import com.finance.winport.tab.TypeList;
import com.finance.winport.tab.WinportActivity;
import com.finance.winport.tab.adapter.PullBaseAdapter;
import com.finance.winport.tab.model.NotifyList;
import com.finance.winport.util.Constant;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jge on 17/5/5.
 * 服务通知
 */
public class ServiceNoticeAdapter extends PullBaseAdapter<NotifyList.DataBean.ServiceNoticeBean> {
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
    public int getItemViewType(int position) {
        return baseData.get(position).serviceStatus;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        // 0-时间变动 1-服务撤销 2-服务完成 3-日程提醒 4-日程创建
        if (getItemViewType(position) == 0) {//0-时间变动
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
            //0-预约看铺 1-旺铺寻租 2-签约租铺
            holder.serviceType.setText(getType(item.serviceType));
            holder.address.setText(item.shopAddress);
            holder.oldSchedule.setText(item.oldSchedule);
            holder.newSchedule.setText(item.currentSchedule);
            holder.contact.setText(context.getString(R.string.service_contact, item.serviceName, item.serviceTel, Constant.SERVICE_PHONE));
            holder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ScheduleDetailActivity.class).putExtra("scheduleId", item.bussinessId + ""));
                }
            });
        } else if (getItemViewType(position) == 1) {//1-服务撤销（已去掉）
            ViewHolderOff holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.service_notice_list_item_off, viewGroup, false);
                holder = new ViewHolderOff(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderOff) convertView.getTag();
            }
            final NotifyList.DataBean.ServiceNoticeBean item = baseData.get(position);
            holder.title.setText(item.digest);
            holder.time.setText(item.notifyTime);
            //0-预约看铺 1-旺铺寻租 2-签约租铺
            holder.serviceType.setText(getType(item.serviceType));
            holder.address.setText(item.shopAddress);
            holder.schedule.setText(item.currentSchedule);
            holder.reason.setText(item.reason);
            holder.contact.setText(context.getString(R.string.service_contact, item.serviceName, item.serviceTel, Constant.SERVICE_PHONE));
            holder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ScheduleDetailActivity.class).putExtra("scheduleId", item.bussinessId + ""));
                }
            });

        } else if (getItemViewType(position) == 2) {//2-服务完成(1.2 已去掉)
            ViewHolderDone holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.service_notice_list_item_done, viewGroup, false);
                holder = new ViewHolderDone(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderDone) convertView.getTag();
            }
            final NotifyList.DataBean.ServiceNoticeBean item = baseData.get(position);
            holder.title.setText(item.digest);
            holder.time.setText(item.notifyTime);
            //0-预约看铺 1-旺铺寻租 2-签约租铺
            holder.serviceType.setText(getType(item.serviceType));
            holder.address.setText(item.shopAddress);
            holder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, WinportActivity.class).putExtra("type", TypeList.RELEASE));
                }
            });

        } else if (getItemViewType(position) == 3) {//3-日程提醒
            ViewHolderAlert holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.service_notice_list_item_alert, viewGroup, false);
                holder = new ViewHolderAlert(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderAlert) convertView.getTag();
            }
            final NotifyList.DataBean.ServiceNoticeBean item = baseData.get(position);
            holder.title.setText(item.digest);
            holder.time.setText(item.notifyTime);
            //0-预约看铺 1-旺铺寻租 2-签约租铺
            holder.serviceType.setText(getType(item.serviceType));
            holder.address.setText(item.shopAddress);
            holder.schedule.setText(item.currentSchedule);
            holder.more.setVisibility(item.scheduleCount > 0 ? View.VISIBLE : View.GONE);
            holder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, MyScheduleListActivity.class));
                }
            });
        } else if (getItemViewType(position) == 4) {//4-日程创建
            ViewHolderCreate holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.service_notice_list_item_create, viewGroup, false);
                holder = new ViewHolderCreate(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderCreate) convertView.getTag();
            }
            final NotifyList.DataBean.ServiceNoticeBean item = baseData.get(position);
            holder.title.setText(item.digest);
            holder.time.setText(item.notifyTime);
            //0-预约看铺 1-旺铺寻租 2-签约租铺
            holder.serviceType.setText(getType(item.serviceType));
            holder.address.setText(item.shopAddress);
            holder.schedule.setText(item.currentSchedule);
            if (!TextUtils.isEmpty(item.reason)) {
                holder.reason.setText(item.reason);
                holder.dropOff.setVisibility(View.VISIBLE);
            } else {
                holder.dropOff.setVisibility(View.GONE);
            }
            holder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, MyScheduleListActivity.class));
                }
            });
        }

        return convertView;
    }

    private String getType(String type) {
        String s = "";
        if (TextUtils.equals(type, "0")) {
            s = "预约看铺";
        } else if (TextUtils.equals(type, "1")) {
            s = "旺铺寻租";
        } else if (TextUtils.equals(type, "2")) {
            s = "签约租铺";
        }
        return s;
    }

    static class ViewHolderDone {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.service_type)
        TextView serviceType;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.contact)
        TextView contact;
        @BindView(R.id.details)
        RelativeLayout details;

        ViewHolderDone(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderAlert {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.service_type)
        TextView serviceType;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.schedule)
        TextView schedule;
        @BindView(R.id.more)
        TextView more;
        @BindView(R.id.details)
        RelativeLayout details;

        ViewHolderAlert(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderOff {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.service_type)
        TextView serviceType;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.schedule)
        TextView schedule;
        @BindView(R.id.reason)
        TextView reason;
        @BindView(R.id.contact)
        TextView contact;
        @BindView(R.id.details)
        RelativeLayout details;

        ViewHolderOff(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderCreate {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.service_type)
        TextView serviceType;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.schedule)
        TextView schedule;
        @BindView(R.id.reason)
        TextView reason;
        @BindView(R.id.drop_off)
        View dropOff;
        @BindView(R.id.contact)
        TextView contact;
        @BindView(R.id.details)
        RelativeLayout details;

        ViewHolderCreate(View view) {
            ButterKnife.bind(this, view);
        }
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
