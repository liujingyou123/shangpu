package com.finance.winport.trade.adapter;
/**
 * Created by liuworkmac on 17/5/10.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.account.LoginActivity;
import com.finance.winport.dialog.NoticeDelDialog;
import com.finance.winport.dialog.NoticeDialog;
import com.finance.winport.image.Batman;
import com.finance.winport.trade.model.Trade;
import com.finance.winport.trade.presenter.TradeCirclePresenter;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.UnitUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TradeCircleAdapter extends BaseAdapter {
    private Context mContext;
    private List<Trade> mData;
    private TradeCirclePresenter mPresenter;

    public TradeCircleAdapter(Context mContext, List<Trade> mData, TradeCirclePresenter presenter) {
        this.mContext = mContext;
        this.mData = mData;
        this.mPresenter = presenter;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mData != null) {
            ret = mData.size();
        }
        return ret;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_trade, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Trade trade = mData.get(i);
        if (trade != null) {
            viewHolder.tvTitle.setText(trade.getTitle());
            viewHolder.tvTime.setText(trade.getDateTime());
            viewHolder.tvZan.setText(trade.getPraiseNumber() + "");

            if ("1".equals(trade.getKind())) {
                viewHolder.imvFire.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imvFire.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(trade.getLikeStatus()) && "1".equals(trade.getLikeStatus())) {
                viewHolder.tvZan.setSelected(true);
            } else {
                viewHolder.tvZan.setSelected(false);
            }
            viewHolder.tvComments.setText(trade.getCommentNumber() + "");
            if (!TextUtils.isEmpty(trade.getContent())) {
                viewHolder.tvSub.setVisibility(View.VISIBLE);
                viewHolder.tvSub.setText(trade.getContent());
            } else {
                viewHolder.tvSub.setVisibility(View.GONE);
            }
            if (trade != null && trade.getImgList().size() > 0) {
                viewHolder.glImages.setVisibility(View.VISIBLE);
                viewHolder.tvSub.setVisibility(View.GONE);
                setGridLayout(viewHolder, trade.getImgList());
            } else {
                viewHolder.glImages.setVisibility(View.GONE);
            }


            if (trade.getH5obj() != null) {
                viewHolder.rlHref.setVisibility(View.VISIBLE);
                viewHolder.imvHref.setBackgroundResource(R.drawable.default_image_logo);
                Batman.getInstance().fromNet(trade.getH5obj().getUrl(), viewHolder.imvHref);
                viewHolder.tvHrefTitle.setText(trade.getH5obj().getTitle());
                viewHolder.tvHrefSub.setText(trade.getH5obj().getContent());

            } else {
                viewHolder.rlHref.setVisibility(View.GONE);
            }

            viewHolder.tvZan.setOnClickListener(new View.OnClickListener() {
                int index = i;

                @Override
                public void onClick(View v) {
                    if (SharedPrefsUtil.getUserInfo() != null) {
                        if (v.isSelected()) {  //取消点赞
                            mPresenter.cancelzanTopic(mData.get(index).getTopicId() + "", index);
                        } else { //点在
                            mPresenter.zanTopic(mData.get(index).getTopicId() + "", index);
                        }
                    } else {
                        Intent intent1 = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent1);
                    }

                }
            });

            if ("1".equals(trade.getCanBeDelete())) {
                viewHolder.imvDel.setVisibility(View.VISIBLE);
                viewHolder.imvDel.setOnClickListener(new View.OnClickListener() {
                    int index = i;

                    @Override
                    public void onClick(View v) {
                        NoticeDelDialog dialog = new NoticeDelDialog(mContext);
                        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
                            @Override
                            public void onClick() {
                                mPresenter.deleteTopic(mData.get(index).getTopicId() + "");
                            }
                        });
                        dialog.show();
                    }
                });
            } else {
                viewHolder.imvDel.setVisibility(View.GONE);
            }

        }

        return view;
    }

    private void setGridLayout(ViewHolder viewHolder, List<Trade.imgBean> imageUrls) {
        int imageSize = imageUrls.size();
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
        } else { // if (imageSize == 7 || imageSize == 8 || imageSize == 9)
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(mContext, 247.5f));

            viewHolder.glImages.setLayoutParams(lp);
            viewHolder.glImages.setColumnCount(3);
            viewHolder.glImages.setRowCount(3);
        }
//        else {
//            viewHolder.glImages.setVisibility(View.GONE);
//            viewHolder.tvSub.setVisibility(View.VISIBLE);
//            viewHolder.rlHref.setVisibility(View.VISIBLE);
//        }

        imageSize = (imageSize > 9 ? 9 : imageSize);
        for (int j = 0; j < imageSize; j++) {
            viewHolder.glImages.addView(getView(imageUrls.get(j).getImgUrl()));
        }
    }

    private ImageView getView(String url) {

        ImageView imageView = new ImageView(mContext);

        Batman.getInstance().fromNet(url, imageView);
        GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0f);
        GridLayout.Spec columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0f);

        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
        layoutParams.setMargins(6, 6, 6, 6);
        imageView.setLayoutParams(layoutParams);

        imageView.setBackgroundResource(R.drawable.default_image_logo);
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
        @BindView(R.id.imv_fire)
        ImageView imvFire;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}