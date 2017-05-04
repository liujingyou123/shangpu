package com.finance.winport.service;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.finance.winport.R;

/**
 * @author xzw
 */
public class FindMoneyView extends FrameLayout {


    private ImageView view1;
    private ImageView view2;
    public FindMoneyView(Context context) {
        super(context);
        init(context);
    }

    public FindMoneyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FindMoneyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context);
    }

    private void init(final Context context) {
        LayoutInflater.from(context).inflate(R.layout.service_money_view, this, true);
        view1 = (ImageView) findViewById(R.id.img1);
        view2 = (ImageView) findViewById(R.id.img2);
//        start();
    }

//    public void start(){
//        ObjectAnimator animator = new ObjectAnimator().ofFloat(view1, "translationX", 1f,  0f);
//        animator.setDuration(800);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.start();
//
//        ObjectAnimator animator1 = new ObjectAnimator().ofFloat(view2, "translationX", 1f,  0f);
//        animator1.setDuration(1800);
//        animator1.setInterpolator(new LinearInterpolator());
//        animator1.start();
//    }

}
