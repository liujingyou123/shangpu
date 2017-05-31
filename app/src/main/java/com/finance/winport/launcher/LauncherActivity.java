package com.finance.winport.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.finance.winport.MainActivity;
import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by liuworkmac on 17/5/31.
 */

public class LauncherActivity extends BaseActivity {
    private Subscription mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);
        init();
    }

    private void init() {
        mSubscription = Observable.timer(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
                LauncherActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        super.onDestroy();
    }
}
