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

public class SendShopOrderFragment extends BaseFragment {


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
    @BindView(R.id.icon6)
    ImageView icon6;
    @BindView(R.id.icon7)
    ImageView icon7;
    @BindView(R.id.icon8)
    ImageView icon8;
    @BindView(R.id.icon9)
    ImageView icon9;
    @BindView(R.id.icon10)
    ImageView icon10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.shop_order_fragment, container, false);
        ButterKnife.bind(this, root);
        tvFocusHouse.setText("带我踩盘");
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
