package com.finance.winport.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.finance.winport.R;
import com.finance.winport.mine.NoticeListActivity;
import com.finance.winport.tab.model.NotifyType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 16/8/26.
 */
public class NoticeCollectionAdapter extends BaseAdapter {
    private Context context;
    private List<NotifyType.DataBean.BaseNoticeDTOListBean> list;
    private int position = -1;

    public NoticeCollectionAdapter(Context context, List<NotifyType.DataBean.BaseNoticeDTOListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void update(List<NotifyType.DataBean.BaseNoticeDTOListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setCheckPosition(int position) {
        this.position = position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.notice_collection_item, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int mesType = list.get(position).notifyType;
        if (mesType == 0) {
            holder.type.setText("服务");
            holder.img.setImageResource(R.mipmap.notice_service);
        } else if (mesType == 1) {
            holder.type.setText("系统");
            holder.img.setImageResource(R.mipmap.notice_setting);
        } else if (mesType == 2) {
            holder.type.setText("生意圈");
            holder.img.setImageResource(R.mipmap.notice_trade);
        }
        holder.date.setText(list.get(position).creatTimeFormat);
        String c = list.get(position).digest;
        if (!TextUtils.isEmpty(c)) {
            holder.content.setText(c);
            holder.content.setVisibility(View.VISIBLE);
        } else {
            holder.content.setVisibility(View.GONE);
        }
        int status = list.get(position).status;
        if (status == 1) {//状态：0-已查看 1-未查看
            holder.indicator.setVisibility(View.VISIBLE);
        } else {
            holder.indicator.setVisibility(View.GONE);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, NoticeListActivity.class)
                        .putExtra("type", getMesType(position))
                        .putExtra("title", getTitle(position)));
            }
        });
        return convertView;
    }

    public int getMesType(int position) {
        if (list != null && list.size() > position) {
            return list.get(position).notifyType;
        }
        return -1;
    }

    public String getTitle(int position) {
        if (list != null && list.size() > position) {
            int mesType = list.get(position).notifyType;
            String title = "";
            switch (mesType) {
                case 0:
                    title = "服务";
                    break;
                case 1:
                    title = "系统通知";
                    break;
                case 2:
                    title = "生意圈";
                    break;
            }
            return title;
        } else {
            return "";
        }

    }

    class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.indicator)
        ImageView indicator;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
