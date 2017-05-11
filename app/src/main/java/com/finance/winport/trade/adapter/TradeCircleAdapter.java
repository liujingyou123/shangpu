package com.finance.winport.trade.adapter;
/**
 * Created by liuworkmac on 17/5/10.
 */

import android.content.Context;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.dialog.NoticeDelDialog;
import com.finance.winport.image.Batman;
import com.finance.winport.trade.model.Trade;
import com.finance.winport.util.UnitUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TradeCircleAdapter extends BaseAdapter {
    private Context mContext;
    private List<Trade> mData;

    public TradeCircleAdapter(Context mContext, List<Trade> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mData != null) {
            ret = mData.size();
        }
        return 20;
    }

    @Override
    public Trade getItem(int i) {
        Trade ret = null;
        if (mData != null) {
            ret = mData.get(i);
        }
        return ret;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_trade, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        int index = i + 1;

        viewHolder.glImages.setVisibility(View.VISIBLE);
        viewHolder.tvSub.setVisibility(View.GONE);
        viewHolder.rlHref.setVisibility(View.GONE);

        viewHolder.glImages.removeAllViews();

        if (index == 1) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 247.5f));


            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(1);
            viewHolder.glImages.setRowCount(1);
        } else if (index == 2) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 120f));

            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(2);
            viewHolder.glImages.setRowCount(1);
        } else if (index == 3) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 85f));

            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(3);
            viewHolder.glImages.setRowCount(1);
        } else if (index == 4) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 247.5f));

            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(2);
            viewHolder.glImages.setRowCount(2);
        } else if (index == 5 || index == 6) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 163.5f));

            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(3);
            viewHolder.glImages.setRowCount(2);
        } else if (index == 7 || index == 8 || index == 9) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 247.5f));

            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(3);
            viewHolder.glImages.setRowCount(3);
        } else {
            viewHolder.glImages.setVisibility(View.GONE);
            viewHolder.tvSub.setVisibility(View.VISIBLE);
            viewHolder.rlHref.setVisibility(View.VISIBLE);
        }

        for (int j = 0; j < index; j++) {
            viewHolder.glImages.addView(getView());
        }

        viewHolder.imvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeDelDialog dialog = new NoticeDelDialog(mContext);
                dialog.show();
            }
        });
        return view;
    }


    private ImageView getView() {

        ImageView imageView = new ImageView(mContext);

        Batman.getInstance().fromNet("http://img0.imgtn.bdimg.com/it/u=941334686,3174396022&fm=23&gp=0.jpg", imageView);
        GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0f);
        GridLayout.Spec columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0f);

        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
        layoutParams.setMargins(6, 6, 6, 6);
        imageView.setLayoutParams(layoutParams);

        return imageView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_sub)
        TextView tvSub;
        @BindView(R.id.imv_href)
        ImageView imvHref;
        @BindView(R.id.tv_href_title)
        TextView tvHrefTitle;
        @BindView(R.id.tv_href_sub)
        TextView tvHrefSub;
        @BindView(R.id.rl_href)
        RelativeLayout rlHref;
        @BindView(R.id.gl_images)
        GridLayout glImages;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_zan)
        TextView tvZan;
        @BindView(R.id.tv_comments)
        TextView tvComments;
        @BindView(R.id.imv_del)
        ImageView imvDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}