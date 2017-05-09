package com.finance.winport.service.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 *
 */

public class SendFindLoanFragment extends BaseFragment {


    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.send_find_loan_fragment, container, false);
        ButterKnife.bind(this, root);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        handleBack();
    }

}
