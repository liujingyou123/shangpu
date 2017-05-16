package com.finance.winport.tab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.tab.fragment.PredictionFragment;
import com.finance.winport.tab.fragment.PredictionResultFragment;

public class FiercePredictionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fierce_prediction);
        pushFragment(new PredictionFragment());
    }
}
