package com.finance.winport.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.MainActivity;
import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by jge on 17/8/10.
 */

public class AdvertActivity extends BaseActivity {
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.skip)
    TextView skip;
    private Subscription mSubscription;

    private int TIME = 1000;
    private Handler handler;
    private int i = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobclickAgent.enableEncrypt(true);
        setContentView(R.layout.advert_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {


        handler = new Handler();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                // handler自带方法实现定时器
                try {
                    if(i==1){
                        Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                        startActivity(intent);
                        AdvertActivity.this.finish();
//                        return;
                    }
                    handler.postDelayed(this, TIME);
                    skip.setText("跳过  "+(--i));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, TIME); //每隔1s执行


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @OnClick(R.id.skip)
    public void onViewClicked() {
        Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
        startActivity(intent);
        AdvertActivity.this.finish();
    }
}
