package com.finance.winport.account.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
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
import com.finance.winport.account.event.JPushEvent;
import com.finance.winport.account.model.ImageVerifyCode;
import com.finance.winport.account.model.Message;
import com.finance.winport.account.model.UserInfo;
import com.finance.winport.account.net.UserManager;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.StringUtil;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.CountDownButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.view_line1)
    View viewLine1;
    @BindView(R.id.view_line2)
    View viewLine2;
    @BindView(R.id.view_line3)
    View viewLine3;
    @BindView(R.id.clear)
    ImageView clear;
    private String userPhone;
    private String messageId;
    private String picVerifyCode;
    private String picVerifyId;
    private String smsVerifyCode;
    private String inviteCode;
    private boolean isVoiceEnable = true;
    private static final int CODE_LIMIT_COUNT = 1;// 单词获取验证码限制次数
    private int requestCodeCount;//获取验证码次数
    private boolean isFirstPic;

    private LoadingDialog loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

//    @Subscribe
//    public void onLoginEvent(JPushEvent event) {
//        login();
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        loading = new LoadingDialog(context);
        imageVerifyCode.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        phoneView.setFilters(new InputFilter[]{TextViewUtil.phoneFormat()});
        phoneView.setInputType(InputType.TYPE_CLASS_PHONE);
        verifyCodeView.setInputType(InputType.TYPE_CLASS_NUMBER);
        login.setEnabled(false);
        contact.setChecked(true);
        int start = 0;
        int end = contactTip.getText().toString().indexOf("》") + 1;
        TextViewUtil.setPartialColor(contactTip, start, end, (Color.parseColor("#8C9AC9")));
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

            if (!TextUtils.isEmpty(UnitUtil.trim(phoneView.getText().toString()))) {
                clear.setVisibility(View.VISIBLE);
            } else {
                clear.setVisibility(View.GONE);
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
                    verifyCodeView.requestFocus();
                    if (requestCodeCount >= CODE_LIMIT_COUNT) {
                        showPicCode();
                    } else {
                        getVerifyCode();
                        if (!isFirstPic) {// 如果还没有图片验证码,就预加载一张
                            getPicCode();
                        }
                    }
                }
            }
        });
    }

    private void showPicCode() {
        if (!isFirstPic && imageVerifyCode.getVisibility() == View.GONE) {
            getPicCode();
        }
        imageVerifyCode.setVisibility(View.VISIBLE);
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

    void initJPush() {
        if (TextUtils.isEmpty(JPushInterface.getRegistrationID(context))) {
            JPushInterface.init(context);
            loading.show();
        } else {
            loading.show();
            login();
        }
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
//        params.put("deviceId", JPushInterface.getRegistrationID(context.getApplicationContext()));
        params.put("deviceId", "ddssdsdgsdsdd");

        params.put("osType", 1);//0-iOS 1-Android
        UserManager.getInstance().login(params, new NetworkCallback<UserInfo>() {
            @Override
            public void success(UserInfo response) {
                if (getView() == null) return;
                loading.dismiss();
                if (response != null && response.isSuccess()) {
                    response.data.userPhone = userPhone;
                    SharedPrefsUtil.saveUserInfo(response);
                    getActivity().finish();
                } else {
                    verifyCodeView.setText("");
                    ToastUtil.show(context, response == null ? "null response" : response.errMsg);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                if (getView() == null) return;
                loading.dismiss();
                verifyCodeView.setText("");
                if (requestCodeCount >= CODE_LIMIT_COUNT) {
                    showPicCode();
                }
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
                    isFirstPic = true;
                    picVerifyId = response.data.picVerifyId;
                    picVerifyCode = response.data.picVerifyCode;
                    picCode.setImageBitmap(fromBase64(response.data.base64Str));
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
        if (imageVerifyCode.getVisibility() == View.VISIBLE) {
            if (!TextUtils.equals(picVerifyCode, verifyCodeViewImage.getText().toString().trim())) {
                ToastUtil.show(context, "图片验证码不正确");
                return false;
            }
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
            loading.show();
            login();
//            initJPush();
        }
    }


    @OnClick(R.id.close)
    public void onCloseClicked() {
        handleBack();
    }


    @OnClick(R.id.clear)
    public void onPhoneClearClicked() {
        phoneView.setText("");
    }

    @OnClick(R.id.contact_tip)
    public void onContractClicked() {
        pushFragment(new UserContractFragment());
    }
}
