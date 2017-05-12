package com.finance.winport.trade.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.finance.winport.R;
import com.finance.winport.image.Batman;
import com.finance.winport.util.UnitUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WorkCommunitDetailListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private List<String> datas;

    private LayoutInflater layoutInflater;

    private Context mContext;

    public WorkCommunitDetailListAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.datas = datas;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderViewHolder(layoutInflater.inflate(R.layout.work_communit_detail_list_header, parent, false));
        } else {
            return new ItemViewHolder(layoutInflater.inflate(R.layout.work_communit_detail_list_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            viewHolder.tvHeaderPhone.setText("133****4559");
            int index = 11 + 1;

            viewHolder.glImages.setVisibility(View.VISIBLE);
            viewHolder.tvSub.setVisibility(View.GONE);
            viewHolder.rlHref.setVisibility(View.GONE);

            setGridLayout(viewHolder, index);

        } else {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;

            final String info = getItem(position);
            if (info == null) {
                return;
            }

            viewHolder.tvPhone.setText("187****3112");
            viewHolder.tvTime.setText(info + "分钟前评论");

        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 1 : datas.size() + 1;
    }

    private void setGridLayout(HeaderViewHolder viewHolder, int imageSize) {
        viewHolder.glImages.removeAllViews();

        if (imageSize == 1) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 247.5f));


            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(1);
            viewHolder.glImages.setRowCount(1);
        } else if (imageSize == 2) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 120f));

            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(2);
            viewHolder.glImages.setRowCount(1);
        } else if (imageSize == 3) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 85f));

            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(3);
            viewHolder.glImages.setRowCount(1);
        } else if (imageSize == 4) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 247.5f));

            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(2);
            viewHolder.glImages.setRowCount(2);
        } else if (imageSize == 5 || imageSize == 6) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 163.5f));

            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(3);
            viewHolder.glImages.setRowCount(2);
        } else if (imageSize == 7 || imageSize == 8 || imageSize == 9) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 247.5f));

            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(3);
            viewHolder.glImages.setRowCount(3);
        } else { //TODO  这里仅做UI调试
            viewHolder.glImages.setVisibility(View.GONE);
            viewHolder.tvSub.setVisibility(View.VISIBLE);
            viewHolder.rlHref.setVisibility(View.VISIBLE);
        }

        for (int j = 0; j < imageSize; j++) {
            viewHolder.glImages.addView(getView(null));
        }
    }

    private ImageView getView(String url) {

        ImageView imageView = new ImageView(mContext);

        //TODO 真是数据 用URL
        Batman.getInstance().fromNet("http://img0.imgtn.bdimg.com/it/u=941334686,3174396022&fm=23&gp=0.jpg", imageView);
        GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0f);
        GridLayout.Spec columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0f);

        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
        layoutParams.setMargins(6, 6, 6, 6);
        imageView.setLayoutParams(layoutParams);

        return imageView;
    }
    private String getItem(int pos) {
        if (datas == null || datas.size() == 0) {
            return null;
        }

        return datas.get(pos - 1);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_comment)
        TextView tvComment;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_header_icon)
        ImageView ivHeaderIcon;
        @BindView(R.id.tv_header_phone)
        TextView tvHeaderPhone;
        @BindView(R.id.tv_header_msg)
        TextView tvHeaderMsg;
        @BindView(R.id.tv_header_time)
        TextView tvHeaderTime;
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

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
