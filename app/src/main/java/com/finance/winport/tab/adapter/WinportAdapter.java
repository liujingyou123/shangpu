package com.finance.winport.tab.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.tab.model.WinportModel;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 2017/5/12.
 */

public class WinportAdapter extends PullBaseAdapter<WinportModel> {

    public WinportAdapter(PtrClassicFrameLayout baseView, List<WinportModel> baseData, int maxTotal) {
        super(baseView, baseData, maxTotal);
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_winport_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.history_count)
        TextView historyCount;
        @BindView(R.id.scan_count)
        TextView scanCount;
        @BindView(R.id.release_time)
        TextView releaseTime;
        @BindView(R.id.area)
        TextView area;
        @BindView(R.id.contact)
        TextView contact;
        @BindView(R.id.drop_off)
        TextView dropOff;
        @BindView(R.id.release)
        TextView release;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
