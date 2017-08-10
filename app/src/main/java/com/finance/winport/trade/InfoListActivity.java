package com.finance.winport.trade;

import android.os.Bundle;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseFragment;

public class InfoListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        TradeType type = (TradeType) getIntent().getSerializableExtra("type");
        handleType(type);

    }

    private void handleType(TradeType type) {
        BaseFragment fragment = null;
        switch (type) {
            case HEAD_LIST:
                fragment = new NewsListFragment();
                break;
            case BIBLE_LIST:
                fragment = new BibleListFragment();
                break;

        }
        if (fragment != null) {
            fragment.setArguments(getIntent().getExtras());
            pushFragment(fragment);
        }
    }
}
