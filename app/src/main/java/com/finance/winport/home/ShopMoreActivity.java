package com.finance.winport.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/8.
 * 更多
 */

public class ShopMoreActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopmore);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }
}
