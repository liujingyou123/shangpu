package com.finance.winport.view.refreshview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

import com.finance.winport.view.refreshview.indicator.PtrIndicator;

/**
 * Created by wangzg on 2017/5/4.
 */
public final class XPtrHeaderFooter extends FrameLayout implements PtrUIHandler {
    private static final String color1 = "#FFB632";
    private static final String color2 = "#5A54D7";

    private View dot;
    private LayoutParams layoutParams;
    private boolean refreshing;

    private ScaleAnimation scaleToSmallAnim;
    private ScaleAnimation scaleToBigAnim;

    private ShapeDrawable shapeDrawable1;
    private ShapeDrawable shapeDrawable2;
    private ShapeDrawable preShapeDrawable;

    public XPtrHeaderFooter(Context context) {
        super(context);
        init();
    }

    public XPtrHeaderFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XPtrHeaderFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public XPtrHeaderFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        XPtrConstants.init();

        OvalShape ovalShape = new OvalShape();
        shapeDrawable1 = new ShapeDrawable(ovalShape);
        shapeDrawable1.getPaint().setColor(Color.parseColor(color1));
        shapeDrawable1.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);

        shapeDrawable2 = new ShapeDrawable(ovalShape);
        shapeDrawable2.getPaint().setColor(Color.parseColor(color2));
        shapeDrawable2.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);

        scaleToSmallAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleToSmallAnim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (refreshing) {
                    dot.startAnimation(scaleToBigAnim);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        scaleToSmallAnim.setDuration(XPtrConstants.ANIM_TIME_MS);
        scaleToSmallAnim.setFillAfter(true);
        scaleToSmallAnim.setInterpolator(new AccelerateDecelerateInterpolator());

        scaleToBigAnim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleToBigAnim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                preShapeDrawable = preShapeDrawable == shapeDrawable1 ? shapeDrawable2 : shapeDrawable1;
                dot.setBackground(preShapeDrawable);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (refreshing) {
                    dot.startAnimation(scaleToSmallAnim);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        scaleToBigAnim.setDuration(XPtrConstants.ANIM_TIME_MS);
        scaleToBigAnim.setFillAfter(true);
        scaleToBigAnim.setInterpolator(new AccelerateDecelerateInterpolator());

        dot = new View(getContext());
        layoutParams = new LayoutParams(XPtrConstants.MIN_DOT_PX, XPtrConstants.MIN_DOT_PX, Gravity.CENTER);
        layoutParams.topMargin = XPtrConstants.DOT_MARGIN_PX;
        layoutParams.bottomMargin = XPtrConstants.DOT_MARGIN_PX;
        dot.setLayoutParams(layoutParams);
        dot.setBackground(shapeDrawable1);
        preShapeDrawable = shapeDrawable1;
        addView(dot);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        layoutParams.width = layoutParams.height = XPtrConstants.MIN_DOT_PX;
        dot.setLayoutParams(layoutParams);
        dot.setBackground(shapeDrawable1);
        preShapeDrawable = shapeDrawable1;
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        layoutParams.width = layoutParams.height = XPtrConstants.MIN_DOT_PX;
        dot.setLayoutParams(layoutParams);
        dot.setBackground(shapeDrawable1);
        preShapeDrawable = shapeDrawable1;
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        refreshing = true;
        layoutParams.width = layoutParams.height = XPtrConstants.MAX_DOT_PX;
        dot.setLayoutParams(layoutParams);
        scaleToSmallAnim.cancel();
        scaleToBigAnim.cancel();
        dot.clearAnimation();
        dot.startAnimation(scaleToSmallAnim);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
        refreshing = false;
        scaleToSmallAnim.cancel();
        scaleToBigAnim.cancel();
        dot.clearAnimation();
        dot.postInvalidate();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int currentPos = ptrIndicator.getCurrentPosY();
        int dif = currentPos - XPtrConstants.DOT_MARGIN_PX;
        if (!refreshing && dif > XPtrConstants.MIN_DOT_PX && dif < XPtrConstants.MAX_DOT_PX) {
            layoutParams.width = layoutParams.height = dif;
            dot.setLayoutParams(layoutParams);
        }
    }
}