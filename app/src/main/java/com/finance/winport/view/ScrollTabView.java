package com.finance.winport.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.log.XLog;
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
    @BindView(R.id.tv_yingyufanwei)
    TextView tvYingyufanwei;
    @BindView(R.id.view_hs)
    PositionHScrollView viewHs;
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

        init();

    }

    private void init() {
        viewHs.setOnScrollChangedListener(new PositionHScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                viewSelectTab.setX(getOffsetByIndex(selectIndex));
            }
        });

    }


    public void setSelectPosition(int position) {
        Log.e("position", "s = " + selectIndex + " po" + position);
        if (viewSelectTab.getVisibility() == View.GONE) {
            viewSelectTab.setVisibility(View.VISIBLE);

            if (selectIndex == 0 && position == 0) {
                viewSelectTab.setX(55);
                tvZhoubian.setSelected(true);
            }
        }
        if (selectIndex != position) {
            selectIndex = position;
            tvZhoubian.setSelected(false);
            tvLinpu.setSelected(false);
            tvMienmian.setSelected(false);
            tvYingyufeiyong.setSelected(false);
            tvPeitaosheshi.setSelected(false);
            tvYingyufanwei.setSelected(false);

            if (position == 0) {
                tvZhoubian.setSelected(true);
            } else if (position == 1) {
                tvLinpu.setSelected(true);
            } else if (position == 2) {
                tvMienmian.setSelected(true);
            } else if (position == 3) {
                tvYingyufanwei.setSelected(true);
            } else if (position == 4) {
                tvYingyufeiyong.setSelected(true);
            } else if (position == 5) {
                tvPeitaosheshi.setSelected(true);
            }

            setSeletViewTab(position);
        }
    }

    public void setLinpuGone() {
        tvLinpu.setVisibility(View.GONE);
    }

    public void setYingYuFeiyongGone() {
        tvYingyufeiyong.setVisibility(View.GONE);
    }

    public void setPeitaoGone() {
        tvPeitaosheshi.setVisibility(View.GONE);
    }

    public void setJingYingFanWeiGone() {
        tvYingyufanwei.setVisibility(View.GONE);
    }


    private int getOffsetByIndex(int index) {
        View subView = viewTabs.getChildAt(index);

        int[] location = new int[2];
        subView.getLocationOnScreen(location);
        int x = location[0];
        int offset = 0;


        if (subView != null) {
            int width = subView.getWidth();
            int tabWidth = viewSelectTab.getWidth();
            offset = (width - tabWidth) / 2;
        }

        return (x + offset);
    }

    private void setSeletViewTab(int index) {
        View subView = viewTabs.getChildAt(index);
        if (subView != null) {
            viewSelectTab.setPivotY(0);
            viewSelectTab.setPivotX(0);

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewSelectTab, "translationX", viewSelectTab.getTranslationX(), getOffsetByIndex(index));
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimator.setDuration(300);
            objectAnimator.start();
        }
    }

    @OnClick({R.id.tv_zhoubian, R.id.tv_linpu, R.id.tv_mienmian, R.id.tv_yingyufeiyong, R.id.tv_peitaosheshi, R.id.tv_yingyufanwei})
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
            case R.id.tv_yingyufanwei:
                setSelectPosition(3);
                if (mOnSelectViewListener != null) {
                    mOnSelectViewListener.onSelectViewPosition(3);
                }
                break;
            case R.id.tv_yingyufeiyong:
                setSelectPosition(4);
                if (mOnSelectViewListener != null) {
                    mOnSelectViewListener.onSelectViewPosition(4);
                }
                break;
            case R.id.tv_peitaosheshi:
                setSelectPosition(5);
                if (mOnSelectViewListener != null) {
                    mOnSelectViewListener.onSelectViewPosition(5);
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
