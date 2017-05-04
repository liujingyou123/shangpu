package com.finance.winport.service;

import android.os.Bundle;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.service.fragment.ShopOrderFragment;
import com.finance.winport.service.fragment.ShopRentFragment;


public class ShopOrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_rent);
        pushFragment(new ShopOrderFragment());
    }

}
