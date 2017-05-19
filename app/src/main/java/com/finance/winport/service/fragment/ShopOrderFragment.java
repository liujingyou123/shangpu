package com.finance.winport.service.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;
import com.finance.winport.service.presenter.IFindServiceView;
import com.finance.winport.service.presenter.ServicePresenter;
import com.finance.winport.util.UnitUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 *
 */

public class ShopOrderFragment extends BaseFragment implements IFindServiceView {


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
    @BindView(R.id.sen_btn)
    TextView senBtn;
    @BindView(R.id.shop_count)
    TextView shopCount;
    @BindView(R.id.boss_count)
    TextView bossCount;

    private ServicePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.shop_order_fragment, container, false);
        ButterKnife.bind(this, root);
        tvFocusHouse.setText("带我踩盘");
        getData();
        return root;
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ServicePresenter(this);
        }
        mPresenter.getOrderShopCount();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.sen_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.sen_btn:
                BaseFragment sendShop = new SendShopOrderFragment();
                pushFragment(sendShop);
                break;
        }
    }

    @Override
    public void shopOrderCount(ShopOrderCountResponse response) {


        SpannableString builder = new SpannableString(response.getData().getAmount() + "间");
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor("#666666"));
        builder.setSpan(redSpan, builder.length() - 1, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new AbsoluteSizeSpan((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics())), builder.length() - 1, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        shopCount.setText(builder);

        SpannableString builder1 = new SpannableString(response.getData().getVisit() + "位");
        ForegroundColorSpan redSpan1 = new ForegroundColorSpan(Color.parseColor("#666666"));
        builder1.setSpan(redSpan1, builder1.length() - 1, builder1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder1.setSpan(new AbsoluteSizeSpan((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics())), builder1.length() - 1, builder1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bossCount.setText(builder1);


    }

    @Override
    public void findLoanCount(FindLoanCountResponse response) {

    }

//    @OnClick(R.id.imv_focus_house_back)
//    public void onViewClicked() {
//        handleBack();
//    }
}
