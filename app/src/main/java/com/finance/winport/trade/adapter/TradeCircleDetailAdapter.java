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
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_EMPTY = 2;
    private TradeDetailResponse.DataBean mData = new TradeDetailResponse.DataBean();
    private List<CommentResponse.DataBean.Comment> mComments = new ArrayList<>();
    private LayoutInflater layoutInflater;

    private Context mContext;
    private TradeCircleDetailPresener mPresenter;
    private String topicId;
//    String data = "<body>\n" +
//            "  \n" +
//            "  <div id =\"flash_container\" class=\"noPrint\">    \n" +
//            "  </div>\n" +
//            "  \n" +
//            "  <div class=\"container-fluid\">  \n" +
//            "      \n" +
//            "<div class=\"row-fluid article_row_fluid\">\n" +
//            "    <div class=\"span8 contant article_detail_bg\">\n" +
//            "        <h1>阿里要在日本再造一个支付宝</h1>\n" +
//            "        <div class=\"article_meta\">\n" +
//            "            <div style=\"margin-bottom: 5px;\">\n" +
//            "            <span class=\"timestamp\">时间&nbsp;2017-08-16 12:48:36\n" +
//            "            </span>\n" +
//            "            <span class=\"from\">\n" +
//            "                <i class=\"icon-globe\"></i>\n" +
//            "                    <a class=\"cut cut28 from\" href=\"/sites/MJNVfm\" target=\"_blank\">36氪\n" +
//            "                    </a>\n" +
//            "            </span>\n" +
//            "            </div>\n" +
//            "            <div class=\"source\">\n" +
//            "                <i style=\"float:left;\">原文</i>&nbsp; \n" +
//            "                <a class=\"cut cut70\" href=\"http://36kr.com/p/5088324.html?utm_source=tuicool&amp;utm_medium=referral\" style=\"display:inline-block;\">http://36kr.com/p/5088324.html</a>\n" +
//            "            </div>\n" +
//            "            <div>\n" +
//            "                <span>主题</span>\n" +
//            "                <a href=\"/topics/10100004\" target='_blank' >\n" +
//            "                    <span class=\"new-label\">支付宝</span>\n" +
//            "                </a>\n" +
//            "            </div>\n" +
//            "        </div>\n" +
//            "        <div class=\"article_body\" id=\"nei\">\n" +
//            "            <p> <img src=\"http://img0.tuicool.com/AVNbi2V.jpg!web\" class=\"alignCenter\" /> </p> \n" +
//            "<p> 本文 <a href=\"http://www.yicai.com/news/5331457.html\" target=\"_blank\" rel=\"nofollow,noindex\">来自第一财经</a> ，36氪经授权转载。 </p> \n" +
//            "<p>对于访日的中国游客来说，在日本店铺使用支付宝进行结算并不算是一件新鲜事，但是阿里显然不满足于此，它希望日本当地的住民也能享受到手机支付的便捷。</p> \n" +
//            "<p>据日经新闻报道，阿里巴巴集团将于2018年春季在日本推出基于智能手机的电子结算服务。利用智能手机应用程序中存放的现金即可购物。将面向日本人提供与中国“支付宝”同样构造的服务，力争3年内赢得1千万用户。</p> \n" +
//            "<p>新服务将由阿里巴巴旗下的金融公司蚂蚁金服日本分公司提供。利用店铺生成、或消费者的智能手机应用程序显示的二维码进行结算，二维码写入了支付账户和转账账户等的信息。</p> \n" +
//            "<p>支付宝目前可以在罗森、电器店和百货商场等3万多家日本国内店铺使用，预计到2017年底，支付宝加盟店铺将增加至约5万家。 阿里巴巴在日本推出的新手机支付服务将以这些店铺为中心，充分利用这个因访日游客增加而扩张起来的支付宝加盟网络。除了结算以外，还将逐步增加生活相关的功能，列如实现电影票的预订和购买等。</p> \n" +
//            "<p>由于支付宝只服务于拥有中国银行账户的用户，因此新的手机结算服务将使用新品牌名称。服务推出后，日本用户将也能在中国境内利用此结算服务。日本每年访问中国的人数大约为250万人次，出差和旅行时的便利性将随之提高。</p> \n" +
//            "<p>对于以现金结算为主流的日本市场来说，手机支付市场的增长空间十分巨大。据野村综合研究所的调查，日本国内2017年电子货币的结算市场仅为5.6万亿日元，还有很大的增长空间。</p>\n" +
//            "        </div>\n" +
//            "        <div class=\"article_social\">\n" +
//            "         <div class=\"article_like\">\n" +
//            "    <div class=\"circle circle-like\" id=\"my_zan\" data_id=\"6BNZV3n\">  </div>\n" +
//            "\n" +
//            "</div>\n" +
//            "</body>";

    public TradeCircleDetailAdapter(Context context, String topicId, TradeCircleDetailPresener presenter) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.topicId = topicId;
        this.mPresenter = presenter;
    }

    public void setTraddeDetail(TradeDetailResponse.DataBean data) {
        if (data != null) {
            mData.setPraiseNumber(data.getPraiseNumber());
            mData.setSignature(TextUtils.isEmpty(data.getSignature()) ? "老板很懒，暂未设置签名" : data.getSignature());
            mData.setNickName(data.getNickName()/*"稻草人Kevin"*/);
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
//                viewHolder.content.setHtml(data);
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
                String s = "<html><body><p>" + info.getNickName() + "<span style=\"color:#999999\">" + " 回复 " + (TextUtils.isEmpty(info.getParentNickName()) ? "哈哈" : info.getParentNickName()) + "</span>" + "</p></body><html/>";
                viewHolder.tvPhone.setText(Html.fromHtml(s));
            }
            viewHolder.tvTime.setText(info.getDateTime() + "评论");
            viewHolder.tvComment.setText(info.getContent());
            Batman.getInstance().getImageWithCircle(info.getHeadPicture(), viewHolder.ivIcon, R.mipmap.default_user_small, R.mipmap.default_user_small);

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.showCommentDialog(info.getCommentatorId() + "", info.getNickName());
                }
            });
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
        @BindView(R.id.imv_del)
        ImageView imvDel;
        @BindView(R.id.content)
        WebView content;
        //        HtmlTextView content;
        @BindView(R.id.img_layout)
        LinearLayout imgLayout;

        public HeaderViewHolder(View itemView) {
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
