package com.finance.winport.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.winport.R;
import com.finance.winport.account.model.ImageVerifyCode;
import com.finance.winport.account.model.Message;
import com.finance.winport.account.net.UserManager;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.Tag;
import com.finance.winport.home.model.TagResponse;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.StringUtil;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.util.ToolsUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.CheckGroup;
import com.finance.winport.view.CountDownButton;
import com.finance.winport.view.MistakeItem;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/9.
 * 纠错
 */

public class MisTakeActivity extends BaseActivity {

    @BindView(R.id.et_else)
    EditText etElse;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.ll_code)
    LinearLayout llCode;
    @BindView(R.id.tv_change)
    TextView tvChange;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.ll_tags)
    CheckGroup llTags;
    @BindView(R.id.rl_else)
    RelativeLayout rlElse;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.verify_code_view)
    EditText verifyCodeView;
    @BindView(R.id.verify_code_view_image)
    EditText verifyCodeViewImage;
    @BindView(R.id.pic_code)
    ImageView picCode;
    @BindView(R.id.btn_done)
    TextView btnDone;

    private int textSize;
    private List<Tag> data;
    private String shopId;
    private String tagId;
    @BindView(R.id.countDown)
    CountDownButton countDown;
    @BindView(R.id.image_verify_code)
    LinearLayout imageVerifyCode;
    private String userPhone;
    private String messageId;
    private String picVerifyCode;
    private String picVerifyId;

    private static final int CODE_LIMIT_COUNT = 3;// 单词获取验证码限制次数
    private int requestCodeCount;//获取验证码次数
    private String smsVerifyCode;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mistake);
        ButterKnife.bind(this);
        init();
        getTagList();
        getInitData();
    }

    private void getInitData() {
        Intent intent = getIntent();
        if (intent != null) {
            shopId = intent.getStringExtra("shopId");
        }
    }

    private void init() {
        tvFocusHouse.setText("旺铺纠错");
        etElse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    textSize = s.length();
                }
                tvNum.setText(textSize + "/200字");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPhone.setText(SharedPrefsUtil.getUserInfo().data.userPhone);

    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_change, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_change:

                initCountDownButton();

                llCode.setVisibility(View.VISIBLE);
                tvChange.setVisibility(View.GONE);
                break;
            case R.id.btn_done:
                if (check()) {
                    done();
                }
                break;
        }
    }

    private void getTagList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("tagType", "3");
        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getTagList(hashMap), new NetSubscriber<TagResponse>() {
            @Override
            public void response(TagResponse response) {
                if (response != null && response.getData() != null) {
                    data = response.getData();
                }
                setTags();
            }
        });
    }

    private void setTags() {
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                MistakeItem mistakeItem = new MistakeItem(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(this, 45));
                llTags.addView(mistakeItem, lp);
                mistakeItem.setTags(data.get(i));
            }
        }

        llTags.setOnCheckedBoxListener(new CheckGroup.onCheckedBoxListener() {
            @Override
            public void onCheckedBox(CheckBox checkBox) {
                if (checkBox.isChecked()) {
                    Tag tag = (Tag) checkBox.getTag();
                    if ("其他".equals(tag.getName())) {
                        rlElse.setVisibility(View.VISIBLE);
                    } else {
                        rlElse.setVisibility(View.GONE);
                    }
                    tagId = tag.getId() + "";
                } else {
                    Tag tag = (Tag) checkBox.getTag();
                    if ("其他".equals(tag.getName())) {
                        rlElse.setVisibility(View.GONE);
                    }
                    tagId = "";
                }

            }
        });
    }

    private void done() {
        if (TextUtils.isEmpty(tagId)) {
            ToastUtil.show(this, "请选择原因");
            return;
        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("shopId", shopId);
        hashMap.put("tagId", tagId);
        hashMap.put("contactMobile", userPhone);

        String content = etElse.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            hashMap.put("content", content);
        }

        String contacter = etName.getText().toString();

        if (TextUtils.isEmpty(contacter)) {
            ToastUtil.show(this, "请输入联系人");
            return;
        }

        hashMap.put("contacter", contacter);

        if (!TextUtils.isEmpty(messageId)) {
            hashMap.put("messageId", messageId);
        }

        if (!TextUtils.isEmpty(smsVerifyCode)) {
            hashMap.put("smsVerifyCode", smsVerifyCode);
        }

        if (!TextUtils.isEmpty(picVerifyId)) {
            hashMap.put("picVerifyId", picVerifyId);
        }

        if (!TextUtils.isEmpty(picVerifyCode)) {
            hashMap.put("picVerifyCode", picVerifyCode);
        }


        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).createCorrect(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                MisTakeActivity.this.finish();
                Intent intent = new Intent(MisTakeActivity.this, MistakeSuccessActivity.class);
                startActivity(intent);
            }

        });
    }

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

    private void counting() {
        countDown.start();
    }

    private boolean check() {
        userPhone = UnitUtil.trim(etPhone.getText().toString().trim());
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

    private void showPicCode() {
        if (imageVerifyCode.getVisibility() == View.GONE) {
            getPicCode();
        }
        imageVerifyCode.setVisibility(View.VISIBLE);
    }

    private void getVerifyCode() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userPhone", userPhone);
        params.put("sendType", 0);//0-短信 1-语音，默认0
        params.put("useScene", 5);//0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺
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
}
