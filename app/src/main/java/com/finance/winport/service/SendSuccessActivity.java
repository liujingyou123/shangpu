package com.finance.winport.service;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.MainActivity;
import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.mine.MyServiceDetailActivity;
import com.finance.winport.mine.MyServiceListFragment;
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
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_success);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        scheduleId = getIntent().getStringExtra("scheduleId");
        type = getIntent().getIntExtra("type", -1);
        if (TextUtils.isEmpty(scheduleId)) {
            btnDone.setText("回到服务");
        }

        if(type==2||type==3){
            tvFocusHouse.setText("预约成功");
        }else {
            tvFocusHouse.setText("发布成功");
        }

    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                if (type == 1) {
                    startActivity(new Intent(SendSuccessActivity.this, ShopRentActivity.class));
                } else if (TextUtils.isEmpty(scheduleId)) {
                    startActivity(new Intent(SendSuccessActivity.this, FindLoanActivity.class));
                }
                finish();
                break;
            case R.id.btn_done:
                if (TextUtils.isEmpty(scheduleId)) {
//                    startActivity(new Intent(SendSuccessActivity.this, FindLoanActivity.class));
                    startActivity(new Intent(context, MainActivity.class).putExtra("tab", MainActivity.SERVICE));
                    finish();
                } else {

//                    Intent intent = new Intent(SendSuccessActivity.this, ScheduleDetailActivity.class);
//                    intent.putExtra("scheduleId", scheduleId);
//                    startActivity(intent);

                    int serviceType = 0;
                    if(type==1){
                        serviceType = 0;
                    }else if(type==2){
                        serviceType = 2;
                    }else if(type==3){
                        serviceType = 1;
                    }
                    Intent intent = new Intent(SendSuccessActivity.this, MyServiceDetailActivity.class);
                    intent.putExtra("id",Integer.parseInt(scheduleId));
                    intent.putExtra("type",serviceType);
                    startActivity(intent);
                }
                break;
        }
    }


}
