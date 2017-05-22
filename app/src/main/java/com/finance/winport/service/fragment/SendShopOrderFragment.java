package com.finance.winport.service.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.winport.R;
import com.finance.winport.account.model.ImageVerifyCode;
import com.finance.winport.account.model.Message;
import com.finance.winport.account.net.UserManager;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.service.SendSuccessActivity;
import com.finance.winport.service.model.OrderShopRequest;
import com.finance.winport.service.model.SendOrderShopResponse;
import com.finance.winport.service.presenter.ISendOrderView;
import com.finance.winport.service.presenter.SendOrderPresenter;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.util.StringUtil;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.CountDownButton;
import com.finance.winport.view.HeaderTextView;
import com.finance.winport.view.dialog.DateSelectDialog;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 *
 */

public class SendShopOrderFragment extends BaseFragment implements ISendOrderView {


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
    @BindView(R.id.img_code_txt)
    HeaderTextView imgCodeTxt;
    @BindView(R.id.img_code_view)
    ImageView imgCodeView;
    @BindView(R.id.submit)
    TextView submit;
    private SendOrderPresenter mPresenter;
    private String userPhone;
    private String messageId;
    private String picVerifyCode;
    private String picVerifyId;
    private String smsVerifyCode;
    private String inviteCode;

    private int requestCodeCount;//获取验证码次数
    private static final int CODE_LIMIT_COUNT = 3;// 单词获取验证码限制次数

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
        initCountDownButton();
    }

    private void getData() {
        OrderShopRequest request = new OrderShopRequest();
        request.setContactName(nameView.getText());
        request.setContactMobile(phoneView.getText());
        request.setShopId("1");
        request.setSubscribeTime(orderTime.getText());
        request.setSmsVerifyCode(verifyCodeView.getText());
        request.setMessageId(messageId);
        request.setPicVerifyCode(imgCodeTxt.getText().toString().trim());
        request.setPicVerifyId(picVerifyId);
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
                llVerifyCode.setVisibility(View.VISIBLE);
                llImgCode.setVisibility(View.GONE);
                codeLine.setVisibility(View.VISIBLE);
                imgLine.setVisibility(View.GONE);
                modifyArea.setVisibility(View.VISIBLE);
                modifyArea.setVisibility(View.VISIBLE);

                ObjectAnimator animator1 = new ObjectAnimator().ofFloat(modifyArea, "scaleY", 0f, 1f);
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
    public void shopSendOrderResult(SendOrderShopResponse response) {

        Intent intent = new Intent(getActivity(), SendSuccessActivity.class);
        intent.putExtra("scheduleId",response.getData());
        startActivity(intent);
    }

//    @OnClick(R.id.imv_focus_house_back)
//    public void onViewClicked() {
//        handleBack();
//    }


    // 倒计时
    private void initCountDownButton() {
        countDown.setOnUpdateTextListener(new CountDownButton.OnUpdateTextListener() {
            @Override
            public String onPreText() {
                return "获取验证码";
            }

            @Override
            public String onCountingText(int count) {
                return count + "s";
            }

            @Override
            public String onEndText() {
                return "重新获取";
            }
        });

        countDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    counting();
                    verifyCodeView.requestFocus();
                    if (requestCodeCount >= CODE_LIMIT_COUNT) {
                        showPicCode();
                    } else {
                        getVerifyCode();
                    }
                }
            }
        });
    }

    private void showPicCode() {
        if (llImgCode.getVisibility() == View.GONE) {
            getPicCode();
        }
        llImgCode.setVisibility(View.VISIBLE);
        imgLine.setVisibility(View.VISIBLE);
        ObjectAnimator animator1 = new ObjectAnimator().ofFloat(llImgCode, "scaleY", 0f, 1f);
        animator1.setDuration(200);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.start();
        modify.setVisibility(View.GONE);
    }


    private void getVerifyCode() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userPhone", userPhone);
        params.put("sendType", 0);//0-短信 1-语音，默认0
        params.put("useScene", 0);//0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺
        UserManager.getInstance().getVerifyCode(params, new NetworkCallback<Message>() {
            @Override
            public void success(Message response) {
                messageId = response.data.messageId;
                requestCodeCount++;
            }

            @Override
            public void failure(Throwable throwable) {
                ToastUtil.show(context, throwable.getMessage());
            }
        });
    }

    // 获取图片验证码
    private void getPicCode() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("useScene", 0);//0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺
        UserManager.getInstance().getPicCode(params, new NetworkCallback<ImageVerifyCode>() {
            @Override
            public void success(ImageVerifyCode response) {
                if (response != null && response.isSuccess()) {
                    picVerifyId = response.data.picVerifyId;
                    picVerifyCode = response.data.picVerifyCode;
                    imgCodeView.setImageBitmap(fromBase64(response.data.base64Str));
                }
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
    }

    public static Bitmap fromBase64(String base64) {
        Bitmap bitmap = null;
        if (!TextUtils.isEmpty(base64)) {
            try {
                byte[] bytes = Base64.decode(base64, Base64.DEFAULT);
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            } catch (OutOfMemoryError error) {
                error.printStackTrace();
            }
        }
        return bitmap;
    }


    private void counting() {
        countDown.start();
    }

    private boolean check() {
        userPhone = UnitUtil.trim(phoneView.getText().toString().trim());
        smsVerifyCode = verifyCodeView.getText().toString().trim();
        if (!StringUtil.isCellPhone(userPhone)) {
            Toast.makeText(context, "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
            return false;
        }
        //校验图片验证码
        if (llImgCode.getVisibility() == View.VISIBLE) {
            if (!TextUtils.equals(picVerifyCode, imgCodeTxt.getText().toString().trim())) {
                ToastUtil.show(context, "图片验证码不正确");
                return false;
            }
        }
        return true;
    }

}
