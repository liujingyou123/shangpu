package com.finance.winport.view.home;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.adapter.LocationAdapter;
import com.finance.winport.util.UnitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/5/4.
 * 选择
 */

public class ChoiceView extends RelativeLayout {
    @BindView(R.id.sl_view)
    SelectView slView;
    @BindView(R.id.tv_quyu)
    TextView tvQuyu;
    @BindView(R.id.tv_ditie)
    TextView tvDitie;
    @BindView(R.id.ls_one)
    ListView lsOne;
    @BindView(R.id.ls_two)
    ListView lsTwo;
    @BindView(R.id.ll_weizhi)
    LinearLayout llWeizhi;
    @BindView(R.id.view_bg)
    View viewBg;

    private Context mContext;
    private LocationAdapter locationOneAdapter;
    private List<String> listOne = new ArrayList<>();

    private LocationAdapter locationTwoAdapter;
    private List<String> listTwo = new ArrayList<>();

    public ChoiceView(Context context) {
        super(context);
        init(context);
    }

    public ChoiceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChoiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_choice, this);
        ButterKnife.bind(this, this);

        if (locationOneAdapter == null) {
            locationOneAdapter = new LocationAdapter(context, listOne);
        }
        lsOne.setAdapter(locationOneAdapter);

        if (locationTwoAdapter == null) {
            locationTwoAdapter = new LocationAdapter(context, listTwo);
        }
        lsTwo.setAdapter(locationTwoAdapter);
    }


    /**
     * 位置
     */
    public void showLocation() {
        slView.onLocationClick();
        llWeizhi.setVisibility(View.VISIBLE);
        llWeizhi.setPivotX(0f);
        llWeizhi.setPivotY(0f);
        ObjectAnimator alphaObject = ObjectAnimator.ofFloat(viewBg, "alpha", 0, 1);
        ObjectAnimator transObject = ObjectAnimator.ofFloat(llWeizhi,"translationY", -UnitUtil.dip2px(mContext, 356), 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaObject, transObject);
        animatorSet.setStartDelay(100);
        animatorSet.setDuration(500);
        animatorSet.start();
    }
}
