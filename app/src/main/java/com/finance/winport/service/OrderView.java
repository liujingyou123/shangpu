package com.finance.winport.service;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.finance.winport.R;

/**
 * @author xzw
 */
public class OrderView extends FrameLayout {


    private ImageView view1;
    private ImageView view2;
    private Handler handler;
    public OrderView(Context context) {
        super(context);
        init(context);
    }

    public OrderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OrderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context);
    }

    private void init(final Context context) {
        handler = new Handler();
        LayoutInflater.from(context).inflate(R.layout.service_order_view, this, true);
        view1 = (ImageView) findViewById(R.id.img2);
        view2 = (ImageView) findViewById(R.id.img3);
        view1.setVisibility(View.GONE);
        view2.setVisibility(View.GONE);

    }

    public void start(){

        Log.i("banner start","OrderView startt 8888888888888");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);


                ObjectAnimator animator = new ObjectAnimator().ofFloat(view1, "translationX", 1500f,  0f);
                animator.setDuration(1800);
                animator.setInterpolator(new LinearInterpolator());
                Log.i("banner start","OrderView startt 9999999999999");
                animator.start();

                ObjectAnimator animator1 = new ObjectAnimator().ofFloat(view2, "translationX", 1700f,  1f);
                animator1.setDuration(2800);
                animator1.setInterpolator(new LinearInterpolator());
                animator1.start();

            }
        },500);
    }


}
