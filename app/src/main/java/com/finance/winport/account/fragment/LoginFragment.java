package com.finance.winport.account.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
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
import com.finance.winport.base.BaseFragment;
import com.finance.winport.util.StringUtil;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.CountDownButton;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


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
    @BindView(R.id.invite_code)
    EditText inviteCode;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.contact)
    CheckBox contact;
    @BindView(R.id.contact_tip)
    TextView contactTip;
    private String phoneNo;
    private String checkCode;
    private boolean isVoiceEnable = true;
    private String type = "0";//0经纪人、1经纪公司、2经纪门店
    private String megId;

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
//        phoneView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
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
                return count + "秒";
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
//                    getVerifyCode();
                    verifyCodeView.requestFocus();
                }
            }
        });
    }


//    private void getVerifyCode() {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("phone", phoneNo);
//        UserManager.getInstance().getVerifyCode(params, new NetworkCallback<VerifyCode>() {
//            @Override
//            public void success(VerifyCode response) {
//
//                megId = response.getData().getMsgId();
//
//            }
//
//            @Override
//            public void failure(Throwable throwable) {
//                ToastUtil.show(context, throwable.getMessage());
//            }
//        });
//    }


    private void counting() {
        countDown.start();
    }

    private boolean check() {
        phoneNo = UnitUtil.trim(phoneView.getText().toString().trim());
        checkCode = verifyCodeView.getText().toString().trim();
        if (!StringUtil.isCellPhone(phoneNo)) {
            Toast.makeText(context, "请输入正确的电话号码",Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
