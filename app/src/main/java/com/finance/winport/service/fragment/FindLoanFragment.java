package com.finance.winport.service.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 *
 */

public class FindLoanFragment extends BaseFragment {


    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.icon1)
    ImageView icon1;
    @BindView(R.id.icon2)
    ImageView icon2;
    @BindView(R.id.icon3)
    ImageView icon3;
    @BindView(R.id.icon4)
    ImageView icon4;
    @BindView(R.id.icon5)
    ImageView icon5;
    @BindView(R.id.send_btn)
    TextView sendBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.find_loan_fragment, container, false);
        ButterKnife.bind(this, root);
        tvFocusHouse.setText("找资金");
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.send_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.send_btn:
                BaseFragment sendLoan = new SendFindLoanFragment();
                pushFragment(sendLoan);
                break;
        }
    }

}
