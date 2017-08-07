package com.finance.winport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.TextView;


import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 16/11/16.
 * 房贷计算器
 */
public class MyServiceActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.id_tab)
    SlidingTabLayout idTab;
    @BindView(R.id.id_view_pager)
    ViewPager idViewPager;
    /*每个 tab 的 item*/
    private List<MyServicePageItem> mTab = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_service_list);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onClick() {
        handleBack();
    }

    private void initView() {
        String housePrice = getIntent().getStringExtra("housePrice");
        tvFocusHouse.setText("房贷计算器");
        mTab.add(new MyServicePageItem("公积金贷款", "1"));
        mTab.add(new MyServicePageItem("商业贷款", "2"));
        mTab.add(new MyServicePageItem("组合贷款", "3"));
//        idViewPager.setAdapter(new SlidingTabPagerAdapter(getSupportFragmentManager(), mTab));
        idTab.setViewPager(idViewPager);
        if (!TextUtils.isEmpty(housePrice)) {
            idViewPager.setCurrentItem(1);
        }
    }
}
