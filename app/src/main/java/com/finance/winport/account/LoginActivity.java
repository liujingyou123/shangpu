package com.finance.winport.account;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.finance.winport.R;
import com.finance.winport.account.fragment.LoginFragment;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseFragment;


public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pushFragment(new LoginFragment());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_enter_normal, R.anim.close_exit_bottom_out);
    }
}
