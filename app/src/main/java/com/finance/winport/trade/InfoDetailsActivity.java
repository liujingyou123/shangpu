package com.finance.winport.trade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseFragment;

public class InfoDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        BaseFragment fragment = new NewsDetailsFragment();
        fragment.setArguments(getIntent().getExtras());
        pushFragment(fragment);
    }
}
