package com.finance.winport.dialog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.finance.winport.R;
import com.finance.winport.home.adapter.SortAdapter;
import com.finance.winport.util.LogUtil;
import com.finance.winport.util.UnitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 17/3/28.
 */

public class SortPopupView extends AnimPopup {
    SortAdapter adapter;
    Context context;
    @BindView(R.id.background)
    View background;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.mListView)
    ListView mListView;
    private List<String> mData = new ArrayList<>();

    public SortPopupView(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_sort_view, null);
        ButterKnife.bind(this, contentView);
        setContentView(contentView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
            }
        });
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        if (adapter == null) {
            adapter = new SortAdapter(context, mData);
        }
        mListView.setAdapter(adapter);

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

        ObjectAnimator stoa = ObjectAnimator.ofFloat(content, "translationY", -height, 0);
        stoa.setDuration(200);
        ObjectAnimator saoa = ObjectAnimator.ofFloat(background, "alpha", 0, 1);
        saoa.setDuration(300);
        showSet.playTogether(stoa, saoa);
        setShowAnim(showSet);

        ObjectAnimator dtoa = ObjectAnimator.ofFloat(content, "translationY", 0, -height);
        dtoa.setDuration(300);
        ObjectAnimator daoa = ObjectAnimator.ofFloat(background, "alpha", 1, 0);
        saoa.setDuration(300);
        dismissSet.playTogether(dtoa, daoa);
        setDismissAnim(dismissSet);
    }

}
