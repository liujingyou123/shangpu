package com.finance.winport.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.finance.winport.MainActivity;
import com.finance.winport.R;
import com.finance.winport.account.AdActivity;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.home.model.BannerResponse;
import com.finance.winport.home.model.CheckVersionResponse;
import com.finance.winport.home.model.HomeFoundShopResponse;
import com.finance.winport.home.model.ShopCount;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.presenter.HomePresenter;
import com.finance.winport.home.view.IHomeView;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.tab.model.UnReadMsg;
import com.finance.winport.util.SpUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by liuworkmac on 17/5/31.
 */

public class LauncherActivity extends BaseActivity implements IHomeView{
    private Subscription mSubscription;

    private HomePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobclickAgent.enableEncrypt(true);
        setContentView(R.layout.launcher_activity);
        init();
    }



    private void init() {


        mSubscription = Observable.timer(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                String interTimes = SpUtil.getInstance().getStringData("interTimes", null);
                if (TextUtils.isEmpty(interTimes)) {

                    Intent intent = new Intent(LauncherActivity.this, AdActivity.class);
                    startActivity(intent);
                    LauncherActivity.this.finish();


                } else {

                    getData();

//                    Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    LauncherActivity.this.finish();
                }
            }
        });
//        String interTimes = SpUtil.getInstance().getStringData("interTimes", null);
//        if (TextUtils.isEmpty(interTimes)) {
//            Intent login = new Intent(context, AdActivity.class);
//            startActivity(login);
//            LauncherActivity.this.finish();
//        } else {
//            mSubscription = Observable.timer(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
//                @Override
//                public void call(Long aLong) {
//                    Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    LauncherActivity.this.finish();
//                }
//            });
//        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new HomePresenter(this);
        }
        mPresenter.getBanner("0","2");
    }

    @Override
    protected void onDestroy() {
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void showShopList(ShopListResponse response) {

    }

    @Override
    public void showMoreList(ShopListResponse response) {

    }

    @Override
    public void showShopCount(ShopCount response) {

    }

    @Override
    public void showBanners(BannerResponse response) {

        if(response.getData()==null||response.getData().size()==0){
            Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
            startActivity(intent);
            LauncherActivity.this.finish();
        }else{

            Intent login = new Intent(LauncherActivity.this, AdvertActivity.class);
            ArrayList<BannerResponse.DataBean> list = (ArrayList<BannerResponse.DataBean>) response.getData();
            login.putExtra("data",list);
            startActivity(login);
            LauncherActivity.this.finish();
        }

    }

    @Override
    public void isUnReadMsg(UnReadMsg readMsg) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void showPersonalInfo(PersonalInfoResponse response) {

    }

    @Override
    public void checkVersion(CheckVersionResponse response) {

    }

    @Override
    public void showFoundShop(HomeFoundShopResponse response) {

    }
}
