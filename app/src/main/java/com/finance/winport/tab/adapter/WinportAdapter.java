package com.finance.winport.tab.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.tab.WinportActivity;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_winport_release_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //下架
        holder.dropOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dropOff = new Intent(context, WinportActivity.class);
                dropOff.putExtra("type", 5);
                context.startActivity(dropOff);
            }
        });
        //联系小二
        holder.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactAlert("13133333333");
            }
        });

        //重新发布
        holder.release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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

    void showContactAlert(final String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        SpannableString pButton = new SpannableString("拨打");
        SpannableString nButton = new SpannableString("取消");
        pButton.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA73B")), 0, pButton.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        nButton.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA73B")), 0, nButton.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AlertDialog dialog = builder.setTitle("提示").setMessage("直拨小二电话：" + phone)
                .setPositiveButton(pButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent().setAction(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + phone)));
                    }
                }).setNegativeButton(nButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        dialog.show();
    }
}
