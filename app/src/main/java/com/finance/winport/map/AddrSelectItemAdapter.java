package com.finance.winport.map;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.baidu.mapapi.search.core.PoiInfo;
import com.finance.winport.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddrSelectItemAdapter extends RecyclerView.Adapter<AddrSelectItemAdapter.ViewHolder>{

    private List<PoiInfo> datas;

    private LayoutInflater layoutInflater;

    private OnItemClickListener listener;

    public AddrSelectItemAdapter(Context context, List<PoiInfo> datas, OnItemClickListener listener) {
        this.datas = datas;
        this.listener = listener;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.addr_select_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final PoiInfo info = getItem(position);
        if (info == null){
            return;
        }

        if (position == 0){
            holder.ivIcon.setVisibility(View.VISIBLE);
        }else{
            holder.ivIcon.setVisibility(View.GONE);
        }

        holder.addrName.setText("" + info.name);
        holder.addrLocation.setText("" + info.address);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position, info);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    private PoiInfo getItem(int pos){
        if (datas == null || datas.size() == 0){
            return null;
        }

        return datas.get(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.fl_root)
        View root;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.addr_name)
        TextView addrName;
        @BindView(R.id.addr_location)
        TextView addrLocation;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
