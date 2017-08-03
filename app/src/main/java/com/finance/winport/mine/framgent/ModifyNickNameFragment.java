package com.finance.winport.mine.framgent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.mine.event.ModifyEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * 修改昵称
 */

public class ModifyNickNameFragment extends BaseFragment {
    private static final int COUNT = 10;
    @BindView(R.id.content)
    EditText content;
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
        View root = inflater.inflate(R.layout.fragment_personal_nick_name, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }


    private void save() {
        //...

        String content = this.content.getText().toString().trim();
        setResult(content);
    }

    private void setResult(String contentText) {
        EventBus.getDefault().post(new ModifyEvent(ModifyEvent.InfoType.NICK_NAME, contentText));
        handleBack();
    }

    private void initView() {
        tvFocusHouse.setText("昵称");
        content.setFilters(new InputFilter[]{new InputFilter.LengthFilter(COUNT)});
        content.setText(text);
        content.setSelection(text == null ? 0 : text.length());
    }


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
