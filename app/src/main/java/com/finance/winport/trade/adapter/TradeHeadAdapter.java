package com.finance.winport.trade.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.image.Batman;
import com.finance.winport.trade.InfoDetailsActivity;
import com.finance.winport.trade.InfoListActivity;
import com.finance.winport.trade.TradeType;
import com.finance.winport.trade.model.TradeSub;
import com.finance.winport.trade.model.TradeTag;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.DrawableTopLeftTextView;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 2017/8/7.
 * 行业头条 列表
 */

public class TradeHeadAdapter extends PullRecyclerBaseAdapter<TradeSub> {
    LayoutInflater inflater;
    List<TradeTag.Tag> headerInfo;

    public TradeHeadAdapter(PtrClassicFrameLayout baseView, List<TradeSub> baseData, int maxTotal) {
        super(baseView, baseData, maxTotal);
        inflater = LayoutInflater.from(context);
    }

    public void updateHeader(List<TradeTag.Tag> headerInfo) {
        this.headerInfo = headerInfo;
        if (headerInfo != null && headerInfo.size() > 0) {
            notifyItemChanged(0);
        } else {
            notifyDataChanged();
        }
    }

    private void setHeaderInfo(HeaderViewHolder holder, List<TradeTag.Tag> headerInfo) {
        if (headerInfo != null && headerInfo.size() > 0) {
            RecyclerView.Adapter adapter = holder.header.getAdapter();
            if (adapter == null) {
                holder.header.setAdapter(new HeaderAdapter(context, headerInfo));
            } else {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == 0) {
            View v = inflater.inflate(R.layout.head_header_layout, parent, false);
            holder = new HeaderViewHolder(v);
            ((HeaderViewHolder) holder).header.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        } else {
            View v = inflater.inflate(R.layout.trade_item_child_head, parent, false);
            holder = new ViewHolder(v);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof HeaderViewHolder) {
            setHeaderInfo((HeaderViewHolder) viewHolder, headerInfo);
        } else {
            if (baseData == null) return;
            TradeSub item = (TradeSub) getItem(position);
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.title.setText(item.title);
            holder.type.setText(item.content);
            holder.from.setText(item.source);
            holder.date.setText(item.dateTime);
            holder.scanCount.setText(item.viewCount + "浏览");
            if (item.kind) {
                holder.title.setDrawable(R.mipmap.label_top);
            } else {
                holder.title.setDrawable(0);
            }
            Batman.getInstance().fromNet(item.image, holder.img);
            if (position > 1) {
                holder.itemView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_top_divider_inset_left));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, InfoDetailsActivity.class)
                            .putExtra("type", TradeType.HEAD_DETAILS));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (headerInfo == null || headerInfo.size() == 0) {
            return baseData == null ? 0 : baseData.size();
        }
        return baseData == null ? 1 : baseData.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (headerInfo == null || headerInfo.size() == 0) {
            return super.getItem(position);
        }
        return super.getItem(position - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (headerInfo == null || headerInfo.size() == 0) {
            return 1;
        } else {
            return position == 0 ? 0 : 1;
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.header)
        RecyclerView header;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        DrawableTopLeftTextView title;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.from)
        TextView from;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.scan_count)
        TextView scanCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.ViewHolder> {
        Context context;
        LayoutInflater inflater;
        List<TradeTag.Tag> headerInfo;

        public HeaderAdapter(Context context, List<TradeTag.Tag> headerInfo) {
            this.context = context;
            this.headerInfo = headerInfo;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public HeaderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.head_header_item_layout, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final TradeTag.Tag item = headerInfo.get(position);
//            holder.name.setText(item.tagName);
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            if (position > 0) {
                lp.leftMargin = UnitUtil.dip2px(context, 7.5f);
            } else {
                lp.leftMargin = UnitUtil.dip2px(context, 0);
            }
            holder.itemView.requestLayout();
//            Batman.getInstance().fromNet(item.tagIcon, holder.img);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, InfoListActivity.class)
                            .putExtra("id", item.tagId).putExtra("title", item.tagName)
                            .putExtra("type", TradeType.HEAD_LIST));
                }
            });
        }

        @Override
        public int getItemCount() {
            return headerInfo == null ? 0 : headerInfo.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.img)
            ImageView img;
            @BindView(R.id.name)
            TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

}
