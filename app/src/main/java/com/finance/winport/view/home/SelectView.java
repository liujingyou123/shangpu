package com.finance.winport.view.home;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.finance.winport.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/3.
 * 筛选
 */

public class SelectView extends LinearLayout {

    @BindView(R.id.rl_location_s)
    RelativeLayout tvLocationS;
    @BindView(R.id.rl_sort_s)
    RelativeLayout rlSortS;
    @BindView(R.id.rl_c_s)
    RelativeLayout rlCS;

    public SelectView(Context context) {
        super(context);
        init(context);
    }

    public SelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_select, this);
        ButterKnife.bind(this, this);
        this.setGravity(Gravity.CENTER);
        this.setBackgroundColor(Color.parseColor("#ffffff"));
        this.setOrientation(LinearLayout.VERTICAL);
    }

    @OnClick({R.id.rl_location_s, R.id.rl_sort_s, R.id.rl_c_s})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_location_s:
                break;
            case R.id.rl_sort_s:
                break;
            case R.id.rl_c_s:
                break;
        }
    }
}
