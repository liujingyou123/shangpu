package com.finance.winport.service;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
        LayoutInflater.from(context).inflate(R.layout.service_order_view, this, true);
        view1 = (ImageView) findViewById(R.id.img1);
        view2 = (ImageView) findViewById(R.id.img2);
    }


}
