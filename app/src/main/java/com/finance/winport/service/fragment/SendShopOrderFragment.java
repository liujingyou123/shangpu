package com.finance.winport.service.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.finance.winport.service.SendSuccessActivity;
import com.finance.winport.service.model.OrderShopRequest;
import com.finance.winport.service.model.SendOrderShopResponse;
import com.finance.winport.service.presenter.ISendOrderView;
import com.finance.winport.service.presenter.SendOrderPresenter;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.util.NoDoubleClickUtils;
import com.finance.winport.util.SharedPrefsUtil;
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
    @BindView(R.id.watch)
    LinearLayout watch;
    @BindView(R.id.shop_img2)
    ImageView shopImg2;
    @BindView(R.id.order_phone)
    TextView orderPhone;
    @BindView(R.id.shop_img3)
    ImageView shopImg3;
    @BindView(R.id.order_watch)
    TextView orderWatch;
    @BindView(R.id.shop_img4)
    ImageView shopImg4;
    @BindView(R.id.order_sign)
    TextView orderSign;
    @BindView(R.id.sign)
    LinearLayout sign;
    private SendOrderPresenter mPresenter;
    private String userPhone;
    private String messageId;
    private String picVerifyCode;
    private String picVerifyId;
    private String smsVerifyCode;
    private String inviteCode;

    private int requestCodeCount;//获取验证码次数
    private static final int CODE_LIMIT_COUNT = 3;// 单词获取验证码限制次数
    private String shopId;
    private int type;
    private int scene;

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
        phoneView.setEditable(false);
        phoneView.setInputType(InputType.TYPE_CLASS_PHONE);
        verifyCodeView.setInputType(InputType.TYPE_CLASS_NUMBER);
        phoneView.addTextChangedListener(watcher);
        phoneView.setText(SharedPrefsUtil.getUserInfo().data.userPhone.substring(0, 3) + " " + SharedPrefsUtil.getUserInfo().data.userPhone.substring(3, 7) + " " + SharedPrefsUtil.getUserInfo().data.userPhone.substring(7, 11));
        if (type == 1) {

            watch.setVisibility(View.VISIBLE);
            tvFocusHouse.setText("签约租铺");
            scene = 2;

        } else if (type == 2) {

            sign.setVisibility(View.VISIBLE);
            tvFocusHouse.setText("预约看铺");
            scene = 4;
        }
        initCountDownButton();
//        getArguments().getBundle().getString()
        shopId = getArguments().getString("shopId");
        type = getArguments().getInt("type", -1);


    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(UnitUtil.trim(phoneView.getText().toString().trim()).equals(SharedPrefsUtil.getUserInfo().data.userPhone)){

                llVerifyCode.setVisibility(View.GONE);
                llImgCode.setVisibility(View.GONE);
                codeLine.setVisibility(View.GONE);
                imgLine.setVisibility(View.GONE);
                modifyArea.setVisibility(View.GONE);
                modify.setVisibility(View.VISIBLE);
                ObjectAnimator animator1 = new ObjectAnimator().ofFloat(modifyArea, "scaleY", 1f, 0f);
                animator1.setDuration(200);
                animator1.setInterpolator(new LinearInterpolator());
                animator1.start();
                phoneView.setEditable(false);
            }

        }
    };

    private void getData() {

        OrderShopRequest request = new OrderShopRequest();
        request.setContactName(nameView.getText());
        request.setContactMobile(UnitUtil.trim(phoneView.getText().toString().trim()));
        request.setShopId(shopId);
        request.setSubscribeTime(orderTime.getText());
        request.setSmsVerifyCode(verifyCodeView.getText());
        request.setMessageId(messageId);
        request.setPicVerifyCode(imgCodeTxt.getText().toString().trim());
        request.setPicVerifyId(picVerifyId);
        if (mPresenter == null) {
            mPresenter = new SendOrderPresenter(this);
        }
        if (type == 1) {

            mPresenter.getShopSignResult(request);
        } else if (type == 2) {

            mPresenter.getShopOrderResult(request);
        }
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
                phoneView.setEditable(true);
                phoneView.setText("");
                break;

            case R.id.submit:
                if(!NoDoubleClickUtils.isDoubleClick()){

                    if (checkCommit()) {

                        getData();
                    }
                }
                break;
        }
    }

    @Override
    public void shopSendOrderResult(SendOrderShopResponse response) {

        Intent intent = new Intent(getActivity(), SendSuccessActivity.class);
        intent.putExtra("scheduleId", response.getData());
        intent.putExtra("type",2);
        startActivity(intent);
        getActivity().finish();
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
        params.put("useScene", scene);//0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺
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
        params.put("useScene", scene);//0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺
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

    private boolean checkCommit() {
        userPhone = UnitUtil.trim(phoneView.getText().toString().trim());
        smsVerifyCode = verifyCodeView.getText().toString().trim();

        if (TextUtils.isEmpty(nameView.getText())) {
            Toast.makeText(context, "请输入联系人姓名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!StringUtil.isCellPhone(userPhone)) {
            Toast.makeText(context, "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
            return false;
        }
        //校验图片验证码
        if (llVerifyCode.getVisibility() == View.VISIBLE) {
            if (TextUtils.isEmpty(verifyCodeView.getText())) {
                ToastUtil.show(context, "请输入短信验证码");
                return false;
            }
        }
        //校验图片验证码
        if (llImgCode.getVisibility() == View.VISIBLE) {
            if (!TextUtils.equals(picVerifyCode, imgCodeTxt.getText().toString().trim())) {
                ToastUtil.show(context, "图片验证码不正确");
                return false;
            }
        }
        if (TextUtils.isEmpty(orderTime.getText())) {
            Toast.makeText(context, "请输入约见时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
