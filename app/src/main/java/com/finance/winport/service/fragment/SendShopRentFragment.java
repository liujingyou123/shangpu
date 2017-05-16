package com.finance.winport.service.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.map.AddrSelectActivity;
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

public class SendShopRentFragment extends BaseFragment {


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
    @BindView(R.id.map_address)
    HeaderTextView mapAddress;
    @BindView(R.id.modify)
    TextView modify;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.code_line)
    View codeLine;
    @BindView(R.id.img_line)
    View imgLine;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.send_shop_rent_fragment, container, false);
        ButterKnife.bind(this, root);
        init();
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void init()
    {
        phoneView.setFilters(new InputFilter[]{TextViewUtil.phoneFormat()});
//        phoneView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        phoneView.setInputType(InputType.TYPE_CLASS_PHONE);
        verifyCodeView.setInputType(InputType.TYPE_CLASS_NUMBER);
        phoneView.setText("18878787998");
    }
    @OnClick({R.id.imv_focus_house_back, R.id.order_time, R.id.map_address, R.id.modify})
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
            case R.id.map_address:
                startActivityForResult(new Intent(getActivity(), AddrSelectActivity.class), AddrSelectActivity.ACTIVITY_REQUEST_CODE_ADDR_SELECT);
                break;
            case R.id.modify:
                llVerifyCode.setVisibility(View.VISIBLE);
                llImgCode.setVisibility(View.VISIBLE);
                codeLine.setVisibility(View.VISIBLE);
                imgLine.setVisibility(View.VISIBLE);
                modify.setVisibility(View.GONE);
                break;
        }
    }

//    @OnClick(R.id.imv_focus_house_back)
//    public void onViewClicked() {
//        handleBack();
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == AddrSelectActivity.ACTIVITY_REQUEST_CODE_ADDR_SELECT) {
            String addrStr = data.getStringExtra("addr");
            if (!TextUtils.isEmpty(addrStr)) {
                mapAddress.setText(addrStr);
            }
        }
    }

}
