package com.finance.winport.mine.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.finance.winport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 16/8/26.
 */
public class NoticeCollectionAdapter extends BaseAdapter {
    private Context context;
//    private List<MessageTypeListResponse.DataBean> list;
    private int position = -1;

    public NoticeCollectionAdapter(Context context) {
        this.context = context;
//        this.list = list;
    }

    @Override
    public int getCount() {
//        return list == null ? 0 : list.size();
        return  3;
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
//        int mesType = list.get(position).getMesType();
//        if (mesType == 6) {
//            holder.type.setText("委托");
//            holder.img.setImageResource(R.mipmap.icon_notice_entrust);
//        } else if (mesType == 8) {
//            holder.type.setText("活动");
//            holder.img.setImageResource(R.mipmap.icon_notice_commission);
//        } else if (mesType == 4) {
//            holder.type.setText("系统通知");
//            holder.img.setImageResource(R.mipmap.icon_notice_system);
//        }
//        holder.date.setText(list.get(position).getCreateTime());
//        String c = list.get(position).getMesContent();
//        if (!TextUtils.isEmpty(c)) {
//            holder.content.setText(c);
//            holder.content.setVisibility(View.VISIBLE);
//        } else {
//            holder.content.setVisibility(View.GONE);
//        }
//        int num = list.get(position).getStatus();
////        int num = list.get(position).getUnReadNum();
//        if (num ==0) {
//            holder.indicator.setVisibility(View.VISIBLE);
//        } else {
//            holder.indicator.setVisibility(View.GONE);
//        }
        return convertView;
    }

//    public int getMesType(int position) {
//        if (list != null && list.size() > position) {
//            return list.get(position).getMesType();
//        }
//        return -1;
//    }
//
//    public String getTitle(int position) {
//        if (list != null && list.size() > position) {
//            int mesType = list.get(position).getMesType();
//            String title = "";
//            switch (mesType) {
//                case 6:
//                    title = "委托";
//                    break;
//                case 8:
//                    title = "活动";
//                    break;
//                case 4:
//                    title = "系统通知";
//                    break;
//            }
//            return title;
//        } else {
//            return "";
//        }
//
//    }

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
        TextView indicator;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
