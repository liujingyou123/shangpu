package com.finance.winport.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.mine.api.MineServices;
import com.finance.winport.mine.model.ScheduleListResponse;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SuggestActivity extends BaseActivity {

    private static final int COUNT = 200;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rl_title_root)
    RelativeLayout rlTitleRoot;
    @BindView(R.id.feedback)
    EditText feedback;
    @BindView(R.id.clear)
    ImageView clear;
    @BindView(R.id.remain)
    TextView remain;
    @BindView(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvFocusHouse.setText("意见反馈");
        clear.setVisibility(View.GONE);
        remain.setText("0" + "/" + COUNT + "字");
        feedback.setFilters(new InputFilter[]{new InputFilter.LengthFilter(COUNT)});
        feedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int rm = s.length();
                if (s.length() > 0) {
                    clear.setVisibility(View.VISIBLE);
                } else {
                    clear.setVisibility(View.GONE);
                }
                if (rm > 0) {
                    remain.setText(rm + "/" + COUNT + "字");
                } else {
                    remain.setText("0" + "/" + COUNT + "字");
                }
            }
        });
    }

    @OnClick({R.id.submit, R.id.imv_focus_house_back, R.id.clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                userFeedback();
                break;
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.clear:
                feedback.setText("");
                break;
        }
    }

    private void userFeedback() {
        if (TextUtils.isEmpty(feedback.getText().toString().trim())) {
            ToastUtil.show(context, "意见反馈不能为空");
            return;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("feedback", feedback.getText().toString());
        ToolsUtil.subscribe(ToolsUtil.createService(MineServices.class).commitFeedBack(params), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if(isFinishing()){
                    return;
                }
                Toast.makeText(SuggestActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
