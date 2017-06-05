package com.finance.winport.tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.tab.model.Prediction;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * 测凶吉结果
 */

public class PredictionResultFragment extends BaseFragment {

    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.prediction_name)
    TextView predictionName;
    @BindView(R.id.wealth_star)
    RatingBar wealthStar;
    @BindView(R.id.customer_star)
    RatingBar customerStar;
    @BindView(R.id.prospect_star)
    RatingBar prospectStar;
    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.confirm)
    TextView confirm;
    Prediction prediction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            prediction = (Prediction) getArguments().getSerializable("result");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(context).inflate(R.layout.fragment_prediction_result, container, false);
        ButterKnife.bind(this, root);
        initView();
        setResult();
        return root;
    }

    private void initView() {
        tvFocusHouse.setText("店名测吉凶");
    }

    private void setResult() {
        if (prediction != null) {
            title.setText(prediction.data.name);
            wealthStar.setRating(prediction.data.moneyLuck);
            customerStar.setRating(prediction.data.guestLuck);
            prospectStar.setRating(prediction.data.future);
            result.setText(prediction.data.description);
        }
    }


    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        handleBack();
    }

    @OnClick(R.id.confirm)
    public void onConfirmClicked() {
        MobclickAgent.onEvent(context,"luckyshopname_test_retest");
        handleBack();
    }
}
