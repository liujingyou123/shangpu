package com.finance.winport.view.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.finance.winport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/5/18.
 */

public class NearShopView extends LinearLayout {
    @BindView(R.id.tv_linpuweizhi)
    ItemView tvLinpuweizhi;
    @BindView(R.id.tv_linpumingcheng)
    ItemView tvLinPuMingcheng;
    @BindView(R.id.tv_suoshuyetai)
    ItemView tvSuoshuyetai;

    public NearShopView(Context context) {
        super(context);
        init(context);
    }

    public NearShopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NearShopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.view_near_shop, this);
        ButterKnife.bind(this, this);
    }

    public void setLocation(String text) {
        tvLinpuweizhi.setLableTwo(text);
    }

    public void setShopName(String text) {
        tvLinPuMingcheng.setLableTwo(text);
    }

    public void setShopYeTai(String text) {
        tvSuoshuyetai.setLableTwo(text);
    }

}
