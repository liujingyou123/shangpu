package com.finance.winport.view.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/5/17.
 */

public class NearShop extends LinearLayout {

    @BindView(R.id.imv_near_shop)
    ImageView imvNearShop;
    @BindView(R.id.tv_near_shop)
    TextView tvNearShop;

    public NearShop(Context context) {
        super(context);
        init(context);
    }

    public NearShop(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NearShop(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.neb_shop, this);
        ButterKnife.bind(this,this);
    }

    public void setShopName(String text) {
        tvNearShop.setText(text);
    }

    public void setIsOwnShop() {
        imvNearShop.setImageResource(R.mipmap.icon_our_shop);
    }
}
