package com.finance.winport.account.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.winport.R;
import com.finance.winport.account.model.ImageVerifyCode;
import com.finance.winport.account.model.Message;
import com.finance.winport.account.model.UserInfo;
import com.finance.winport.account.net.UserManager;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.image.Batman;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.StringUtil;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.CountDownButton;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;


/**
 *
 *
 */

public class LoginFragment extends BaseFragment {


    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.phone_view)
    EditText phoneView;
    @BindView(R.id.verify_code_view_image)
    EditText verifyCodeViewImage;
    @BindView(R.id.image_verify_code)
    LinearLayout imageVerifyCode;
    @BindView(R.id.verify_code_view)
    EditText verifyCodeView;
    @BindView(R.id.countDown)
    CountDownButton countDown;
    @BindView(R.id.ll_verify_code)
    LinearLayout llVerifyCode;
    @BindView(R.id.et_invite_code)
    EditText etInviteCode;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.contact)
    CheckBox contact;
    @BindView(R.id.contact_tip)
    TextView contactTip;
    @BindView(R.id.pic_code)
    ImageView picCode;
    private String userPhone;
    private String messageId;
    private String picVerifyCode;
    private String picVerifyId;
    private String smsVerifyCode;
    private String inviteCode;
    private boolean isVoiceEnable = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        phoneView.setFilters(new InputFilter[]{TextViewUtil.phoneFormat()});
        phoneView.setInputType(InputType.TYPE_CLASS_PHONE);
        verifyCodeView.setInputType(InputType.TYPE_CLASS_NUMBER);
        login.setEnabled(false);
        contact.setChecked(true);
        int start = 0;
        int end = contactTip.getText().toString().indexOf("》") + 1;
        TextViewUtil.setPartialColor(contactTip, start, end, (Color.parseColor("#000000")));
        initCountDownButton();

        contact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!TextUtils.isEmpty(UnitUtil.trim(phoneView.getText().toString().trim()))
                            && !TextUtils.isEmpty(verifyCodeView.getText().toString().trim())) {
                        login.setEnabled(true);
                    }
                } else {
                    login.setEnabled(false);
                }
            }
        });
        phoneView.addTextChangedListener(watcher);
        verifyCodeView.addTextChangedListener(watcher);
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
            if (!TextUtils.isEmpty(UnitUtil.trim(phoneView.getText().toString()))
                    && !TextUtils.isEmpty(verifyCodeView.getText().toString().trim())
                    ) {
                login.setEnabled(true);
            } else {
                login.setEnabled(false);
            }
        }
    };

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
                    getVerifyCode();
                    verifyCodeView.requestFocus();
                }
            }
        });
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
            }

            @Override
            public void failure(Throwable throwable) {
                ToastUtil.show(context, throwable.getMessage());
            }
        });
    }

    private void login() {
        userPhone = UnitUtil.trim(phoneView.getText().toString().trim());
        smsVerifyCode = verifyCodeView.getText().toString().trim();
        inviteCode = etInviteCode.getText().toString().trim();
        picVerifyCode = verifyCodeViewImage.getText().toString().trim();
        HashMap<String, Object> params = new HashMap<>();
        params.put("userPhone", userPhone);
        params.put("smsVerifyCode", smsVerifyCode);
        params.put("messageId", messageId);
        params.put("inviteCode", inviteCode);
        params.put("picVerifyCode", picVerifyCode);
        params.put("picVerifyId", picVerifyId);
        params.put("deviceId", JPushInterface.getRegistrationID(context.getApplicationContext()));
        params.put("osType", 1);//0-iOS 1-Android
        UserManager.getInstance().login(params, new NetworkCallback<UserInfo>() {
            @Override
            public void success(UserInfo response) {
                SharedPrefsUtil.saveUserInfo(response);
                getActivity().finish();
            }

            @Override
            public void failure(Throwable throwable) {
                verifyCodeView.setText("");
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
                    Batman.getInstance().fromNet(response.data.picUrl, picCode);
                }
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
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
        return true;
    }


//    private void debug() {
//        Intent improveInfo = new Intent(context, IdentityActivity.class);
//        startActivity(improveInfo);
//        getActivity().finish();
//    }


    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    public boolean handleDispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

            return true;
        }
        return super.handleDispatchKeyEvent(event);
    }


    @OnClick(R.id.pic_code)
    public void onPicClicked() {
        getPicCode();
    }

    @OnClick(R.id.login)
    public void onLoginClicked() {
        if (check()) {
            login();
        }
    }
}
