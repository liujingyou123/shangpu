package com.finance.winport.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    @BindView(R.id.rl_title_root)
    RelativeLayout rlTitleRoot;

    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.contact)
    TextView contact;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.type)
    TextView type;
    private NoticeDialog mNoticeDialog;//拨打电话弹框
    private NoticeDialog nNoticeDialog;//撤销服务弹框
    private NoticeDialog bNoticeDialog;//确认服务弹框
    private ScheduleDetailPresenter mPresenter;
    private String scheduleId;
    private String clerkPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);
        ButterKnife.bind(this);
        init();
    }


    public void init() {
        tvFocusHouse.setText("日程详情");

        scheduleId = getIntent().getStringExtra("scheduleId");

        if (mPresenter == null) {
            mPresenter = new ScheduleDetailPresenter(this);
        }
        if (!TextUtils.isEmpty(scheduleId)) {

            mPresenter.getScheduleDetail(scheduleId);
        }
    }

//    @OnClick({R.id.imv_focus_house_back, R.id.cancel, R.id.service_phone, R.id.btn_done})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.imv_focus_house_back:
//                handleBack();
//                break;
//            case R.id.cancel:
//                MobclickAgent.onEvent(ScheduleDetailActivity.this, "date_undo");
//                if (nNoticeDialog == null) {
//                    nNoticeDialog = new NoticeDialog(this);
//                    nNoticeDialog.setMessage("撤销服务");
//                    nNoticeDialog.setPositiveBtn("确认");
//                    nNoticeDialog.setOkClickListener(new NoticeDialog.OnPreClickListner() {
//                        @Override
//                        public void onClick() {
//                            if (mPresenter == null) {
//                                mPresenter = new ServiceDetailPresenter(ScheduleDetailActivity.this);
//                            }
//                            mPresenter.revokeSchedule(scheduleId);
//                        }
//                    });
//                }
//                if (!nNoticeDialog.isShowing()) {
//                    nNoticeDialog.show();
//                }
//                break;
//            case R.id.service_phone:
//                MobclickAgent.onEvent(ScheduleDetailActivity.this, "date_call");
//                if (mNoticeDialog == null) {
//                    mNoticeDialog = new NoticeDialog(this);
//                    mNoticeDialog.setMessage("小二电话：" + clerkPhone);
//                    mNoticeDialog.setPositiveBtn("拨打");
//                    mNoticeDialog.setOkClickListener(new NoticeDialog.OnPreClickListner() {
//                        @Override
//                        public void onClick() {
//                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
//                            if (ActivityCompat.checkSelfPermission(ScheduleDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                                // TODO: Consider calling
//                                //    ActivityCompat#requestPermissions
//                                // here to request the missing permissions, and then overriding
//                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                //                                          int[] grantResults)
//                                // to handle the case where the user grants the permission. See the documentation
//                                // for ActivityCompat#requestPermissions for more details.
//                                return;
//                            }
//                            startActivity(intent);
//                        }
//                    });
//                }
//                if (!mNoticeDialog.isShowing()) {
//                    mNoticeDialog.show();
//                }
//                break;
//            case R.id.btn_done:
//                if (bNoticeDialog == null) {
//                    bNoticeDialog = new NoticeDialog(this);
//                    bNoticeDialog.setMessage("请确认小二已完成对您的任务");
//                    bNoticeDialog.setPositiveBtn("好的");
//                    bNoticeDialog.setOkClickListener(new NoticeDialog.OnPreClickListner() {
//                        @Override
//                        public void onClick() {
//                            if (mPresenter == null) {
//                                mPresenter = new ServiceDetailPresenter(ScheduleDetailActivity.this);
//                            }
//                            mPresenter.ensureSchedule(scheduleId);
//                        }
//                    });
//                }
//                if (!bNoticeDialog.isShowing()) {
//                    bNoticeDialog.show();
//                }
//                break;
//        }
//    }
//
//    @Override
//    public void showScheduleDetail(ServiceDetailResponse response) {
//
//
//        if (response.getData().getStatus() == 0 || response.getData().getStatus() == 3) {
//
//            status.setText("服务受理中");
//        } else if (response.getData().getStatus() == 1) {
//
//            status.setText("已完成");
//            cancel.setEnabled(false);
//            cancel.setTextColor(Color.parseColor("#cccccc"));
//            btnDone.setEnabled(false);
//        } else if (response.getData().getStatus() == 2) {
//
//            status.setText("已撤销");
//            cancel.setEnabled(false);
//            cancel.setTextColor(Color.parseColor("#cccccc"));
//            btnDone.setEnabled(false);
//        }
//
//        if (response.getData().getServiceType() == 0) {
//
//            serviceType.setText("旺铺寻租");
//            btnDone.setVisibility(View.GONE);
//        } else if (response.getData().getServiceType() == 1) {
//
//            serviceType.setText("预约看铺");
//            btnDone.setVisibility(View.VISIBLE);
//        } else if (response.getData().getServiceType() == 2) {
//
//            serviceType.setText("签约租铺");
//            btnDone.setVisibility(View.VISIBLE);
//        }
//
//        applyTime.setText(response.getData().getApplyTime());
//        address.setText(response.getData().getAddress());
//        phone.setText(response.getData().getContactPhone());
//        if(!TextUtils.isEmpty(response.getData().getOrderTime())){
//
//            orderTime.setText(response.getData().getOrderTime().substring(0,response.getData().getOrderTime().length()-3));
//        }
//        contactName.setText(response.getData().getContactName());
//
//        clerkPhone = response.getData().getClerkPhone();
//
//        if (TextUtils.isEmpty(response.getData().getClerkPhone())) {
//            btnDone.setEnabled(false);
//            servicePhone.setEnabled(false);
//            servicePhone.setTextColor(Color.parseColor("#cccccc"));
//            name.setText("等待小二接受服务");
//        } else {
//            name.setText("本次服务由小二 " + response.getData().getClerkName() + " 为您服务");
//        }
//
//    }
//
//    @Override
//    public void showensureSchedule(BaseResponse response) {
//        if (mPresenter == null) {
//            mPresenter = new ServiceDetailPresenter(this);
//        }
//        mPresenter.getScheduleDetail(scheduleId);
//    }
//
//    @Override
//    public void showRevokeSchedule(BaseResponse response) {
//
//        if (mPresenter == null) {
//            mPresenter = new ServiceDetailPresenter(this);
//        }
//        mPresenter.getScheduleDetail(scheduleId);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mPresenter.clear();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.contact:
                break;
        }
    }

    @Override
    public void showScheduleDetail(ScheduleDetailResponse response) {

        time.setText(response.getData().getOrderTime().substring(response.getData().getOrderTime().length()-8,response.getData().getOrderTime().length()-3));

    }
}
