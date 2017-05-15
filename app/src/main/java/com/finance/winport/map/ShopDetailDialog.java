package com.finance.winport.map;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.view.dialog.BottomDialog;
import com.finance.winport.view.tagview.TagCloudLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by css_double on 17/5/4.
 */

public class ShopDetailDialog extends BottomDialog {


    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tv_shopname)
    TextView tvShopname;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.line_tv)
    View lineTv;
    @BindView(R.id.view2)
    TagCloudLayout view2;
    @BindView(R.id.textView10)
    View textView10;

    public ShopDetailDialog(@NonNull Context context, String msg) {
        super(context);
        setContentView(R.layout.shop_list_item);
        ButterKnife.bind(this);

        tvShopname.setText(msg);
    }
}
