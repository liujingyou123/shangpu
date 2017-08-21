package com.finance.winport.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 2017/8/18.
 */

public class TradeBottomPopup extends AnimPopup {
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.praise)
    TextView praise;
    @BindView(R.id.down_praise)
    TextView downPraise;

    public TradeBottomPopup(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        View contentView = LayoutInflater.from(context).inflate(R.layout.trade_detail_bottom_layout, null);
        ButterKnife.bind(this, contentView);
        setContentView(contentView);
    }

    @Override
    protected void init() {
        super.init();
        ColorDrawable background = new ColorDrawable(Color.parseColor("#000000"));
        //设置PopupWindow弹出窗体的背景
        this.setBackgroundDrawable(background);
    }

    public TradeBottomPopup setOnShareClickListener(View.OnClickListener onShareClickListener) {
        share.setOnClickListener(onShareClickListener);
        return this;
    }

    public TradeBottomPopup setOnPraiseClickListener(View.OnClickListener onPraiseClickListener) {
        praise.setOnClickListener(onPraiseClickListener);
        return this;
    }

    public TradeBottomPopup setOnDownPraiseClickListener(View.OnClickListener onDownPraiseClickListener) {
        downPraise.setOnClickListener(onDownPraiseClickListener);
        return this;
    }

}
