package com.finance.winport.trade.adapter;

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
import com.finance.winport.trade.TradeHeadActivity;
import com.finance.winport.trade.TradeType;
import com.finance.winport.trade.model.TradeBible;
import com.finance.winport.trade.model.TradeHead;
import com.finance.winport.trade.model.TradeTopic;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.DrawableTopLeftTextView;
import com.finance.winport.view.HtmlTextView;
import com.finance.winport.view.dialog.TradeMorePopup;
import com.finance.winport.view.roundview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 2017/8/4.
 */

public class HomeTradeCircleAdapter extends BaseExpandableListAdapter {
    private Context context;
    private Map<String, List<BaseResponse>> data;
    private List<String> group = new ArrayList<>();

    public HomeTradeCircleAdapter(Context context, Map<String, List<BaseResponse>> data) {
        this.context = context;
        this.data = data;
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
        String title = group.get(groupPosition);
        groupHolder.title.setText(title == null ? "" : title);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (groupPosition) {
                    case 0://行业头条
                        context.startActivity(new Intent(context, TradeHeadActivity.class).putExtra("type", TradeType.HEAD));
                        break;
                    case 1://生意宝典
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
        if (child instanceof TradeHead) {
            TradeHead item = (TradeHead) child;
            holder.title.setText(item.title);
            holder.type.setText(item.content);
            holder.from.setText(item.source);
            holder.date.setText(item.dateTime);
            holder.scanCount.setText(item.viewCount + "浏览");
            if (item.kind) {
                holder.title.setDrawable(R.mipmap.label_top);
            } else {
                holder.title.setDrawable(0);
            }
            Batman.getInstance().fromNet(item.image, holder.img);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private void handleBibleItem(int groupPosition, int childPosition, TradeBibleViewHolder holder, View convertView) {
        Object child = getChild(groupPosition, childPosition);
        if (child instanceof TradeBible) {
            TradeBible item = (TradeBible) child;
            holder.desc.setText(item.title);
            holder.tip.setText(item.content);
            holder.date.setText(item.dateTime);
            holder.scanCount.setText(item.viewCount + "浏览");
            Batman.getInstance().fromNet(item.image, holder.img);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private void handleCommunityItem(int groupPosition, int childPosition, final TradeCommunityViewHolder holder, View convertView) {
        Object child = getChild(groupPosition, childPosition);
        if (child instanceof TradeTopic) {
            final TradeTopic item = (TradeTopic) child;
            holder.name.setText(item.nickName);
            holder.workType.setText(item.signature);
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
                holder.content.setHtml(item.content);
                holder.content.setVisibility(View.VISIBLE);
            } else {
                holder.content.setVisibility(View.GONE);
            }
            if (item.imgList != null && item.imgList.size() > 0) {
                holder.glImages.setVisibility(View.VISIBLE);
                setGridLayout(holder, item.imgList);
            } else {
                holder.glImages.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(item.getLikeStatus()) && "1".equals(item.getLikeStatus())) {
                holder.more.setSelected(true);
            } else {
                holder.more.setSelected(false);
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
            holder.praise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SharedPrefsUtil.getUserInfo() != null) {
                        startScaleAnim(v);
                        if (v.isSelected()) {  //取消点赞
                            item.likeStatus = "0";
                            item.praiseNumber -= 1;
                        } else { //点赞
                            item.likeStatus = "1";
                            item.praiseNumber += 1;
                        }
                        notifyDataSetChanged();
                    } else {
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }

                }
            });

            //delete
            if ("1".equals(item.getCanBeDelete())) {
                holder.more.setVisibility(View.VISIBLE);
                holder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TradeMorePopup deletePopup = new TradeMorePopup(context);
                        deletePopup.setOnDeleteListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //
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

                }
            });
        }
    }

    private void startScaleAnim(View target) {
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        final AnimatorSet animatorSet1 = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, "scaleX", 1.0f, 1.5f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, "scaleY", 1.0f, 1.5f);
        ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(target, "scaleX", 1.5f, 1.0f);
        ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(target, "scaleY", 1.5f, 1.0f);
        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY);//两个动画同时开始
        animatorSet.start();
        animatorSet1.setDuration(400);
        animatorSet1.setInterpolator(new DecelerateInterpolator());
        animatorSet1.play(scaleX1).with(scaleY1);//两个动画同时开始
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet1.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
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

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class TradeHeadViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        DrawableTopLeftTextView title;
        @BindView(R.id.type)
        TextView type;
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
        @BindView(R.id.tip)
        TextView tip;
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
        @BindView(R.id.work_type)
        TextView workType;
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
