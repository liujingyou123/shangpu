package com.finance.winport.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.service.fragment.SendShopOrderFragment;

/**
 * Created by liuworkmac on 17/5/9.
 */

public class OrderShopActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordershop);
        pushFragment(new SendShopOrderFragment());

    }
}
