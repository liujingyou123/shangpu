package com.finance.winport.view.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.finance.winport.R;

/**
 * Created by liuworkmac on 17/5/3.
 * 筛选
 */

public class SelectView extends LinearLayout {
    public SelectView(Context context) {
        super(context);
    }

    public SelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_select, this);
    }
}
