package com.finance.winport.service.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.service.SendSuccessActivity;
import com.finance.winport.service.model.OrderShopRequest;
import com.finance.winport.service.presenter.ISendOrderView;
import com.finance.winport.service.presenter.SendOrderPresenter;
import com.finance.winport.service.presenter.ServicePresenter;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.view.CountDownButton;
import com.finance.winport.view.HeaderTextView;
import com.finance.winport.view.dialog.DateSelectDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 *
 */

public class SendShopOrderFragment extends BaseFragment implements ISendOrderView{


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
    @BindView(R.id.shop_img1)
    ImageView shopImg1;
    @BindView(R.id.address1)
    TextView address1;
    @BindView(R.id.name_view)
    HeaderTextView nameView;
    @BindView(R.id.phone_view)
    HeaderTextView phoneView;
    @BindView(R.id.verify_code_view)
    HeaderTextView verifyCodeView;
    @BindView(R.id.countDown)
    CountDownButton countDown;
    @BindView(R.id.ll_verify_code)
    LinearLayout llVerifyCode;
    @BindView(R.id.img_code_view)
    HeaderTextView imgCodeView;
    @BindView(R.id.ll_img_code)
    LinearLayout llImgCode;
    @BindView(R.id.order_time)
    HeaderTextView orderTime;
    @BindView(R.id.modify)
    TextView modify;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.code_line)
    View codeLine;
    @BindView(R.id.img_line)
    View imgLine;
    @BindView(R.id.modify_area)
    LinearLayout modifyArea;
    private SendOrderPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.send_shop_order_fragment, container, false);
        ButterKnife.bind(this, root);
        init();
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void init() {
        phoneView.setFilters(new InputFilter[]{TextViewUtil.phoneFormat()});
//        phoneView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        phoneView.setInputType(InputType.TYPE_CLASS_PHONE);
        verifyCodeView.setInputType(InputType.TYPE_CLASS_NUMBER);
        phoneView.setText("188 7878 7998");
    }

    private void getData() {
        OrderShopRequest request = new OrderShopRequest();
        request.setContactName(nameView.getText());
        request.setContactMobile(phoneView.getText());
        request.setShopId("1");
        request.setSubscribeTime(orderTime.getText());
        if (mPresenter == null) {
            mPresenter = new SendOrderPresenter(this);
        }
        mPresenter.getShopOrderResult(request);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.order_time, R.id.modify, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.order_time:
                DateSelectDialog dialog = new DateSelectDialog(getActivity(), new DateSelectDialog.SelectResultListener() {
                    @Override
                    public void onResult(String date) {
                        orderTime.setText(date);
                    }
                });
                dialog.show();
                break;
            case R.id.modify:
//                llVerifyCode.setVisibility(View.VISIBLE);
//                llImgCode.setVisibility(View.VISIBLE);
//                codeLine.setVisibility(View.VISIBLE);
//                imgLine.setVisibility(View.VISIBLE);
                modifyArea.setVisibility(View.VISIBLE);

                ObjectAnimator animator1 = new ObjectAnimator().ofFloat(modifyArea, "scaleY", 0f,  1f);
                animator1.setDuration(200);
                animator1.setInterpolator(new LinearInterpolator());
                animator1.start();
                modify.setVisibility(View.GONE);
                break;

            case R.id.submit:
                getData();
                break;
        }
    }

    @Override
    public void shopSendOrderResult(BaseResponse response) {

        startActivity(new Intent(getActivity(), SendSuccessActivity.class));
    }

//    @OnClick(R.id.imv_focus_house_back)
//    public void onViewClicked() {
//        handleBack();
//    }

}
