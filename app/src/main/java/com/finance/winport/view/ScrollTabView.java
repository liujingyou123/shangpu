package com.finance.winport.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.util.UnitUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/5.
 */

public class ScrollTabView extends LinearLayout {
    @BindView(R.id.tv_zhoubian)
    TextView tvZhoubian;
    @BindView(R.id.tv_linpu)
    TextView tvLinpu;
    @BindView(R.id.tv_mienmian)
    TextView tvMienmian;
    @BindView(R.id.tv_yingyufeiyong)
    TextView tvYingyufeiyong;
    @BindView(R.id.tv_peitaosheshi)
    TextView tvPeitaosheshi;
    @BindView(R.id.view_tabs)
    LinearLayout viewTabs;
    @BindView(R.id.view_select_tab)
    View viewSelectTab;
    private Context mContext;

    private int selectIndex;

    private OnSelectViewListener mOnSelectViewListener;

    public ScrollTabView(Context context) {
        super(context);
        initView(context);
    }

    public ScrollTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ScrollTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        this.mContext = context;
        setOrientation(LinearLayout.VERTICAL);

        LayoutInflater.from(context).inflate(R.layout.view_scrolltabview, this);
        ButterKnife.bind(this, this);

        tvZhoubian.setSelected(true);

    }

//    /**
//     * 设施选中到text
//     */
//    private void setSelectView(View view) {
//        tvZhoubian.setSelected(false);
//        tvLinpu.setSelected(false);
//        tvMienmian.setSelected(false);
//        tvYingyufeiyong.setSelected(false);
//        tvPeitaosheshi.setSelected(false);
//
//        view.setSelected(true);
//    }

    public void setSelectPosition(int position) {
        Log.e("position", "s = " + selectIndex + " po" + position);
        if (selectIndex != position) {
            selectIndex = position;
            tvZhoubian.setSelected(false);
            tvLinpu.setSelected(false);
            tvMienmian.setSelected(false);
            tvYingyufeiyong.setSelected(false);
            tvPeitaosheshi.setSelected(false);

            if (position == 0) {
                tvZhoubian.setSelected(true);
            } else if (position == 1) {
                tvLinpu.setSelected(true);
            } else if (position == 2) {
                tvMienmian.setSelected(true);
            } else if (position == 3) {
                tvYingyufeiyong.setSelected(true);
            } else if (position == 4) {
                tvPeitaosheshi.setSelected(true);
            }

            setSeletViewTab(position);
        }

    }

    private void setSeletViewTab(int index) {
        View subView = viewTabs.getChildAt(index);
        if (subView != null) {
            int left = subView.getLeft();
            int right = subView.getRight();
            int centerPointX = (left + right) / 2;

            int seletViewHelftWidth = UnitUtil.dip2px(mContext, 18) / 2;
            int seletViewLeft = centerPointX - seletViewHelftWidth;

            viewSelectTab.setPivotY(0);
            viewSelectTab.setPivotX(0);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewSelectTab, "translationX", viewSelectTab.getTranslationX(), seletViewLeft - UnitUtil.dip2px(mContext, 18));
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimator.setDuration(300);
            objectAnimator.start();
        }
    }

    @OnClick({R.id.tv_zhoubian, R.id.tv_linpu, R.id.tv_mienmian, R.id.tv_yingyufeiyong, R.id.tv_peitaosheshi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zhoubian:
                setSelectPosition(0);
                if (mOnSelectViewListener != null) {
                    mOnSelectViewListener.onSelectViewPosition(0);
                }
                break;
            case R.id.tv_linpu:
                setSelectPosition(1);
                if (mOnSelectViewListener != null) {
                    mOnSelectViewListener.onSelectViewPosition(1);
                }
                break;
            case R.id.tv_mienmian:
                setSelectPosition(2);
                if (mOnSelectViewListener != null) {
                    mOnSelectViewListener.onSelectViewPosition(2);
                }
                break;
            case R.id.tv_yingyufeiyong:
                setSelectPosition(3);
                if (mOnSelectViewListener != null) {
                    mOnSelectViewListener.onSelectViewPosition(3);
                }
                break;
            case R.id.tv_peitaosheshi:
                setSelectPosition(4);
                if (mOnSelectViewListener != null) {
                    mOnSelectViewListener.onSelectViewPosition(4);
                }
                break;
        }
    }

    public void setOnSelectViewListener(OnSelectViewListener onSelectViewListener) {
        this.mOnSelectViewListener = onSelectViewListener;
    }

    public interface OnSelectViewListener {
        void onSelectViewPosition(int position);
    }
}
