package com.finance.winport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by liuworkmac on 17/5/18.
 */

public class PositionHScrollView extends HorizontalScrollView {
    private OnScrollChangedListener mOnScrollChangedListener;

    public PositionHScrollView(Context context) {
        super(context);
    }

    public PositionHScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PositionHScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.mOnScrollChangedListener = onScrollChangedListener;
    }


    public interface OnScrollChangedListener{
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
