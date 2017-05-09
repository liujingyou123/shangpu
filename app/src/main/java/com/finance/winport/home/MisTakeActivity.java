package com.finance.winport.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;

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

    private int textSize;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mistake);
        ButterKnife.bind(this);
        init();
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

    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_change:
                llCode.setVisibility(View.VISIBLE);
                tvChange.setVisibility(View.GONE);
                break;
        }
    }
}
