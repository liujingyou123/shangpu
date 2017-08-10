package com.finance.winport.trade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;

public class InfoDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        pushFragment(new NewsDetailsFragment());
    }
}
