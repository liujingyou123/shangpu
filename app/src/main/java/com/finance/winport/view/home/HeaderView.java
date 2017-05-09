package com.finance.winport.view.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.finance.winport.R;
import com.finance.winport.view.StopWatchTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/5/3.
 * 首页头部
 */

public class HeaderView extends RelativeLayout {
    @BindView(R.id.tv_today_shop)
    StopWatchTextView tvTodayShop;
    @BindView(R.id.ll_today)
    LinearLayout llToday;
    @BindView(R.id.ll_nomeney)
    LinearLayout llNomeney;
    @BindView(R.id.ll_baiping)
    LinearLayout llBaiping;
    @BindView(R.id.ll_nears)
    LinearLayout llNears;

    public HeaderView(Context context) {
        super(context);
        init(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_home_header, this);
        ButterKnife.bind(this, this);

    }

    public void setTodayShop(float number) {
        tvTodayShop.setShowNumber(number);
    }

    public void setNewShopsListener(OnClickListener onClickListener) {
        llToday.setOnClickListener(onClickListener);
    }

    public void setNoMenoyListener(OnClickListener onClickListener) {
        llNomeney.setOnClickListener(onClickListener);
    }

    public void setSmallShopListener(OnClickListener onClickListener) {
        llBaiping.setOnClickListener(onClickListener);
    }

    public void setNearMetroListener(OnClickListener onClickListener) {
        llNears.setOnClickListener(onClickListener);
    }
}
