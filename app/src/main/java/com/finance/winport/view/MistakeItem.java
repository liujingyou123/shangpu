package com.finance.winport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.finance.winport.R;

/**
 * Created by liuworkmac on 17/5/9.
 */

public class MistakeItem extends RelativeLayout {

    private Context mContext;
    public MistakeItem(Context context) {
        super(context);
        init(context);
    }

    public MistakeItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_mistakeitem, this);
    }
}
