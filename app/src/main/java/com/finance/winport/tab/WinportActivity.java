package com.finance.winport.tab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.tab.fragment.MineWinportFragment;

public class WinportActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winport);
        pushFragment(new MineWinportFragment());
    }
}
