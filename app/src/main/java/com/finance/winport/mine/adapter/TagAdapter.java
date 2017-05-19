package com.finance.winport.mine.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.finance.winport.R;
import com.finance.winport.util.UnitUtil;

import java.util.ArrayList;
import java.util.List;

//import com.finance.businessman.repo.model.TagItem;

/**
 * Created by xzw on 16/8/29.
 */
public class TagAdapter extends BaseAdapter {
    private Context context;
    private List<TagItem> list;
    private int selectPos = -1;
    private List<TagItem> multiSelect;
    private ChoiceType type;

    public TagAdapter(Context context, List<TagItem> list) {
        this.context = context;
        this.list = list;
        this.multiSelect = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        if (list == null) {
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setChoiceType(ChoiceType type) {
        this.type = type;
    }

    public void setMultiItemSelect(int selectPos) {
            if (list == null) return;
        TagItem item = list.get(selectPos);
        item.setSelected(true);
        multiSelect.add(item);
        notifyDataSetChanged();
    }

    public void setMultiItemSelect(int selectPos, boolean select) {
        if (list == null) return;
        TagItem item = list.get(selectPos);
        item.setSelected(select);
        if (select) {
            multiSelect.add(item);
        } else {
            if (multiSelect.contains(item)) {
                multiSelect.remove(item);
            }
        }
        notifyDataSetChanged();
    }

    public void setMultiItemSelect(TagItem tag, boolean select) {
        if (list == null) return;
        int pos = list.indexOf(tag);
        if (pos == -1) return;
        TagItem item = list.get(pos);
        item.setSelected(select);
        if (select) {
            multiSelect.add(item);
        } else {
            if (multiSelect.contains(item)) {
                multiSelect.remove(item);
            }
        }
        notifyDataSetChanged();
    }

    public void setSingleItemSelect(int selectPos) {
        if (list == null) return;
        if (this.selectPos != -1) {
            list.get(this.selectPos).setSelected(false);
        }
        this.selectPos = selectPos;
        list.get(selectPos).setSelected(true);
        notifyDataSetChanged();
    }
    public void setSingleItemUnSelect(int selectPos) {
        if (list == null) return;
        if (this.selectPos != -1) {
            list.get(this.selectPos).setSelected(false);
        }
        this.selectPos = selectPos;
        list.get(selectPos).setSelected(false);
        notifyDataSetChanged();
    }


    public void update(List<TagItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public TagItem getSingleSelectedItem() {
        return selectPos == -1 ? null : list.get(selectPos);
    }

    public List<TagItem> getMultiSelectedItems() {
        return multiSelect;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tag_item, viewGroup, false);
            holder = new ViewHolder();
            holder.tag = (TextView) convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {
            int colorNormal = Color.parseColor(list.get(position).getTextColor()[0]);
            int colorSelect = Color.parseColor(list.get(position).getTextColor()[1]);
            holder.tag.setTextColor(getColorSelector(colorNormal, colorSelect));
            Drawable drawableNormal = getDrawable(UnitUtil.dip2px(context, 2)
                    , Color.parseColor(list.get(position).getBgColor()[0])
                    , 0
                    , 0);
            Drawable drawableSelect = getDrawable(UnitUtil.dip2px(context, 2)
                    , Color.parseColor(list.get(position).getBgColor()[1])
                    , UnitUtil.dip2px(context, 0.5f)
                    , Color.parseColor(list.get(position).getStrokeColor()[1]));
            holder.tag.setBackgroundDrawable(getDrawableSelector(drawableNormal, drawableSelect));
            holder.tag.setText(list.get(position).getTagName());
            holder.tag.setSelected(list.get(position).isSelected());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tag;
    }

    private ColorStateList getColorSelector(int normal, int select) {
        int[] colors = new int[]{normal, select};
        int[][] states = new int[2][];
        states[0] = new int[]{-android.R.attr.state_selected, normal};
        states[1] = new int[]{android.R.attr.state_selected, select};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    private StateListDrawable getDrawableSelector(Drawable normal, Drawable select) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-android.R.attr.state_selected}, normal);
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, select);
        return stateListDrawable;
    }

    private GradientDrawable getDrawable(int radius, int fillColor, int strokeWidth, int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(radius);
        gradientDrawable.setColor(fillColor);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        return gradientDrawable;
    }

    private enum ChoiceType {
        SINGLE, MULTI;
    }
}
