package com.finance.winport;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.finance.winport.base.BaseActivity;
import com.finance.winport.home.HomeFragment;
import com.finance.winport.tab.BusinessFragment;
import com.finance.winport.tab.MineFragment;
import com.finance.winport.tab.ServiceFragment;
import com.finance.winport.view.BottomTabView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements BottomTabView.OnTabSelectedListener {

    static final int HOME = 0;
    static final int SERVICE = 1;
    static final int BUSINESS = 2;
    static final int MINE = 3;
    @BindView(R.id.tabView)
    BottomTabView tabView;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
        tabView.setOnTabSelectedListener(this);
        tabView.setTabResIds(new int[]{R.drawable.selector_bottom_tab_home
                , R.drawable.selector_bottom_tab_service, R.drawable
                .selector_bottom_tab_business, R.drawable
                .selector_bottom_tab_mine});
        onTabSelected(HOME);
    }

    @Override
    public void onTabSelected(int index) {

        switch (index) {
            case HOME:
                handleHome(index);
                break;
            case SERVICE:
                handleService(index);
                break;
            case BUSINESS:
                handleBusiness(index);
                break;
            case MINE:
                handleMine(index);
                break;
        }
    }

    private void handleHome(int index) {
        HomeFragment homeFragment = (HomeFragment) fm
                .findFragmentByTag(HomeFragment.class.getName());
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        tabView.setIndicatorDisplay(0, true);
        addFragment(homeFragment, false);
    }

    private void handleService(int index) {
        ServiceFragment serviceFragment = (ServiceFragment) fm
                .findFragmentByTag(ServiceFragment.class.getName());
        if (serviceFragment == null) {
            serviceFragment = new ServiceFragment();
        }

        tabView.setIndicatorDisplay(1, true);
        addFragment(serviceFragment, false);
    }

    private void handleBusiness(int index) {
        BusinessFragment businessFragment = (BusinessFragment) fm
                .findFragmentByTag(BusinessFragment.class.getName());
        if (businessFragment == null) {
            businessFragment = new BusinessFragment();
        }
        tabView.setIndicatorDisplay(2, true);
        addFragment(businessFragment, false);
    }

    private void handleMine(int index) {
        MineFragment mineFragment = (MineFragment) fm
                .findFragmentByTag(MineFragment.class.getName());
        if (mineFragment == null) {
            mineFragment = new MineFragment();
        }
        tabView.setIndicatorDisplay(3, true);
        addFragment(mineFragment, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
