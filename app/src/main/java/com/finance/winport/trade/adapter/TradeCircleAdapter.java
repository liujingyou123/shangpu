package com.finance.winport.trade.adapter;
/**
 * Created by liuworkmac on 17/5/10.
 */

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.account.LoginActivity;
import com.finance.winport.dialog.NoticeDelDialog;
import com.finance.winport.home.H5Activity;
import com.finance.winport.image.Batman;
import com.finance.winport.image.BatmanCallBack;
import com.finance.winport.trade.TradeCircleDetailActivity;
import com.finance.winport.trade.TradeCircleListFragment;
import com.finance.winport.trade.model.TradeTopic;
import com.finance.winport.trade.presenter.TradeCirclePresenter;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.HtmlTextView;
import com.finance.winport.view.roundview.RoundedImageView;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TradeCircleAdapter extends BaseAdapter {
    private Context mContext;
    private List<TradeTopic> mData;
    private TradeCirclePresenter mPresenter;

    public TradeCircleAdapter(Context mContext, List<TradeTopic> mData, TradeCirclePresenter presenter) {
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
    public TradeTopic getItem(int i) {
        TradeTopic ret = null;
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
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.trade_item_child_community, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final TradeTopic trade = mData.get(i);
        if (trade != null) {
            viewHolder.name.setText(trade.nickName);
            viewHolder.sign.setText(trade.signature);
            Batman.getInstance().fromNet(trade.headPicture, new BatmanCallBack() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    viewHolder.headImg.setImageBitmap(bitmap);
                }

                @Override
                public void onFailure(Exception error) {
                }
            });
            viewHolder.title.setText(trade.getTitle());
            viewHolder.releaseTime.setText(trade.getDateTime());
            viewHolder.praise.setText(trade.getPraiseNumber() + "");

            if (TextUtils.equals(trade.kind, "1")) {
                viewHolder.title.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.label_top, 0, 0, 0);
            } else {
                viewHolder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            if (!TextUtils.isEmpty(trade.getLikeStatus()) && "1".equals(trade.getLikeStatus())) {
                viewHolder.praise.setSelected(true);
            } else {
                viewHolder.praise.setSelected(false);
            }
            viewHolder.commentCount.setText(trade.getCommentNumber() + "");
            if (!TextUtils.isEmpty(trade.getContent())) {
                viewHolder.content.setVisibility(View.VISIBLE);
                viewHolder.content.setHtml(trade.getContent(), false);
            } else {
                viewHolder.content.setVisibility(View.GONE);
            }
            if (trade != null && trade.getImgList().size() > 0) {
                viewHolder.glImages.setVisibility(View.VISIBLE);
                setGridLayout(viewHolder, trade.getImgList());
            } else {
                viewHolder.glImages.setVisibility(View.GONE);
            }


            if (trade.getH5obj() != null) {
                viewHolder.rlHref.setVisibility(View.VISIBLE);
                viewHolder.rlHref.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent bannerDetails = new Intent(mContext, H5Activity.class);
                        bannerDetails.putExtra("type", 5);
                        bannerDetails.putExtra("url", trade.getH5obj().getUrl());
                        bannerDetails.putExtra("title", trade.getH5obj().getTitle());
                        mContext.startActivity(bannerDetails);
                    }
                });
                viewHolder.imvHref.setBackgroundResource(R.drawable.default_image_logo);
                Batman.getInstance().fromNet(trade.getH5obj().getImageUrl(), viewHolder.imvHref);
                viewHolder.tvHrefTitle.setText(trade.getH5obj().getTitle());
                viewHolder.tvHrefSub.setText(trade.getH5obj().getContent());

            } else {
                viewHolder.rlHref.setVisibility(View.GONE);
            }

            viewHolder.praise.setOnClickListener(new View.OnClickListener() {
                int index = i;

                @Override
                public void onClick(View v) {
                    if (SharedPrefsUtil.getUserInfo() != null) {
                        TextViewUtil.startScaleAnim(v);
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
                viewHolder.more.setVisibility(View.VISIBLE);
                viewHolder.more.setOnClickListener(new View.OnClickListener() {
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
                viewHolder.more.setVisibility(View.GONE);
            }

        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(mContext, "circle_comment");
                if (trade != null) {
                    Intent intent = new Intent(mContext, TradeCircleDetailActivity.class);
                    intent.putExtra("topicId", trade.getTopicId() + "");
                    mContext.startActivity(intent);
                }
            }
        });

        return view;
    }

    private void setGridLayout(ViewHolder viewHolder, List<TradeTopic.imgBean> imageUrls) {
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

        imageSize = (imageSize > 9 ? 9 : imageSize);
        for (int j = 0; j < imageSize; j++) {
            viewHolder.glImages.addView(getView(imageUrls.get(j).getImgUrl(), j));
        }
    }

    private ImageView getView(String url, int position) {

        ImageView imageView = new ImageView(mContext);

        Batman.getInstance().fromNet(url, imageView);
        GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0f);
        GridLayout.Spec columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0f);

        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
        layoutParams.width = 0;
        int m = UnitUtil.dip2px(mContext, 7.5f);
        int left = position % 3 == 0 ? 0 : m;
        int top = position / 3 == 0 ? 0 : m;
        layoutParams.setMargins(left, top, 0, 0);
        imageView.setLayoutParams(layoutParams);

        imageView.setBackgroundResource(R.drawable.default_image_logo);
        return imageView;
    }

    static class ViewHolder {
        @BindView(R.id.headImg)
        RoundedImageView headImg;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.sign)
        TextView sign;
        @BindView(R.id.more)
        ImageView more;
        @BindView(R.id.top)
        RelativeLayout top;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        HtmlTextView content;
        @BindView(R.id.imv_href)
        ImageView imvHref;
        @BindView(R.id.tv_href_title)
        TextView tvHrefTitle;
        @BindView(R.id.tv_href_sub)
        TextView tvHrefSub;
        @BindView(R.id.rl_href)
        RelativeLayout rlHref;
        @BindView(R.id.glImages)
        GridLayout glImages;
        @BindView(R.id.img_layout)
        LinearLayout imgLayout;
        @BindView(R.id.release_time)
        TextView releaseTime;
        @BindView(R.id.praise)
        TextView praise;
        @BindView(R.id.comment_count)
        TextView commentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}