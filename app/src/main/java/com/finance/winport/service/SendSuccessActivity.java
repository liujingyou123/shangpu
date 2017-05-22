package com.finance.winport.service;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SendSuccessActivity extends BaseActivity {


    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rl_title_root)
    RelativeLayout rlTitleRoot;
    @BindView(R.id.empty_tips)
    ImageView emptyTips;
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.txt_tip)
    TextView txtTip;
    @BindView(R.id.btn_done)
    TextView btnDone;
    @BindView(R.id.empty)
    RelativeLayout empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_success);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvFocusHouse.setText("发布成功");
        imvFocusHouseBack.setVisibility(View.GONE);

    }


    @OnClick(R.id.btn_done)
    public void onViewClicked() {
    }
}
