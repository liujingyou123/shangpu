package com.finance.winport.mine.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.dialog.NoticeDialog;
import com.finance.winport.tab.adapter.PullBaseAdapter;
import com.finance.winport.tab.model.NotifyList;
import com.finance.winport.trade.TradeCircleDetailActivity;
import com.finance.winport.util.Constant;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jge on 17/5/5.
 * 生意圈通知
 */
public class BusinessNoticeAdapter extends PullBaseAdapter<NotifyList.DataBean.BussinessNoticeBean> {
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
        if (TextUtils.equals(baseData.get(position).bussinessType, "0")) {
            return 0;
        } else if (TextUtils.equals(baseData.get(position).bussinessType, "3")) {
            return 1;
        }
        return 2;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final NotifyList.DataBean.BussinessNoticeBean item = baseData.get(position);
        // 0：评论通知1：帖子被撤通知2：评论被删通知 3-评论被评论
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
            holder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, TradeCircleDetailActivity.class).putExtra("topicId", item.bussinessId + ""));
                }
            });
        } else if (getItemViewType(position) == 1) {// 评论被评论
            ViewHolderReply holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.business_notice_list_reply_comment_item, viewGroup, false);
                holder = new ViewHolderReply(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderReply) convertView.getTag();
            }
            holder.title.setText(item.digest);
            holder.time.setText(item.notifyTime);
            holder.post.setText(item.postName);
            holder.content.setText(item.contentOrReason);
            holder.doTime.setText(item.commentOrOprationTime);
            holder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, TradeCircleDetailActivity.class).putExtra("topicId", item.bussinessId + ""));
                }
            });
        } else if (getItemViewType(position) == 2) {//帖子被撤通知,评论被删通知
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
            holder.contact.setText(context.getString(R.string.business_contact, Constant.SERVICE_PHONE));
            if (TextUtils.isEmpty(item.contentOrReason)) {
                holder.RlReason.setVisibility(View.GONE);
            } else {
                holder.RlReason.setVisibility(View.VISIBLE);
                holder.reason.setText(item.contentOrReason);
            }
            holder.doTime.setText(item.commentOrOprationTime);
            holder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.equals(item.bussinessType, "1")) {//帖子被撤
                        showAlert("您的帖子已经被删除，无法查看");
                    } else if (TextUtils.equals(item.bussinessType, "2")) {//评论被删
                        showAlert("您的评论已经被删除，无法查看");
//                        context.startActivity(new Intent(context, TradeCircleDetailActivity.class)
//                                .putExtra("topicId", item.bussinessId + ""));
                    }
                }
            });
        }

        return convertView;
    }

    private void showAlert(String info) {
        NoticeDialog alert = new NoticeDialog(context);
        alert.setOneButton();
        alert.setMessage(info);
        alert.show();
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
        @BindView(R.id.rl_reason)
        RelativeLayout RlReason;

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

    static class ViewHolderReply {
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

        ViewHolderReply(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
