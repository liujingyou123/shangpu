package com.finance.winport.mine.framgent;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.account.model.ImageVerifyCode;
import com.finance.winport.account.model.Message;
import com.finance.winport.account.net.UserManager;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.mine.event.ModifyEvent;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.util.NetworkUtil;
import com.finance.winport.util.StringUtil;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.CountDownButton;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * 修改手机号
 */

public class ModifyPhoneFragment extends BaseFragment {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.oldPhone)
    TextView tvOldPhone;
    @BindView(R.id.phone_view)
    EditText phoneView;
    @BindView(R.id.verify_code_view_image)
    EditText verifyCodeViewImage;
    @BindView(R.id.pic_code)
    ImageView picCode;
    @BindView(R.id.image_verify_code)
    LinearLayout imageVerifyCode;
    @BindView(R.id.verify_code_view)
    EditText verifyCodeView;
    @BindView(R.id.countDown)
    CountDownButton countDown;
    private String oldPhone;

    private static final int CODE_LIMIT_COUNT = 3;// 单词获取验证码限制次数
    private int requestCodeCount;//获取验证码次数
    private boolean isFirstPic;
    private String messageId;
    private String picVerifyCode;
    private String picVerifyId;
    private String smsVerifyCode;
    String userPhone;

    private LoadingDialog loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forceSoftKeyboardVisible(true);
        if (getArguments() == null) return;
        oldPhone = getArguments().getString("data");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personal_phone, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }


    private void save() {
        //...

        String content = this.phoneView.getText().toString().trim();
        setResult(content);
        modifyUserPhone();
    }

    private void modifyUserPhone() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("oldPhone", oldPhone);
        params.put("newPhone", userPhone);
        params.put("smsVerifyCode", smsVerifyCode);
        params.put("messageId", messageId);
        params.put("picVerifyCode", picVerifyCode);
        params.put("picVerifyId", picVerifyId);
        PersonManager.getInstance().modifyUserPhone(params, new NetworkCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse response) {
                setResult(userPhone);
            }

            @Override
            public void failure(Throwable throwable) {
                ToastUtil.show(context, throwable.getMessage());
            }
        });
    }


    private void setResult(String contentText) {
        EventBus.getDefault().post(new ModifyEvent(ModifyEvent.InfoType.PHONE, contentText));
        handleBack();
    }

    private void initView() {
        tvFocusHouse.setText("修改手机号");
        tvOldPhone.setText(oldPhone);
        loading = new LoadingDialog(context);
        phoneView.setFilters(new InputFilter[]{TextViewUtil.phoneFormat()});
        phoneView.setInputType(InputType.TYPE_CLASS_PHONE);
        initCountDownButton();
    }

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
        countDown.setOnCountDownListener(new CountDownButton.OnCountDownListener() {
            @Override
            public void onFinish() {
//                if (phoneView.length() > 0) {
//                    clear.setVisibility(View.VISIBLE);
//                }
                phoneView.setEnabled(true);
            }

            @Override
            public void onTick(int time) {
//                clear.setVisibility(View.GONE);
                phoneView.setEnabled(false);
            }
        });
    }


    private boolean check() {
        userPhone = UnitUtil.trim(phoneView.getText().toString().trim());
        if (!NetworkUtil.isNetworkConnected(context)) {
            ToastUtil.show(context, "没有开启网络");
            return false;
        }
        if (!StringUtil.isCellPhone(userPhone)) {
            ToastUtil.show(context, "请输入正确手机号");
            return false;
        }
        return true;
    }

    private void counting() {
        countDown.start();
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
                ToastUtil.show(context, "验证码发送成功");
            }

            @Override
            public void failure(Throwable throwable) {
                if (getView() == null) return;
                countDown.reset();
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


    @OnClick(R.id.save)
    public void onSave() {
        save();
    }

    @OnClick(R.id.pic_code)
    public void onPicCode() {
        getPicCode();
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        handleBack();
    }
}
