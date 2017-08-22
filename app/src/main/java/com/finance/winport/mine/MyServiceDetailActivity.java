package com.finance.winport.mine;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.dialog.NoticeDialog;
import com.finance.winport.home.ShopDetailActivity;
import com.finance.winport.mine.model.ServiceDetailResponse;
import com.finance.winport.mine.presenter.IServiceDetailView;
import com.finance.winport.mine.presenter.ServiceDetailPresenter;
import com.finance.winport.util.UnitUtil;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyServiceDetailActivity extends BaseActivity implements IServiceDetailView {


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
    @BindView(R.id.reason)
    TextView reason;
    @BindView(R.id.contact_name)
    TextView contactName;
    @BindView(R.id.reason_line)
    View reasonLine;
    @BindView(R.id.reason_area)
    RelativeLayout reasonArea;
    private NoticeDialog mNoticeDialog;//拨打电话弹框
    private NoticeDialog nNoticeDialog;//撤销服务弹框
    private NoticeDialog bNoticeDialog;//确认服务弹框
    private ServiceDetailPresenter mPresenter;
    private String id, type;
    private String clerkPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);
        ButterKnife.bind(this);
        init();
    }


    public void init() {

        id = getIntent().getIntExtra("id", -1) + "";
        type = getIntent().getIntExtra("type", -1) + "";

        if (mPresenter == null) {
            mPresenter = new ServiceDetailPresenter(this);
        }
        if (!TextUtils.isEmpty(id)) {

            mPresenter.getServiceDetail(id, type);
        }
    }

    @OnClick({R.id.imv_focus_house_back, R.id.cancel, R.id.service_phone, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.cancel:
                MobclickAgent.onEvent(MyServiceDetailActivity.this, "date_undo");
                if (nNoticeDialog == null) {
                    nNoticeDialog = new NoticeDialog(this);
                    nNoticeDialog.setMessage("撤销服务");
                    nNoticeDialog.setPositiveBtn("确认");
                    nNoticeDialog.setOkClickListener(new NoticeDialog.OnPreClickListner() {
                        @Override
                        public void onClick() {
                            if (mPresenter == null) {
                                mPresenter = new ServiceDetailPresenter(MyServiceDetailActivity.this);
                            }
                            mPresenter.revokeSchedule(id, type);
                        }
                    });
                }
                if (!nNoticeDialog.isShowing()) {
                    nNoticeDialog.show();
                }
                break;
            case R.id.service_phone:
                MobclickAgent.onEvent(MyServiceDetailActivity.this, "date_call");
                if (mNoticeDialog == null) {
                    mNoticeDialog = new NoticeDialog(this);
                    mNoticeDialog.setMessage("小二电话：" + clerkPhone);
                    mNoticeDialog.setPositiveBtn("拨打");
                    mNoticeDialog.setOkClickListener(new NoticeDialog.OnPreClickListner() {
                        @Override
                        public void onClick() {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + clerkPhone));
                            if (ActivityCompat.checkSelfPermission(MyServiceDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            startActivity(intent);
                        }
                    });
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
                    bNoticeDialog.setOkClickListener(new NoticeDialog.OnPreClickListner() {
                        @Override
                        public void onClick() {
                            if (mPresenter == null) {
                                mPresenter = new ServiceDetailPresenter(MyServiceDetailActivity.this);
                            }
                            mPresenter.ensureSchedule(id, type);
                        }
                    });
                }
                if (!bNoticeDialog.isShowing()) {
                    bNoticeDialog.show();
                }
                break;
        }
    }

    @Override
    public void showScheduleDetail(final ServiceDetailResponse response) {


        if (response.getData().getShopStatus() == 0) {

            status.setText("服务受理中");
            reasonLine.setVisibility(View.GONE);
            reasonArea.setVisibility(View.GONE);
        } else if (response.getData().getShopStatus() == 1) {

            status.setText("旺铺已发布");
            cancel.setEnabled(false);
            cancel.setTextColor(Color.parseColor("#cccccc"));
            btnDone.setEnabled(false);
            reasonLine.setVisibility(View.GONE);
            reasonArea.setVisibility(View.GONE);
        } else if (response.getData().getShopStatus() == 2) {

            status.setText("服务已完成");
            cancel.setEnabled(false);
            cancel.setTextColor(Color.parseColor("#cccccc"));
            btnDone.setEnabled(false);
            reasonLine.setVisibility(View.GONE);
            reasonArea.setVisibility(View.GONE);
        } else if (response.getData().getShopStatus() == 3) {

            status.setText("服务已撤销");
            cancel.setEnabled(false);
            cancel.setTextColor(Color.parseColor("#cccccc"));
            btnDone.setEnabled(false);
            reasonLine.setVisibility(View.GONE);
            reasonArea.setVisibility(View.GONE);
        } else if (response.getData().getShopStatus() == 4) {

            status.setText("店铺被废弃");
            cancel.setEnabled(false);
            cancel.setTextColor(Color.parseColor("#cccccc"));
            btnDone.setEnabled(false);
            reasonLine.setVisibility(View.VISIBLE);
            reasonArea.setVisibility(View.VISIBLE);
        }

        if (response.getData().getType() == 0) {

            serviceType.setText("旺铺寻租");
            btnDone.setVisibility(View.GONE);
        } else if (response.getData().getType() == 1) {

            serviceType.setText("预约看铺");
            btnDone.setVisibility(View.VISIBLE);
        } else if (response.getData().getType() == 2) {

            serviceType.setText("签约租铺");
            btnDone.setVisibility(View.VISIBLE);
        }

        applyTime.setText(response.getData().getCreateTime());
        address.setText(response.getData().getAddress());
        if(!TextUtils.isEmpty(response.getData().getShopId())){
            address.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.arrow,0);
            address.setCompoundDrawablePadding(UnitUtil.dip2px(MyServiceDetailActivity.this,10));
            address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyServiceDetailActivity.this, ShopDetailActivity.class);
                    intent.putExtra("shopId", response.getData().getShopId());
                }
            });
        }
        phone.setText(response.getData().getContactPhone());
//        if(!TextUtils.isEmpty(response.getData().getOrderTime())){
//
//            orderTime.setText(response.getData().getOrderTime().substring(0,response.getData().getOrderTime().length()-3));
//        }
        contactName.setText(response.getData().getContactPerson());

        clerkPhone = response.getData().getClerkPhone();

        if (TextUtils.isEmpty(response.getData().getClerkPhone())) {
            btnDone.setEnabled(false);
            servicePhone.setEnabled(false);
            servicePhone.setTextColor(Color.parseColor("#cccccc"));
            name.setText("等待小二接受服务");
        } else {
            name.setText("本次服务由小二 " + response.getData().getClerkName() + " 为您服务");
        }

        reason.setText(response.getData().getDiscardReason());

    }

    @Override
    public void showensureSchedule(BaseResponse response) {
        if (mPresenter == null) {
            mPresenter = new ServiceDetailPresenter(this);
        }
        mPresenter.getServiceDetail(id, type);
    }

    @Override
    public void showRevokeSchedule(BaseResponse response) {

        if (mPresenter == null) {
            mPresenter = new ServiceDetailPresenter(this);
        }
        mPresenter.getServiceDetail(id, type);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.clear();
    }
}
