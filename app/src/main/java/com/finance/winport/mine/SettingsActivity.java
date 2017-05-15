package com.finance.winport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.account.LoginActivity;
import com.finance.winport.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingsActivity extends BaseActivity {


    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rl_title_root)
    RelativeLayout rlTitleRoot;
    @BindView(R.id.version)
    TextView version;
    @BindView(R.id.login_out)
    Button loginOut;
    @BindView(R.id.debug)
    Button debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        init();
    }


    public void init(){
        tvFocusHouse.setText("系统设置");
    }

    @OnClick({R.id.imv_focus_house_back, R.id.login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.login_out:
                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
                break;
        }
    }
}
