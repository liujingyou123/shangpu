package com.finance.winport.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.NoticeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ScheduleDetailActivity extends BaseActivity {


    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.shop_img)
    ImageView shopImg;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.btn_done)
    TextView btnDone;
    private NoticeDialog mNoticeDialog;//拨打电话弹框
    private NoticeDialog nNoticeDialog;//撤销服务弹框
    private NoticeDialog bNoticeDialog;//确认服务弹框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);
        ButterKnife.bind(this);
        init();
    }


    public void init() {
    }

    @OnClick({R.id.imv_focus_house_back, R.id.cancel, R.id.phone, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.cancel:
                if (nNoticeDialog == null) {
                    nNoticeDialog = new NoticeDialog(this);
                    nNoticeDialog.setMessage("撤销服务");
                    nNoticeDialog.setPositiveBtn("确认");
                }
                if (!nNoticeDialog.isShowing()) {
                    nNoticeDialog.show();
                }
                break;
            case R.id.phone:
                if (mNoticeDialog == null) {
                    mNoticeDialog = new NoticeDialog(this);
                    mNoticeDialog.setMessage("小二电话：130 2324 0800");
                    mNoticeDialog.setPositiveBtn("拨打");
                }
                if (!mNoticeDialog.isShowing()) {
                    mNoticeDialog.show();
                }
                break;
            case R.id.btn_done:
                if (bNoticeDialog == null) {
                    bNoticeDialog = new NoticeDialog(this);
                    bNoticeDialog.setMessage("请确认小二已完成对您的任务");
                    bNoticeDialog.setPositiveBtn("好的");
                }
                if (!bNoticeDialog.isShowing()) {
                    bNoticeDialog.show();
                }
                break;
        }
    }

}
