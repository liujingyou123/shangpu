package com.finance.winport.tab.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class ScanWinportAdapter extends PullBaseAdapter<WinportModel> {

    public ScanWinportAdapter(PtrClassicFrameLayout baseView, List<WinportModel> baseData, int maxTotal) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_winport_scan_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                 showCancelCollectionAlert();
                return true;
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.mark)
        ImageView mark;
        @BindView(R.id.scan)
        TextView scan;
        @BindView(R.id.call)
        TextView call;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.district)
        TextView district;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.distance)
        TextView distance;
        @BindView(R.id.fee)
        TextView fee;
        @BindView(R.id.release_time)
        TextView releaseTime;
        @BindView(R.id.area)
        TextView area;
        @BindView(R.id.tag)
        LinearLayout tag;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    void showCancelCollectionAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        SpannableString pButton = new SpannableString("确认");
        SpannableString nButton = new SpannableString("取消");
        pButton.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA73B")), 0, pButton.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        nButton.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA73B")), 0, nButton.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AlertDialog dialog = builder.setTitle("提示").setMessage("取消收藏")
                .setPositiveButton(pButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton(nButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        dialog.show();
    }
}
