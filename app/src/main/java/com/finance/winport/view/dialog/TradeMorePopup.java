package com.finance.winport.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.finance.winport.R;

/**
 * Created by xzw on 2017/8/4.
 */

public class TradeMorePopup extends PopupWindow {
    private View.OnClickListener onDeleteListener;

    public TradeMorePopup(Context context) {
        super(context);
        init(context);
    }

    protected void init(Context context) {
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置SelectPicPopupWindow的View
//        this.setContentView(popupWindowView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体的背景
        ColorDrawable drawable = new ColorDrawable(Color.parseColor("#00000000"));
        this.setBackgroundDrawable(drawable);
        initView(context);
    }

    private void initView(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.trade_more, null);
        setContentView(contentView);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onDeleteListener != null) {
                    onDeleteListener.onClick(v);
                }
            }
        });
    }

    public void setOnDeleteListener(View.OnClickListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

}
