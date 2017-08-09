package com.finance.winport.trade.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.image.Batman;
import com.finance.winport.trade.InfoDetailsActivity;
import com.finance.winport.trade.TradeType;
import com.finance.winport.trade.model.TradeBible;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 2017/8/7.
 */

public class BibleListAdapter extends PullRecyclerBaseAdapter<TradeBible> {
    LayoutInflater inflater;

    public BibleListAdapter(PtrClassicFrameLayout baseView, List<TradeBible> baseData, int maxTotal) {
        super(baseView, baseData, maxTotal);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.trade_item_child_bible, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        TradeBible item = baseData.get(position);
        if (item == null) return;
        holder.desc.setText(item.title);
        holder.tip.setText(item.content);
        holder.date.setText(item.dateTime);
        holder.scanCount.setText(item.viewCount + "浏览");
        Batman.getInstance().fromNet(item.image, holder.img);
        if (position > 0) {
            holder.itemView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_top_divider_inset_left));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, InfoDetailsActivity.class)
                        .putExtra("type", TradeType.BIBLE_LIST));
            }
        });
    }

    @Override
    public int getItemCount() {
        return getCount();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.content)
        TextView desc;
        @BindView(R.id.tip)
        TextView tip;
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
