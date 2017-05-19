package com.finance.winport.tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.tab.model.Prediction;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.util.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * 测凶吉
 */

public class PredictionFragment extends BaseFragment {

    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.confirm)
    TextView confirm;
    String name;
    LoadingDialog loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(context).inflate(R.layout.fragment_prediction, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        tvFocusHouse.setText("店名测吉凶");
        loading = new LoadingDialog(context);
    }


    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        handleBack();
    }

    @OnClick({R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                name = content.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.show(context, "请输入店名");
                    return;
                }
                prediction();
                break;
        }
    }

    private void prediction() {
        loading.show();
        HashMap<String, Object> params = new HashMap<>();
        params.put("shopName", name);
        PersonManager.getInstance().predictionShop(params, new NetworkCallback<Prediction>() {
            @Override
            public void success(Prediction response) {
                if (getView() == null) return;
                loading.dismiss();
                handleResult(response);
            }

            @Override
            public void failure(Throwable throwable) {
                if (getView() == null) return;
                loading.dismiss();
            }
        });
    }

    private void handleResult(Prediction result) {
        BaseFragment resultFragment = new PredictionResultFragment();
        Bundle args = new Bundle();
        args.putSerializable("result", result);
        resultFragment.setArguments(args);
        pushFragment(resultFragment);
    }
}
