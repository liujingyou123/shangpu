package com.finance.winport.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class CommonPopup extends PopupWindow {

    private static int defaultAnimationStyle = -1;

    public CommonPopup() {
    }

    public CommonPopup(View contentView) {
        super(contentView);
    }

    public CommonPopup(int width, int height) {
        super(width, height);
    }

    public CommonPopup(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    protected CommonPopup(Context context) {
        super(context);
        init();
    }

    public CommonPopup(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public CommonPopup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonPopup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CommonPopup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    protected void init() {
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置PopupWindow的View
//        this.setContentView(popupWindowView);
        //设置弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置弹出窗口是否支持多点触控
        //		 this.setSplitTouchEnabled(false);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置PopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.PopupAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable transparent_50 = new ColorDrawable(Color.parseColor("#7f000000"));
        //设置PopupWindow弹出窗体的背景
        this.setBackgroundDrawable(transparent_50);
    }

    public CommonPopup setDefaultAnimationStyle() {
        this.setAnimationStyle(defaultAnimationStyle);
        return this;
    }
}
