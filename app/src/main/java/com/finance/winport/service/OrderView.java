package com.finance.winport.service;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.finance.winport.R;

/**
 * @author xzw
 */
public class OrderView extends FrameLayout {


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
    }


}
