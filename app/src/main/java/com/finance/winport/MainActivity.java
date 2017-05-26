package com.finance.winport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.finance.winport.base.BaseActivity;
import com.finance.winport.home.event.HomeEvent;
import com.finance.winport.home.HomeFragment;
import com.finance.winport.trade.TradeCircleFragment;
import com.finance.winport.tab.MineFragment;
import com.finance.winport.service.ServiceFragment;
import com.finance.winport.view.BottomTabView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;


public class MainActivity extends BaseActivity implements BottomTabView.OnTabSelectedListener {

    public static final int HOME = 0;
    public static final int SERVICE = 1;
    public static final int BUSINESS = 2;
    public static final int MINE = 3;
    @BindView(R.id.tabView)
    BottomTabView tabView;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d("main", "deviceId-->" + JPushInterface.getRegistrationID(context.getApplicationContext()));
        fm = getSupportFragmentManager();
        tabView.setOnTabSelectedListener(this);
        tabView.setTabResIds(new int[]{R.drawable.selector_bottom_tab_home
                , R.drawable.selector_bottom_tab_service, R.drawable
                .selector_bottom_tab_business, R.drawable
                .selector_bottom_tab_mine});

        Intent intent = getIntent();
        if (intent == null) {
            onTabSelected(HOME);
        } else {
            int tab = intent.getIntExtra("tab", HOME);
            onTabSelected(tab);
        }


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
        addFragment(homeFragment, true);
    }

    private void handleService(int index) {
        ServiceFragment serviceFragment = (ServiceFragment) fm
                .findFragmentByTag(ServiceFragment.class.getName());
        if (serviceFragment == null) {
            serviceFragment = new ServiceFragment();
        }

        tabView.setIndicatorDisplay(1, true);
        addFragment(serviceFragment, true);
    }

    private void handleBusiness(int index) {
        TradeCircleFragment tradeCircleFragment = (TradeCircleFragment) fm
                .findFragmentByTag(TradeCircleFragment.class.getName());
        if (tradeCircleFragment == null) {
            tradeCircleFragment = new TradeCircleFragment();
        }
        tabView.setIndicatorDisplay(2, true);
        addFragment(tradeCircleFragment, false);
    }

    private void handleMine(int index) {
        MineFragment mineFragment = (MineFragment) fm
                .findFragmentByTag(MineFragment.class.getName());
        if (mineFragment == null) {
            mineFragment = new MineFragment();
        }
        tabView.setIndicatorDisplay(3, true);
        addFragment(mineFragment, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        int tab = intent.getIntExtra("tab", HOME);
        handleHome(tab);
        tabView.setTabDisplay(tab);
    }

}
