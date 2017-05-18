package com.finance.winport.view.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/5/18.
 */

public class RateView extends LinearLayout {
    @BindView(R.id.tv_dianfeitwo)
    TextView tvDianfeitwo;
    @BindView(R.id.tv_feiyongnotice)
    TextView tvFeiyongnotice;

    public RateView(Context context) {
        super(context);
        init(context);
    }

    public RateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.view_rate, this);
        ButterKnife.bind(this, this);
    }

    public void setNum(String num) {
        tvDianfeitwo.setText(num);
    }

    public void setNotice(String notice) {
        tvFeiyongnotice.setText(notice);
    }
}
