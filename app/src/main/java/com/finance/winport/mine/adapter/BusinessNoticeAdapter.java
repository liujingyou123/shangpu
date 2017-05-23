package com.finance.winport.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.tab.adapter.PullBaseAdapter;
import com.finance.winport.tab.model.NotifyList;
import com.finance.winport.trade.TradeCircleDetailActivity;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jge on 17/5/5.
 * 服务通知
 */
public class BusinessNoticeAdapter extends PullBaseAdapter<NotifyList.DataBean.BussinessNoticeBean> {
    protected Context context;

    public BusinessNoticeAdapter(PtrClassicFrameLayout baseView, List<NotifyList.DataBean.BussinessNoticeBean> baseData, int maxTotal) {
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
        if (baseData.get(position).businessType == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final NotifyList.DataBean.BussinessNoticeBean item = baseData.get(position);
        if (getItemViewType(position) == 0) {// 评论通知
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.business_notice_list_item, viewGroup, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(item.digest);
            holder.time.setText(item.notifyTime);
            holder.post.setText(item.postName);
            holder.content.setText(item.contentOrReason);
            holder.doTime.setText(item.commentOrOprationTime);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, TradeCircleDetailActivity.class).putExtra("topicId", item.bussinessId + ""));
                }
            });
        } else if (getItemViewType(position) == 1) {//帖子被撤通知,评论被删通知
            ViewHolderOff holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.business_notice_list_item_off, viewGroup, false);
                holder = new ViewHolderOff(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderOff) convertView.getTag();
            }
            holder.title.setText(item.digest);
            holder.time.setText(item.notifyTime);
            holder.post.setText(item.postName);
            holder.reason.setText(item.contentOrReason);
            holder.doTime.setText(item.commentOrOprationTime);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        return convertView;
    }

    private void readMessage(int messageId, final int position) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("messId", messageId);
    }

    static class ViewHolderOff {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.post)
        TextView post;
        @BindView(R.id.reason)
        TextView reason;
        @BindView(R.id.do_time)
        TextView doTime;
        @BindView(R.id.contact)
        TextView contact;
        @BindView(R.id.details)
        RelativeLayout details;

        ViewHolderOff(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.post)
        TextView post;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.do_time)
        TextView doTime;
        @BindView(R.id.details)
        RelativeLayout details;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
