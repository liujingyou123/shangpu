package com.finance.winport.trade.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.image.Batman;
import com.finance.winport.log.XLog;
import com.finance.winport.trade.model.CommentResponse;
import com.finance.winport.trade.model.TradeDetailResponse;
import com.finance.winport.trade.presenter.TradeCircleDetailPresener;
import com.finance.winport.util.UnitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TradeCircleDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
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
            mData.setAttentionContent(data.getAttentionContent());
            mData.setCanBeDelete(data.getCanBeDelete());
            mData.setCommentNumber(data.getCommentNumber());
            mData.setContent(data.getContent());
            mData.setDateTime(data.getDateTime());
            mData.setH5obj(data.getH5obj());
            mData.setHeadPicture(data.getHeadPicture());
            mData.setImgList(data.getImgList());
            mData.setLikeStatus(data.getLikeStatus());
            mData.setPhone(data.getPhone());
            mData.setPublishType(data.getPublishType());
            mData.setTitle(data.getTitle());
        }

        notifyDataSetChanged();
    }

    public void setComments(List<CommentResponse.DataBean.Comment> comments) {
        mComments.clear();
        mComments.addAll(comments);
        notifyDataSetChanged();
    }

    public List<CommentResponse.DataBean.Comment> getComments() {
        return mComments;
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
            if (mData != null) {
                viewHolder.tvHeaderPhone.setText(mData.getPhone());
                String str = mData.getAttentionContent();
                if (TextUtils.isEmpty(str)) {
                    str = "";
                }
                viewHolder.tvHeaderMsg.setText("关注　" + str);
                Batman.getInstance().fromNet(mData.getHeadPicture(), viewHolder.ivHeaderIcon);
                viewHolder.tvHeaderTime.setText(mData.getDateTime());
                viewHolder.tvTitle.setText(mData.getTitle());
//                if ("1".equals(mData.getCanBeDelete())) {
//                    viewHolder.imvDel.setVisibility(View.VISIBLE);
//                    viewHolder.imvDel.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            mPresenter.deleteTopic(topicId);
//                        }
//                    });
//                } else {
//                    viewHolder.imvDel.setVisibility(View.GONE);
//                }
                if (mData != null && mData.getImgList() != null && mData.getImgList().size() > 0) {
                    viewHolder.glImages.setVisibility(View.VISIBLE);
                    setGridLayout(viewHolder, mData.getImgList());
                } else {
                    viewHolder.glImages.setVisibility(View.GONE);
                }

                if (!TextUtils.isEmpty(mData.getContent())) {
                    viewHolder.tvSub.setVisibility(View.VISIBLE);
                    viewHolder.tvSub.setText(mData.getContent());
                } else {
                    viewHolder.tvSub.setVisibility(View.GONE);
                }
                if (mData.getH5obj() != null) {
                    viewHolder.rlHref.setVisibility(View.VISIBLE);
                    Batman.getInstance().fromNet(mData.getH5obj().getUrl(), viewHolder.imvHref);
                    viewHolder.tvHrefTitle.setText(mData.getH5obj().getTitle());
                    viewHolder.tvHrefSub.setText(mData.getH5obj().getContent());

                } else {
                    viewHolder.rlHref.setVisibility(View.GONE);
                }
            }

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
                        mPresenter.deleteComment(getItem(index).getId(), topicId);
                    }
                });
            } else {
                viewHolder.imvDel.setVisibility(View.GONE);
            }
            viewHolder.tvPhone.setText(info.getPhone());
            viewHolder.tvTime.setText(info.getDateTime() + "评论");
            viewHolder.tvComment.setText(info.getContent());
            Batman.getInstance().fromNet(info.getHeadPicture(), viewHolder.ivIcon);

        }
    }

    @Override
    public int getItemCount() {
        int ret = 1;
        if (mComments != null && mComments.size() > 0) {
            ret = mComments.size() + 1;
        }
        return ret;
    }

    private void setGridLayout(HeaderViewHolder viewHolder, List<TradeDetailResponse.DataBean.Img> imageUrls) {
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

        return imageView;
    }

    private CommentResponse.DataBean.Comment getItem(int pos) {
        if (mComments == null) {
            return null;
        }

        if (pos <= mComments.size()) {

        }
        return mComments.get(pos - 1);
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
        @BindView(R.id.imv_del)
        ImageView imvDel;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
