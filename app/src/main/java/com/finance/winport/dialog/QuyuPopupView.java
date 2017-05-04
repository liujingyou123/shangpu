package com.finance.winport.dialog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.adapter.LocationAdapter;
import com.finance.winport.util.LogUtil;
import com.finance.winport.util.UnitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/5/4.
 */

public class QuyuPopupView extends AnimPopup {

    private Context context;
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
    @BindView(R.id.background)
    View viewBg;

    private LocationAdapter locationOneAdapter;
    private List<String> listOne = new ArrayList<>();

    private LocationAdapter locationTwoAdapter;
    private List<String> listTwo = new ArrayList<>();

    public QuyuPopupView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_quyu_view, null);
        ButterKnife.bind(this, contentView);
        setContentView(contentView);

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        if (locationOneAdapter == null) {
            locationOneAdapter = new LocationAdapter(context, listOne);
        }
        lsOne.setAdapter(locationOneAdapter);

        if (locationTwoAdapter == null) {
            locationTwoAdapter = new LocationAdapter(context, listTwo);
        }
        lsTwo.setAdapter(locationTwoAdapter);
    }

    @Override
    public void showAsDropDown(View anchor) {
        initWindowAttribute(anchor);
        super.showAsDropDown(anchor);
    }

    private void initWindowAttribute(View anchor) {
        int[] point = new int[2];
        anchor.getLocationOnScreen(point);

        LogUtil.e("pointY = "+point[1]);
        this.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.transparent));
        setWidth(context.getResources().getDisplayMetrics().widthPixels);
        int height = UnitUtil.getScreenHeightPixels(context) - point[1] - anchor.getHeight();
        LogUtil.e("height = "+height);

        setHeight(height);
        initAnim(UnitUtil.getScreenHeightPixels(context));
    }

    private void initAnim(int height) {

        LogUtil.e("initAnim height = "+height);

        AnimatorSet showSet = new AnimatorSet();
        AnimatorSet dismissSet = new AnimatorSet();

        ObjectAnimator stoa = ObjectAnimator.ofFloat(llWeizhi, "translationY", -height, 0);
        stoa.setDuration(200);
        ObjectAnimator saoa = ObjectAnimator.ofFloat(viewBg, "alpha", 0, 1);
        saoa.setDuration(300);
        showSet.playTogether(stoa, saoa);
        setShowAnim(showSet);

        ObjectAnimator dtoa = ObjectAnimator.ofFloat(llWeizhi, "translationY", 0, -height);
        dtoa.setDuration(300);
        ObjectAnimator daoa = ObjectAnimator.ofFloat(viewBg, "alpha", 1, 0);
        saoa.setDuration(300);
        dismissSet.playTogether(dtoa, daoa);
        setDismissAnim(dismissSet);
    }
}
