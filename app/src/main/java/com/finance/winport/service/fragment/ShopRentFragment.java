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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;
import com.finance.winport.service.model.ShopRentCountResponse;
import com.finance.winport.service.presenter.IFindServiceView;
import com.finance.winport.service.presenter.ServicePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 *
 */

public class ShopRentFragment extends BaseFragment implements IFindServiceView {


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
    @BindView(R.id.rl_title_root)
    RelativeLayout rlTitleRoot;
    @BindView(R.id.boss_count)
    TextView bossCount;
    @BindView(R.id.day_count)
    TextView dayCount;

    private ServicePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.shop_rent_fragment, container, false);
        ButterKnife.bind(this, root);
        tvFocusHouse.setText("旺铺寻租");
        getData();
        return root;
    }


    private void getData() {
        SpannableString builder1 = new SpannableString(30 + "天");
        ForegroundColorSpan redSpan1 = new ForegroundColorSpan(Color.parseColor("#666666"));
        builder1.setSpan(redSpan1, builder1.length() - 1, builder1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder1.setSpan(new AbsoluteSizeSpan((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics())), builder1.length() - 1, builder1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        dayCount.setText(builder1);
        if (mPresenter == null) {
            mPresenter = new ServicePresenter(this);
        }
        mPresenter.getShopRentCount();
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
                BaseFragment sendRent = new SendShopRentFragment();
                pushFragment(sendRent);
                break;
        }
    }


    @Override
    public void shopOrderCount(ShopOrderCountResponse response) {

    }

    @Override
    public void findLoanCount(FindLoanCountResponse response) {

    }

    @Override
    public void showRentCount(ShopRentCountResponse response) {

        SpannableString builder1 = new SpannableString(response.getData() + "位");
        ForegroundColorSpan redSpan1 = new ForegroundColorSpan(Color.parseColor("#666666"));
        builder1.setSpan(redSpan1, builder1.length() - 1, builder1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder1.setSpan(new AbsoluteSizeSpan((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics())), builder1.length() - 1, builder1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bossCount.setText(builder1);
    }
}
