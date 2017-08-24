package com.finance.winport.trade.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.widget.Space;
import android.support.v7.widget.GridLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.account.LoginActivity;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.H5Activity;
import com.finance.winport.image.Batman;
import com.finance.winport.image.BatmanCallBack;
import com.finance.winport.trade.InfoDetailsActivity;
import com.finance.winport.trade.TradeCircleDetailActivity;
import com.finance.winport.trade.TradeHeadActivity;
import com.finance.winport.trade.TradeType;
import com.finance.winport.trade.model.TradeSub;
import com.finance.winport.trade.model.TradeTopic;
import com.finance.winport.trade.presenter.TradeHomePresenter;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.DrawableTopLeftTextView;
import com.finance.winport.view.HtmlTextView;
import com.finance.winport.view.dialog.TradeMorePopup;
import com.finance.winport.view.roundview.RoundedImageView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 2017/8/4.
 */

public class TradeHomeCircleAdapter extends BaseExpandableListAdapter {
    private Context context;
    private TradeHomePresenter presenter;
    private Map<String, List<BaseResponse>> data;
    private List<String> group = new ArrayList<>();

    public TradeHomeCircleAdapter(Context context, TradeHomePresenter presenter, Map<String, List<BaseResponse>> data) {
        this.context = context;
        this.data = data;
        this.presenter = presenter;
        if (data != null) {
            group.addAll(data.keySet());
        }
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(group.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (data.get(group.get(groupPosition)) != null) {
            return data.get(group.get(groupPosition)).get(childPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return groupPosition;
    }

    @Override
    public int getChildTypeCount() {
        return 3;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.trade_item_group, parent, false);
            groupHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupViewHolder) convertView.getTag();
        }
        if (groupPosition > 0) {
            groupHolder.divider.setVisibility(View.VISIBLE);
        } else {
            groupHolder.divider.setVisibility(View.GONE);
        }
        String title = group.get(groupPosition);
        groupHolder.title.setText(title == null ? "" : title);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (groupPosition) {
                    case 0://行业头条
                        MobclickAgent.onEvent(context, "ircle_industrymore");
                        context.startActivity(new Intent(context, TradeHeadActivity.class).putExtra("type", TradeType.HEAD));
                        break;
                    case 1://生意宝典
                        MobclickAgent.onEvent(context, "circle_guidancemore");
                        context.startActivity(new Intent(context, TradeHeadActivity.class).putExtra("type", TradeType.BIBLE));
                        break;
                    case 2://生意社区
                        context.startActivity(new Intent(context, TradeHeadActivity.class).putExtra("type", TradeType.CIRCLE));
                        break;

                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = getView(groupPosition, childPosition, convertView, parent);
        return convertView;
    }

    private View getView(int groupPosition, int childPosition, View convertView, ViewGroup parent) {
        if (getChildType(groupPosition, childPosition) == 0) {
            TradeHeadViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.trade_item_child_head, parent, false);
                holder = new TradeHeadViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (TradeHeadViewHolder) convertView.getTag();
            }
            handleHeadItem(groupPosition, childPosition, holder, convertView);
        } else if (getChildType(groupPosition, childPosition) == 1) {
            TradeBibleViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.trade_item_child_bible, parent, false);
                holder = new TradeBibleViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (TradeBibleViewHolder) convertView.getTag();
            }
            handleBibleItem(groupPosition, childPosition, holder, convertView);
        } else if (getChildType(groupPosition, childPosition) == 2) {
            TradeCommunityViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.trade_item_child_community, parent, false);
                holder = new TradeCommunityViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (TradeCommunityViewHolder) convertView.getTag();
            }
            handleCommunityItem(groupPosition, childPosition, holder, convertView);
        }
        return convertView;
    }

    private void handleHeadItem(int groupPosition, int childPosition, TradeHeadViewHolder holder, View convertView) {
        Object child = getChild(groupPosition, childPosition);
        if (child instanceof TradeSub) {
            final TradeSub item = (TradeSub) child;
            holder.title.setText(item.title);
            if (item.tagList != null && item.tagList.size() > 0 && item.tagList.get(0) != null) {
                holder.tag.setText(item.tagList.get(0).tagName);
            }
            holder.from.setText(item.source);
            holder.date.setText(item.dateTime);
            holder.scanCount.setText(item.viewCount + "浏览");
            if (item.kind == 1) {
                holder.title.setDrawable(R.mipmap.label_top);
            } else {
                holder.title.setDrawable(0);
            }
            Batman.getInstance().fromNet(item.image, holder.img);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MobclickAgent.onEvent(context, "circle_industryarticle");
                    context.startActivity(new Intent(context, InfoDetailsActivity.class)
                            .putExtra("id", item.contentId)
                            .putExtra("title", item.title)
                            .putExtra("type", TradeType.HEAD_DETAILS));
                }
            });
        }
    }

    private void handleBibleItem(int groupPosition, int childPosition, TradeBibleViewHolder holder, View convertView) {
        Object child = getChild(groupPosition, childPosition);
        if (child instanceof TradeSub) {
            final TradeSub item = (TradeSub) child;
            holder.desc.setText(item.title);
            if (item.tagList != null && item.tagList.size() > 0 && item.tagList.get(0) != null) {
                holder.tag.setText(item.tagList.get(0).tagName);
            }
            holder.date.setText(item.dateTime);
            holder.scanCount.setText(item.viewCount + "浏览");
            Batman.getInstance().fromNet(item.image, holder.img);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MobclickAgent.onEvent(context, "circle_guidancearticle");
                    context.startActivity(new Intent(context, InfoDetailsActivity.class)
                            .putExtra("id", item.contentId)
                            .putExtra("title", item.title)
                            .putExtra("type", TradeType.BIBLE_DETAILS));
                }
            });
        }
    }

    private void handleCommunityItem(final int groupPosition, final int childPosition, final TradeCommunityViewHolder holder, View convertView) {
        Object child = getChild(groupPosition, childPosition);
        if (child instanceof TradeTopic) {
            final TradeTopic item = (TradeTopic) child;
            holder.name.setText(item.nickName);
            holder.sign.setText(item.signature);
            Batman.getInstance().fromNet(item.headPicture, new BatmanCallBack() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    holder.headImg.setImageBitmap(bitmap);
                }

                @Override
                public void onFailure(Exception error) {
                }
            });
            holder.title.setText(item.title);
            holder.title.setVisibility(View.VISIBLE);
            if (TextUtils.equals(item.kind, "1")) {
                holder.title.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.label_top, 0, 0, 0);
            } else {
                holder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            if (!TextUtils.isEmpty(item.content)) {
                holder.content.setVisibility(View.VISIBLE);
                holder.content.setHtml(item.getContent(), false);
            } else {
                holder.content.setVisibility(View.GONE);
            }
            if (item.imgList != null && item.imgList.size() > 0) {
                holder.glImages.setVisibility(View.VISIBLE);
                setGridLayout(holder, item.imgList);
            } else {
                holder.glImages.setVisibility(View.GONE);
            }

            if (item.getH5obj() != null) {
                holder.rlHref.setVisibility(View.VISIBLE);
                holder.rlHref.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent bannerDetails = new Intent(context, H5Activity.class);
                        bannerDetails.putExtra("type", 5);
                        bannerDetails.putExtra("url", item.getH5obj().getUrl());
                        bannerDetails.putExtra("title", item.getH5obj().getTitle());
                        context.startActivity(bannerDetails);
                    }
                });
                holder.imvHref.setBackgroundResource(R.drawable.default_image_logo);
                Batman.getInstance().fromNet(item.getH5obj().getImageUrl(), holder.imvHref);
                holder.tvHrefTitle.setText(item.getH5obj().getTitle());
                holder.tvHrefSub.setText(item.getH5obj().getContent());

            } else {
                holder.rlHref.setVisibility(View.GONE);
            }

            holder.releaseTime.setText(item.dateTime);
            holder.praise.setText(item.praiseNumber + "");
            holder.praise.setSelected(TextUtils.equals(item.likeStatus, "1") ? true : false);
            holder.commentCount.setText(item.commentNumber + "");
            holder.commentCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, TradeCircleDetailActivity.class);
                    intent.putExtra("topicId", item.getTopicId() + "")
                            .putExtra("comment", true);
                    context.startActivity(intent);
                }
            });
            holder.praise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SharedPrefsUtil.getUserInfo() != null) {
                        TextViewUtil.startScaleAnim(v);
                        if (v.isSelected()) {  //取消点赞
                            presenter.cancelZanTopic(item.topicId + "", groupPosition, childPosition);
                        } else { //点赞
                            presenter.zanTopic(item.topicId + "", groupPosition, childPosition);
                        }
                        notifyDataSetChanged();
                    } else {
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }

                }
            });

            //delete
            if (TextUtils.equals(item.getCanBeDelete(), "1")) {
                holder.more.setVisibility(View.VISIBLE);
                holder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TradeMorePopup deletePopup = new TradeMorePopup(context);
                        deletePopup.setOnDeleteListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                presenter.deleteTopic(item.topicId + "", groupPosition, childPosition);
                            }
                        });
                        deletePopup.showAsDropDown(holder.more, UnitUtil.dip2px(context, -40), UnitUtil.dip2px(context, -10));
                    }
                });
            } else {
                holder.more.setVisibility(View.GONE);
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item != null) {
                        Intent intent = new Intent(context, TradeCircleDetailActivity.class);
                        intent.putExtra("topicId", item.getTopicId() + "");
                        context.startActivity(intent);
                    }
                }
            });
        }
    }


    private void setGridLayout(TradeCommunityViewHolder holder, List<TradeTopic.imgBean> imageUrls) {
        int imageSize = imageUrls.size();
        holder.glImages.removeAllViews();
        if (imageSize == 1) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(context, 247.5f));

            holder.glImages.setLayoutParams(lp);
            holder.glImages.setColumnCount(1);
            holder.glImages.setRowCount(1);
        } else if (imageSize == 2) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(context, 126f));

            holder.glImages.setLayoutParams(lp);
            holder.glImages.setColumnCount(2);
            holder.glImages.setRowCount(1);
        } else {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(context, 78f));

            holder.glImages.setLayoutParams(lp);
            holder.glImages.setColumnCount(3);
            holder.glImages.setRowCount(1);
        }
        imageSize = (imageSize > 3 ? 3 : imageSize);
        for (int j = 0; j < imageSize; j++) {
            holder.glImages.addView(getView(imageUrls.get(j).getImgUrl(), j, imageUrls.size()));
        }
    }

    private View getView(String url, int position, int imageSize) {
        View imageLayout = LayoutInflater.from(context).inflate(R.layout.image_layout, null);
        ImageViewHolder holder = new ImageViewHolder(imageLayout);
        Batman.getInstance().fromNet(url, holder.img);
        GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0f);
        GridLayout.Spec columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0f);
        GridLayout.LayoutParams lp = new GridLayout.LayoutParams(rowSpec, columnSpec);
        lp.width = 0;
        if (position > 0) {
            lp.leftMargin = UnitUtil.dip2px(context, 7.5f);
        }
        imageLayout.setLayoutParams(lp);
        if (imageSize > 3 && position == 2) {
            holder.overlay.setVisibility(View.VISIBLE);
            holder.count.setText(imageSize + "");
        } else {
            holder.overlay.setVisibility(View.GONE);
        }
        return imageLayout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.divider)
        Space divider;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class TradeHeadViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        DrawableTopLeftTextView title;
        @BindView(R.id.tag)
        TextView tag;
        @BindView(R.id.from)
        TextView from;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.scan_count)
        TextView scanCount;

        TradeHeadViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class TradeBibleViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.content)
        TextView desc;
        @BindView(R.id.tag)
        TextView tag;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.scan_count)
        TextView scanCount;

        TradeBibleViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class TradeCommunityViewHolder {
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
        @BindView(R.id.glImages)
        GridLayout glImages;
        @BindView(R.id.release_time)
        TextView releaseTime;
        @BindView(R.id.praise)
        TextView praise;
        @BindView(R.id.divider)
        View divider;
        @BindView(R.id.comment_count)
        TextView commentCount;

        @BindView(R.id.imv_href)
        ImageView imvHref;
        @BindView(R.id.tv_href_title)
        TextView tvHrefTitle;
        @BindView(R.id.tv_href_sub)
        TextView tvHrefSub;
        @BindView(R.id.rl_href)
        RelativeLayout rlHref;

        TradeCommunityViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ImageViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.overlay)
        View overlay;

        ImageViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
