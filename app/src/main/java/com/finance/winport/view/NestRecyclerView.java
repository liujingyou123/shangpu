package com.finance.winport.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.finance.winport.R;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrFrameLayout;

/**
 * Created by xzw on 2017/8/7.
 */

public class NestRecyclerView extends RecyclerView {
    public NestRecyclerView(Context context) {
        super(context);
    }

    public NestRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    int mX, mY;
    View nestView;
    PtrFrameLayout.Mode mode;
    PtrClassicFrameLayout parent;

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        parent = (PtrClassicFrameLayout) getParent();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mode = parent.getMode();
                mX = (int) e.getRawX();
                mY = (int) e.getRawY();
                if (isTouchPointInView(nestView, mX, mY)) {
                    if (parent != null) {
                        parent.setMode(PtrFrameLayout.Mode.NONE);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (parent != null) {
                    parent.setMode(mode);
                }
                break;

        }
        return super.dispatchTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (e.getRawX() - mX);
                int dy = (int) (e.getRawY() - mY);
                if (Math.abs(dx) > Math.abs(dy) && Math.abs(dx) > ViewConfiguration.getTouchSlop()) {
                    if (parent != null) {
                        parent.setMode(PtrFrameLayout.Mode.NONE);
                    }
                    return false;
                } else {
                    if (Math.abs(dy) > ViewConfiguration.getTouchSlop()) {
                        if (parent != null) {
                            parent.setMode(mode);
                        }
                    }
                }

        }
        return super.onInterceptTouchEvent(e);
    }

    public void setNestScrollView(View view) {
        this.nestView = view;
    }

    private boolean isTouchPointInView(View view, int x, int y) {
        if (view == null) {
            view = findViewById(R.id.header);
            if (view == null) {
                return false;
            }
        }
        Rect bounds = new Rect();
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        bounds.set(left, top, right, bottom);
        if (bounds.contains(x, y)) {
            return true;
        }
        return false;
    }
}
