package com.finance.winport.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.NoticeDialog;
import com.finance.winport.mine.model.ScheduleDetailResponse;
import com.finance.winport.mine.presenter.IScheduleDetailView;
import com.finance.winport.mine.presenter.ScheduleDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ScheduleDetailActivity extends BaseActivity implements IScheduleDetailView {


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
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.service_phone)
    TextView servicePhone;
    @BindView(R.id.service_type)
    TextView serviceType;
    @BindView(R.id.apply_time)
    TextView applyTime;
    @BindView(R.id.order_time)
    TextView orderTime;
    private NoticeDialog mNoticeDialog;//拨打电话弹框
    private NoticeDialog nNoticeDialog;//撤销服务弹框
    private NoticeDialog bNoticeDialog;//确认服务弹框
    private ScheduleDetailPresenter mPresenter;
    private int scheduleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);
        ButterKnife.bind(this);
        init();
    }


    public void init() {

        scheduleId = getIntent().getIntExtra("scheduleId", -1);

        if (mPresenter == null) {
            mPresenter = new ScheduleDetailPresenter(this);
        }
        mPresenter.getScheduleDetail(scheduleId);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.cancel, R.id.service_phone, R.id.btn_done})
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
            case R.id.service_phone:
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

    @Override
    public void showScheduleDetail(ScheduleDetailResponse response) {

        name.setText("本次服务由小二 "+response.getData().getClerkName()+" 为您服务");
        if(response.getData().getStatus()==0){

            status.setText("服务受理中");
        }else if(response.getData().getStatus()==1){

            status.setText("已完成");
        }else if(response.getData().getStatus()==2){

            status.setText("已撤销");
        }

        if(response.getData().getServiceType()==0){

            serviceType.setText("旺铺寻租");
        }else if(response.getData().getServiceType()==1){

            serviceType.setText("带我踩盘");
        }else if(response.getData().getServiceType()==2){

            serviceType.setText("签约租铺");
        }

        applyTime.setText(response.getData().getApplyTime());
        address.setText(response.getData().getDistrict());
        phone.setText(response.getData().getContactPhone());
        orderTime.setText(response.getData().getOrderTime());


    }
}
