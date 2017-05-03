package com.finance.winport.view.BannerView;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.finance.winport.R;


public class ScrollBannerView extends FrameLayout {
    private static final long DURATION_STAY = 3000;

    private MyViewPager adViewPager;
    private LinearLayout mIndicatorContainer;
    private TextView tvSign;

    private Handler mainHandler;
    private int mIndicatorRes = 0;

    private int totalCount = 0;

    public ScrollBannerView(Context context) {
        super(context);
        init(context);
    }

    public ScrollBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollBannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context);
    }

    /**
     * 设置布局
     */
    private void init(Context context) {
        mainHandler = new Handler();

        LayoutInflater.from(context).inflate(R.layout.banner, this, true);
        mIndicatorContainer = (LinearLayout) findViewById(R.id.view_con);
        adViewPager = (MyViewPager) findViewById(R.id.viewpager);
        tvSign = (TextView) findViewById(R.id.tv_sign);
        adViewPager.addOnPageChangeListener(new MyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int position) {
                setIndicatorRes(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        adViewPager.setOnViewPagerTouchEventListener(new MyViewPager.OnViewPagerTouchEvent() {
            @Override
            public void onTouchDown() {
                cancelScroll();
            }

            @Override
            public void onTouchUp() {
//                startScroll();
            }
        });
    }

    public void startScroll() {
        //没有数据不启动轮播
        PagerAdapter pagerAdapter = adViewPager.getAdapter();
        if (pagerAdapter == null || pagerAdapter.getCount() == 0) {
            return;
        }

        mainHandler.removeCallbacksAndMessages(null);
        mainHandler.postDelayed(autoScroll, DURATION_STAY);
    }

    public void cancelScroll() {
        mainHandler.removeCallbacksAndMessages(null);
    }

    private Runnable autoScroll = new Runnable() {
        @Override
        public void run() {
            int nextItemPos = (adViewPager.getCurrentItem() + 1) % adViewPager.getAdapter().getCount();
            adViewPager.setCurrentItem(nextItemPos);
            mainHandler.postDelayed(this, DURATION_STAY);
        }
    };

    public void setBannerAdapter(PagerAdapter bannerAdapter) {
        adViewPager.setOffscreenPageLimit(2);
        initIndicator(bannerAdapter.getCount());
        adViewPager.setAdapter(bannerAdapter);
    }

    public void setBannerAdapter(PagerAdapter bannerAdapter, Boolean showDot) {
        adViewPager.setOffscreenPageLimit(2);
        if (showDot) {
            tvSign.setVisibility(View.GONE);
            initIndicator(bannerAdapter.getCount());
        } else {
            tvSign.setVisibility(View.VISIBLE);
            initTextView(bannerAdapter.getCount());
        }

        adViewPager.setAdapter(bannerAdapter);
    }

    public void initTextView(int count) {
        totalCount = count;
        if (count == 0) {
            tvSign.setText("");
        } else {

            tvSign.setText("1/" + count);
        }
    }

    public void initIndicator(int count) {
        tvSign.setVisibility(View.GONE);
        mIndicatorContainer.removeAllViews();
        if (count <= 1) {
            return;
        }
        for (int i = 0; i < count; i++) {
            ImageView dot = new ImageView(getContext());
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            dot.setId(i);
            dot.setImageResource(R.mipmap.oval);
            dot.setLayoutParams(params);
            mIndicatorContainer.addView(dot);
        }
        setIndicatorRes(0);
    }

    /**
     * Must be set before setBannerAdapter()
     *
     * @param resId
     */
    public void setIndicatorRes(int resId) {
        tvSign.setText((resId + 1) + "/" + totalCount);
        for (int i = 0; i < mIndicatorContainer.getChildCount(); i++) {
            if (i == resId) {
                ((ImageView) mIndicatorContainer.getChildAt(i)).setImageResource(R.mipmap.oval_checked);
            } else {
                ((ImageView) mIndicatorContainer.getChildAt(i)).setImageResource(R.mipmap.oval);
            }

        }
    }

    public void setDotMargin(int dotMargin) {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        params.bottomMargin = dotMargin;
        mIndicatorContainer.setLayoutParams(params);
    }


    public void clear() {
        if (adViewPager != null) {
            adViewPager.setAdapter(null);
            adViewPager.removeAllViews();
        }

        adViewPager = null;
    }

}
