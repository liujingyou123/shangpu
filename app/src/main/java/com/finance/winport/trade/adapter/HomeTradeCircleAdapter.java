package com.finance.winport.trade.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.image.Batman;
import com.finance.winport.trade.model.TradeCanon;
import com.finance.winport.trade.model.TradeCommunity;
import com.finance.winport.trade.model.TradeHead;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.DrawableTopLeftTextView;
import com.finance.winport.view.NestGridView;
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

                        break;
                    case 1://生意宝典

                        break;
                    case 2://生意社区

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
            TradeCanonViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.trade_item_child_canon, parent, false);
                holder = new TradeCanonViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (TradeCanonViewHolder) convertView.getTag();
            }
            handleCanonItem(groupPosition, childPosition, holder, convertView);
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

    private void handleCanonItem(int groupPosition, int childPosition, TradeCanonViewHolder holder, View convertView) {
        Object child = getChild(groupPosition, childPosition);
        if (child instanceof TradeCanon) {
            TradeCanon item = (TradeCanon) child;
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
        if (child instanceof TradeCommunity) {
            TradeCommunity item = (TradeCommunity) child;
            if (!TextUtils.isEmpty(item.title)) {
                holder.title.setText(item.title);
                holder.title.setVisibility(View.VISIBLE);
            } else {
                holder.title.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(item.content)) {
                holder.desc.setText(item.content);
                holder.desc.setVisibility(View.VISIBLE);
            } else {
                holder.desc.setVisibility(View.GONE);
            }
            if (item.imgList != null && item.imgList.size() > 0) {
                holder.gridImage.setVisibility(View.VISIBLE);
                int count = item.imgList.size();
                ViewGroup.LayoutParams lp = holder.gridImage.getLayoutParams();
                if (count == 1) {
                    lp.height = UnitUtil.dip2px(context, 248);
                } else if (count == 2) {
                    lp.height = UnitUtil.dip2px(context, 126);
                } else {
                    lp.height = UnitUtil.dip2px(context, 78);
                }
                holder.gridImage.requestLayout();
                setItem(holder.gridImage, item.imgList);
            } else {
                holder.gridImage.setVisibility(View.GONE);
            }

            holder.releaseTime.setText(item.dateTime);
            holder.praise.setText(item.praiseNumber);
            holder.commentCount.setText(item.commentNumber);
            //delete
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
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private void setItem(LinearLayout gridImage, List<TradeCommunity.ImageList> imageLists) {
        gridImage.removeAllViews();
        int size = imageLists.size();
        for (int i = 0; i < size; i++) {
            if (i >= 3) break;
            View imageLayout = LayoutInflater.from(context).inflate(R.layout.image_layout, null);
            ImageViewHolder holder = new ImageViewHolder(imageLayout);
            Batman.getInstance().fromNet(imageLists.get(i).imgUrl, holder.img);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            if (i > 0) {
                lp.leftMargin = UnitUtil.dip2px(context, 7.5f);
            }
            imageLayout.setLayoutParams(lp);
            gridImage.addView(imageLayout);
            if (size > 3 && i == 2) {
                holder.overlay.setVisibility(View.VISIBLE);
                holder.count.setText(size + "");
            } else {
                holder.overlay.setVisibility(View.GONE);
            }
        }
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

    static class TradeCanonViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.tip)
        TextView tip;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.scan_count)
        TextView scanCount;

        TradeCanonViewHolder(View view) {
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
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.gridImage)
        LinearLayout gridImage;
        @BindView(R.id.release_time)
        TextView releaseTime;
        @BindView(R.id.praise)
        TextView praise;
        @BindView(R.id.divider)
        View divider;
        @BindView(R.id.comment_count)
        TextView commentCount;

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
        FrameLayout overlay;

        ImageViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
