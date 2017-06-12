package com.finance.winport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.finance.winport.account.LoginActivity;
import com.finance.winport.account.event.TokenTimeOutEvent;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.home.HomeFragment;
import com.finance.winport.trade.TradeCircleFragment;
import com.finance.winport.tab.MineFragment;
import com.finance.winport.service.ServiceFragment;
import com.finance.winport.view.BottomTabView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import noman.weekcalendar.eventbus.Event;


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
        EventBus.getDefault().register(this);
//        Log.d("main", "deviceId-->" + JPushInterface.getRegistrationID(context.getApplicationContext()));
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
        MobclickAgent.onEvent(context, "shoplist");
        HomeFragment homeFragment = (HomeFragment) fm
                .findFragmentByTag(HomeFragment.class.getName());
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        tabView.setIndicatorDisplay(0, true);
        addHomeFragment(homeFragment, true);
    }

    private void handleService(int index) {
        ServiceFragment serviceFragment = (ServiceFragment) fm
                .findFragmentByTag(ServiceFragment.class.getName());
        if (serviceFragment == null) {
            serviceFragment = new ServiceFragment();
        }

        tabView.setIndicatorDisplay(1, true);
        addHomeFragment(serviceFragment, true);
    }

    private void handleBusiness(int index) {
        MobclickAgent.onEvent(context, "circle");
        TradeCircleFragment tradeCircleFragment = (TradeCircleFragment) fm
                .findFragmentByTag(TradeCircleFragment.class.getName());
        if (tradeCircleFragment == null) {
            tradeCircleFragment = new TradeCircleFragment();
        }
        tabView.setIndicatorDisplay(2, true);
        addHomeFragment(tradeCircleFragment, false);
    }

    private void handleMine(int index) {
        MobclickAgent.onEvent(context, "my");
        MineFragment mineFragment = (MineFragment) fm
                .findFragmentByTag(MineFragment.class.getName());
        if (mineFragment == null) {
            mineFragment = new MineFragment();
        }
        tabView.setIndicatorDisplay(3, true);
        addHomeFragment(mineFragment, true);
    }

    //    private void handleMap(Bundle bundle) {
//        MapActivity mapFragment = new MapActivity();
//        mapFragment.setArguments(bundle);
//        pushFragment(mapFragment);
//    }
//
//
//    public void pushFragment(android.app.Fragment fragment) {
//        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.setCustomAnimations(
//                R.animator.fragment_rotate_pop_enter,
//                R.animator.fragment_rotate_pop_exit,
//                R.animator.fragment_rotate_enter,
//                R.animator.fragment_rotate_exit);
//        ft.replace(R.id.rl_fragment_content, fragment);
//        ft.addToBackStack(null);
//        ft.commit();
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onTokenTimeOutEvent(TokenTimeOutEvent event) {
        if (event != null) {
            startActivity(new Intent(context, LoginActivity.class));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        int tab = intent.getIntExtra("tab", HOME);
        onTabSelected(tab);
//        tabView.setTabDisplay(tab);
    }

    protected void addHomeFragment(BaseFragment fragment, boolean addToBackStack) {
        String tag = fragment.getClass().getName();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        int count = fm.getBackStackEntryCount();
        if (fm.findFragmentByTag(tag) == null) {
            ft.add(R.id.rl_fragment_content, fragment, tag);
        }
        ft.show(fragment);
        List<Fragment> list = fm.getFragments();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null && list.get(i) != fragment) {
                    ft.hide(list.get(i));
                }
            }
        }
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

}
