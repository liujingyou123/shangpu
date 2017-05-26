package com.finance.winport.service;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.mine.ScheduleDetailActivity;

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
    private String scheduleId;

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
        scheduleId = getIntent().getStringExtra("scheduleId");
        if(TextUtils.isEmpty(scheduleId)){
            btnDone.setText("回到服务");
        }

    }


    @OnClick(R.id.btn_done)
    public void onViewClicked() {

        if(TextUtils.isEmpty(scheduleId)){
            startActivity(new Intent(SendSuccessActivity.this,FindLoanActivity.class));
            finish();
        }else{

            Intent intent = new Intent(SendSuccessActivity.this,ScheduleDetailActivity.class);
            intent.putExtra("scheduleId",scheduleId);
            startActivity(intent);
        }
    }
}
