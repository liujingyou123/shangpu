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
import com.finance.winport.dialog.ScrollSelectDialog;
import com.finance.winport.service.SendSuccessActivity;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.view.CountDownButton;
import com.finance.winport.view.HeaderTextView;
import com.finance.winport.view.dialog.DateSelectDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 *
 */

public class SendFindLoanFragment extends BaseFragment {


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
    @BindView(R.id.shop_img2)
    ImageView shopImg2;
    @BindView(R.id.address2)
    TextView address2;
    @BindView(R.id.input_loan_money)
    HeaderTextView inputLoanMoney;
    @BindView(R.id.name_view)
    HeaderTextView nameView;
    @BindView(R.id.phone_view)
    HeaderTextView phoneView;
    @BindView(R.id.modify)
    TextView modify;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.verify_code_view)
    HeaderTextView verifyCodeView;
    @BindView(R.id.countDown)
    CountDownButton countDown;
    @BindView(R.id.ll_verify_code)
    LinearLayout llVerifyCode;
    @BindView(R.id.code_line)
    View codeLine;
    @BindView(R.id.img_code_view)
    HeaderTextView imgCodeView;
    @BindView(R.id.ll_img_code)
    LinearLayout llImgCode;
    @BindView(R.id.img_line)
    View imgLine;
    @BindView(R.id.order_time)
    HeaderTextView orderTime;
    @BindView(R.id.modify_area)
    LinearLayout modifyArea;
    ScrollSelectDialog scrollDialog;
    @BindView(R.id.loan_time)
    HeaderTextView loanTime;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.send_find_loan_fragment, container, false);
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
        inputLoanMoney.setInputType(InputType.TYPE_CLASS_NUMBER);
        phoneView.setText("188 7878 7998");

    }

    @OnClick({R.id.imv_focus_house_back, R.id.modify, R.id.order_time, R.id.loan_time, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.modify:
//                llVerifyCode.setVisibility(View.VISIBLE);
//                llImgCode.setVisibility(View.VISIBLE);
//                codeLine.setVisibility(View.VISIBLE);
//                imgLine.setVisibility(View.VISIBLE);
                modifyArea.setVisibility(View.VISIBLE);

                ObjectAnimator animator1 = new ObjectAnimator().ofFloat(modifyArea, "scaleY", 0f, 1f);
                animator1.setDuration(200);
                animator1.setInterpolator(new LinearInterpolator());
                animator1.start();
                modify.setVisibility(View.GONE);
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

            case R.id.loan_time:
                List<String> list = new ArrayList<>();
                list.add("1个月");
                list.add("3个月");
                list.add("6个月");
                list.add("12个月");
                list.add("24个月");
                list.add("36个月");
                if (scrollDialog == null) {

                    scrollDialog = new ScrollSelectDialog(getContext(), list, new ScrollSelectDialog.OnSelectListener() {
                        @Override
                        public void onSelect(String data) {

                            loanTime.setText(data);

                        }
                    });
                }
                scrollDialog.show();
                break;
            case R.id.submit:
                startActivity(new Intent(getActivity(), SendSuccessActivity.class));
                break;
        }
    }

//    @OnClick(R.id.imv_focus_house_back)
//    public void onViewClicked() {
//        handleBack();
//    }

}
