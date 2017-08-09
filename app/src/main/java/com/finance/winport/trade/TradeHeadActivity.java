package com.finance.winport.trade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;

public class TradeHeadActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_head);
        TradeType type = (TradeType) getIntent().getSerializableExtra("type");
        handleType(type);

    }

    private void handleType(TradeType type) {
        switch (type) {
            case HEAD:
                pushFragment(new TradeHeadFragment());
                break;
            case BIBLE:
                pushFragment(new TradeBibleFragment());
                break;
            case CIRCLE:
                pushFragment(new TradeCircleFragment());
                break;

        }
    }
}
