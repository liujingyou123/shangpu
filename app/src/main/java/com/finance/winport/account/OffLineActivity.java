package com.finance.winport.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.finance.winport.MainActivity;
import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OffLineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_line);
        ButterKnife.bind(this);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @OnClick({R.id.login, R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                overridePendingTransition(R.anim.open_enter_bottom_in, R.anim.open_exit_normal);
                break;
            case R.id.close:
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(0, 0);
                break;
        }
    }
}
