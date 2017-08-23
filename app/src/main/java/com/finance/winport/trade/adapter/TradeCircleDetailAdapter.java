package com.finance.winport.trade.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.dialog.NoticeDelDialog;
import com.finance.winport.home.H5Activity;
import com.finance.winport.image.Batman;
import com.finance.winport.trade.model.CommentResponse;
import com.finance.winport.trade.model.TradeDetailResponse;
import com.finance.winport.trade.presenter.TradeCircleDetailPresener;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.imagepreview.ImagePreviewActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TradeCircleDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_HEADER1 = 3;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_EMPTY = 2;
    private TradeDetailResponse.DataBean mData = new TradeDetailResponse.DataBean();
    private List<CommentResponse.DataBean.Comment> mComments = new ArrayList<>();
    private LayoutInflater layoutInflater;

    private Context mContext;
    private TradeCircleDetailPresener mPresenter;
    private String topicId;

    public TradeCircleDetailAdapter(Context context, String topicId, TradeCircleDetailPresener presenter) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.topicId = topicId;
        this.mPresenter = presenter;
    }

    public void setTraddeDetail(TradeDetailResponse.DataBean data) {
        if (data != null) {
            mData.setPraiseNumber(data.getPraiseNumber());
            mData.setSignature(data.getSignature());
            mData.setNickName(data.getNickName());
            mData.setCanBeDelete(data.getCanBeDelete());
            mData.setCommentNumber(data.getCommentNumber());
            mData.setContent(data.getContent());
            mData.setDateTime(data.getDateTime());
            mData.setH5obj(data.getH5obj());
            mData.setHeadPicture(data.getHeadPicture());
            mData.setImgList(data.getImgList());
            mData.setLikeStatus(data.getLikeStatus());
            mData.setPublishType(data.getPublishType());
            mData.setTitle(data.getTitle());
        }

        notifyDataSetChanged();
    }

    public void addComment(int index, CommentResponse.DataBean.Comment comment) {
        if (comment != null) {
            mComments.add(index, comment);
        }
        notifyDataSetChanged();
    }

    public void addComment(CommentResponse.DataBean.Comment comment) {
        if (comment != null) {
            mComments.add(comment);
        }
        notifyDataSetChanged();
    }

    public void setComments(List<CommentResponse.DataBean.Comment> comments) {
        mComments.clear();
        if (comments == null || comments.size() == 0) {
            mComments.add(null);
        } else {
            mComments.addAll(comments);
        }
        notifyDataSetChanged();
    }

    public void setMoreComments(List<CommentResponse.DataBean.Comment> comments) {
        if (comments != null && comments.size() > 0) {
            mComments.addAll(comments);
            notifyDataSetChanged();
        }
    }

    public List<CommentResponse.DataBean.Comment> getComments() {
        return mComments;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == 1) {
            return TYPE_HEADER1;
        } else if (mComments != null && mComments.size() == 1 && mComments.get(0) == null) {
            return TYPE_EMPTY;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderViewHolder(layoutInflater.inflate(R.layout.work_communit_detail_list_header, parent, false));
        }
        if (viewType == TYPE_HEADER1) {
            return new HeaderViewHolder1(layoutInflater.inflate(R.layout.comment_header, parent, false));
        }
        if (viewType == TYPE_EMPTY) {
            return new EmptyViewHolder(layoutInflater.inflate(R.layout.work_communit_detail_list_item_empty, parent, false));
        } else {
            return new ItemViewHolder(layoutInflater.inflate(R.layout.work_communit_detail_list_item, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            if (mData != null) {
                viewHolder.tvHeaderPhone.setText(mData.getNickName());
                String str = mData.getSignature();
                if (TextUtils.isEmpty(str)) {
                    str = "";
                }
                viewHolder.tvHeaderMsg.setText(str);
                Batman.getInstance().getImageWithCircle(mData.getHeadPicture(), viewHolder.ivHeaderIcon, R.mipmap.default_user_small, R.mipmap.default_user_small);
                viewHolder.tvHeaderTime.setText(mData.getDateTime());
                viewHolder.title.setText(mData.getTitle());

                if ("1".equals(mData.getKind())) {
//                    viewHolder.imvFire.setVisibility(View.VISIBLE);
                } else {
//                    viewHolder.imvFire.setVisibility(View.GONE);
                }

                if (mData != null && mData.getImgList() != null && mData.getImgList().size() > 0) {
                    viewHolder.glImages.setVisibility(View.VISIBLE);
                    setGridLayout(viewHolder, mData.getImgList());
                } else {
                    viewHolder.glImages.setVisibility(View.GONE);
                }

                if (!TextUtils.isEmpty(mData.getContent())) {
                    viewHolder.content.setVisibility(View.VISIBLE);
                    viewHolder.content.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                    viewHolder.content.getSettings().setDatabaseEnabled(true);
                    viewHolder.content.getSettings().setAppCacheEnabled(true);
                    viewHolder.content.loadData(mData.getContent(), "text/html; charset=UTF-8", null);
                } else {
                    viewHolder.content.setVisibility(View.GONE);
                }
                if (mData.getH5obj() != null) {
                    viewHolder.rlHref.setVisibility(View.VISIBLE);
                    viewHolder.rlHref.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent bannerDetails = new Intent(mContext, H5Activity.class);
                            bannerDetails.putExtra("type", 5);
                            bannerDetails.putExtra("url", mData.getH5obj().getUrl());
                            bannerDetails.putExtra("title", mData.getH5obj().getTitle());
                            mContext.startActivity(bannerDetails);
                        }
                    });
                    viewHolder.imvHref.setBackgroundResource(R.drawable.default_image_logo);
                    Batman.getInstance().fromNet(mData.getH5obj().getImageUrl(), viewHolder.imvHref);
                    viewHolder.tvHrefTitle.setText(mData.getH5obj().getTitle());
                    viewHolder.tvHrefSub.setText(mData.getH5obj().getContent());

                } else {
                    viewHolder.rlHref.setVisibility(View.GONE);
                }
            }

        } else if (holder instanceof HeaderViewHolder1) {

        } else if (holder instanceof EmptyViewHolder) {

        } else {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;

            final CommentResponse.DataBean.Comment info = getItem(position);
            if (info == null) {
                return;
            }

            if ("1".equals(info.getIsOwn() + "")) {
                viewHolder.imvDel.setVisibility(View.VISIBLE);
                viewHolder.imvDel.setOnClickListener(new View.OnClickListener() {
                    int index = position;

                    @Override
                    public void onClick(View v) {
                        NoticeDelDialog dialog = new NoticeDelDialog(mContext);
                        dialog.setMessage("评论删除之后无法恢复");
                        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
                            @Override
                            public void onClick() {
                                mPresenter.deleteComment(getItem(index).getId(), topicId);
                            }
                        });
                        dialog.show();
                    }
                });
            } else {
                viewHolder.imvDel.setVisibility(View.GONE);
            }
            if (info.getIsReply() == 1) {//1-评论 2-回复评论
                viewHolder.tvPhone.setText(info.getNickName());
            } else {
                String s = "<html><head>" + info.getNickName() + "<font color=\"#999999\">" + " 回复 " + (TextUtils.isEmpty(info.getParentNickName()) ? "" : info.getParentNickName()) + "</font>" + "</head></html>";
                viewHolder.tvPhone.setText(Html.fromHtml(s));
            }
            viewHolder.tvTime.setText(info.getDateTime() + "评论");
            viewHolder.tvComment.setText(info.getContent());
            Batman.getInstance().getImageWithCircle(info.getHeadPicture(), viewHolder.ivIcon, R.mipmap.default_user_small, R.mipmap.default_user_small);

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.showCommentDialog(info.getId() + "", info.getNickName());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int ret = 2;
        if (mComments != null && mComments.size() > 0) {
            ret = mComments.size() + 2;
        }
        return ret;
    }

    private void setGridLayout(HeaderViewHolder viewHolder, final ArrayList<TradeDetailResponse.DataBean.Img> imageUrls) {
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
        final ArrayList<String> urls = new ArrayList<>();
        for (int j = 0; j < imageSize; j++) {
            String url = imageUrls.get(j).getImgUrl();
            urls.add(url);
            ImageView imageView = getView(url, j);
            final int finalJ = j;
            imageView.setOnClickListener(new View.OnClickListener() {
                int index = finalJ;

                @Override
                public void onClick(View v) {
                    MobclickAgent.onEvent(mContext, "post_picture");
                    Intent intents = new Intent(mContext, ImagePreviewActivity.class);
                    intents.putExtra("pics", urls);
                    intents.putExtra("index", index);
                    mContext.startActivity(intents);
                }
            });
            viewHolder.glImages.addView(imageView);
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

    private CommentResponse.DataBean.Comment getItem(int pos) {
        if (mComments == null) {
            return null;
        }

        if (pos <= mComments.size()) {

        }
        return mComments.get(pos - 2);
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
        @BindView(R.id.imv_del)
        ImageView imvDel;

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
        @BindView(R.id.title)
        TextView title;
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
        @BindView(R.id.content)
        WebView content;
        @BindView(R.id.img_layout)
        LinearLayout imgLayout;

        public HeaderViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HeaderViewHolder1 extends RecyclerView.ViewHolder {

        public HeaderViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
