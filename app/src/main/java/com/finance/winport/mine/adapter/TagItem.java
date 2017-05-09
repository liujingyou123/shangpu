package com.finance.winport.mine.adapter;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by xzw on 16/8/29.
 */
public class TagItem implements Serializable {
    private static String[] TEXT_COLOR = {"#9a9a9a", "#ffffff"};//text color: select,unSelect color
    private static String[] BG_COLOR = {"#f5f5f5", "#1976b2"};//background color: select,unSelect color
    private static String[] STROKE_COLOR = {"#9a9a9a", "#1976b2"};//stroke color:select,unSelect color
    private String tagId;
    private String tagName;
    private String level;
    private String category;//0：自定义，1：房源信息，2：小区信息，3：位置周边（二手房标签的分类）
    private boolean isSelected;
    private String tagColor;//display color
    private String[] textColor = TEXT_COLOR;//text color: select,unSelect color
    private String[] bgColor = BG_COLOR;//background color: select,unSelect color
    private String[] strokeColor = STROKE_COLOR;//stroke color:select,unSelect color
    private boolean singleChoice;

    public boolean isSingleChoice() {
        return singleChoice;
    }

    public void setSingleChoice(boolean singleChoice) {
        this.singleChoice = singleChoice;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }

    public String[] getTextColor() {
        return textColor;
    }

    public void setTextColor(String[] textColor) {
        this.textColor = textColor;
    }

    public String[] getBgColor() {
        return bgColor;
    }

    public void setBgColor(String[] bgColor) {
        this.bgColor = bgColor;
    }

    public String[] getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String[] strokeColor) {
        this.strokeColor = strokeColor;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDefault() {
        setTextColor(TEXT_COLOR);
        setBgColor(BG_COLOR);
        setStrokeColor(STROKE_COLOR);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TagItem) {
            TagItem item = (TagItem) obj;
            return this == obj || TextUtils.equals(this.tagName, item.getTagName());
        } else {
            return false;
        }
    }
}
