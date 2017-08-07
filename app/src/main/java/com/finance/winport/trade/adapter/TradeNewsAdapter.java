package com.finance.winport.trade.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.image.Batman;
import com.finance.winport.trade.model.TradeHead;
import com.finance.winport.view.DrawableTopLeftTextView;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 2017/8/7.
 */

public class TradeNewsAdapter extends PullRecyclerBaseAdapter<TradeHead> {
    LayoutInflater inflater;

    public TradeNewsAdapter(PtrClassicFrameLayout baseView, List<TradeHead> baseData, int maxTotal) {
        super(baseView, baseData, maxTotal);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.trade_item_child_head, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        TradeHead item = baseData.get(position);
        if (item == null) return;
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
    }

    @Override
    public int getItemCount() {
        return getCount();
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

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
