package com.finance.winport.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;

/**
 * Created by xzw on 17/4/20.
 */

public class AnimPopup extends CommonPopup {
    AnimatorSet showAnim;
    AnimatorSet dismissAnim;
    protected Context context;

    public AnimPopup(Context context) {
        super(context);
        this.context = context;
        initAnim();
    }

    public AnimPopup(View contentView, int width, int height) {
        super(contentView, width, height);
        initAnim();
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        startShowAnim();
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        startShowAnim();
    }

    private void initAnim() {
        //去掉默认动画
        setAnimationStyle(0);
    }

    @Override
    public void dismiss() {
        if (dismissAnim != null) {
            dismissAnim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    AnimPopup.super.dismiss();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            startDismissAnim();
        }
    }

    private void startShowAnim() {
        if (showAnim != null) {
            showAnim.start();
        }
    }

    private void startDismissAnim() {
        if (dismissAnim != null) {
            dismissAnim.start();
        }
    }

    public void setShowAnim(AnimatorSet showAnim) {
        this.showAnim = showAnim;
    }

    public void setDismissAnim(AnimatorSet dismissAnim) {
        this.dismissAnim = dismissAnim;
    }
}
