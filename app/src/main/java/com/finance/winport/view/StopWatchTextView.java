package com.finance.winport.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

/**
 * Created by liuworkmac on 17/5/3.
 * 码表
 */

public class StopWatchTextView extends TextView {
    public StopWatchTextView(Context context) {
        super(context);
    }

    public StopWatchTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StopWatchTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setShowNumber(float number) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this,"number", 0, number);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
    }

    public void setNumber(float number) {
        String text = (int)number + "";
        setText(text);
    }
}
