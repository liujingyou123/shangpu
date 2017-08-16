package com.finance.winport.mine.framgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.mine.event.ModifyEvent;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * 修改签名
 */

public class ModifySignFragment extends BaseFragment {
    private static final int COUNT = 20;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.clear)
    ImageView clear;
    @BindView(R.id.remain)
    TextView remain;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    private String text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forceSoftKeyboardVisible(true);
        if (getArguments() == null) return;
        text = getArguments().getString("data");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personal_sign, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }


    private void save() {
        String content = this.content.getText().toString().trim();
        setResult(content);
        modifySign(content);
    }

    private void modifySign(final String content) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("signature", content);
        PersonManager.getInstance().modifyNickNameAndSign(params, new NetworkCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse response) {
                setResult(content);
            }

            @Override
            public void failure(Throwable throwable) {
                ToastUtil.show(context, throwable.getMessage());
            }
        });
    }

    private void setResult(String contentText) {
        EventBus.getDefault().post(new ModifyEvent(ModifyEvent.InfoType.SIGN, contentText));
        handleBack();
    }

    private void initView() {
        tvFocusHouse.setText("个性签名");
        remain.setText(COUNT + "");
        clear.setVisibility(View.GONE);
        content.setFilters(new InputFilter[]{new InputFilter.LengthFilter(COUNT)});
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int rm = COUNT - s.length();
//                if (s.length() > 0) {
//                    clear.setVisibility(View.VISIBLE);
//                } else {
//                    clear.setVisibility(View.GONE);
//                }
                if (rm > 0) {
                    remain.setText(rm + "");
                } else {
                    remain.setText("0");
                }
            }
        });
        content.setText(text);
        content.setSelection(text == null ? 0 : text.length());
    }

//    @OnClick(R.id.clear)
//    public void onClick() {
//        content.setText("");
//    }


    @OnClick(R.id.save)
    public void onSave() {
        if (content.getText().toString().trim().length() <= 0) {

        }
        save();
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        handleBack();
    }
}
