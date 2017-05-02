package com.finance.winport.account;

import android.os.Bundle;

import com.finance.winport.R;
import com.finance.winport.account.fragment.LoginFragment;
import com.finance.winport.base.BaseActivity;


public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pushFragment(new LoginFragment());
    }

}
