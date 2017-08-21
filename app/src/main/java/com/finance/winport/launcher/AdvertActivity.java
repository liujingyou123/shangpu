package com.finance.winport.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.MainActivity;
import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.home.H5Activity;
import com.finance.winport.home.ShopDetailActivity;
import com.finance.winport.home.model.BannerResponse;
import com.finance.winport.image.Batman;
import com.finance.winport.service.FindLoanActivity;
import com.finance.winport.service.ShopOrderActivity;
import com.finance.winport.service.ShopRentActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

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

    private ArrayList<BannerResponse.DataBean> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobclickAgent.enableEncrypt(true);
        setContentView(R.layout.advert_activity);
        ButterKnife.bind(this);
        init();
    }


    private void init() {

        list = (ArrayList<BannerResponse.DataBean>) getIntent().getSerializableExtra("data");
        Batman.getInstance().fromNet(list.get(0).getAdPicUrl(), img);

        handler = new Handler();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                // handler自带方法实现定时器
                try {
                    if (i == 1) {
                        Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                        startActivity(intent);
                        AdvertActivity.this.finish();
//                        return;
                    }
                    handler.postDelayed(this, TIME);
                    skip.setText("跳过  " + (--i));
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

    @OnClick({R.id.img, R.id.skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                if(list!=null&&list.get(0)!=null&&!TextUtils.isEmpty(list.get(0).getType())&&list.get(0).getType().equals("0")){
                    if (!TextUtils.isEmpty(list.get(0).getToUrl()) && !"null".equals(list.get(0).getToUrl())) {
                        Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                        startActivity(intent);
                        Intent bannerDetails = new Intent(AdvertActivity.this, H5Activity.class);
                        bannerDetails.putExtra("type", 4);
                        bannerDetails.putExtra("url", list.get(0).getToUrl());
                        startActivity(bannerDetails);
                    }
                }else{

                    Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                    startActivity(intent);
                    if (list!=null&&list.get(0)!=null&&!TextUtils.isEmpty(list.get(0).getInnerType())&&list.get(0).getInnerType().equals("1")) {
                        startActivity(new Intent(AdvertActivity.this, ShopRentActivity.class));
                    } else if (list!=null&&list.get(0)!=null&&!TextUtils.isEmpty(list.get(0).getInnerType())&&list.get(0).getInnerType().equals("2")) {
                        startActivity(new Intent(AdvertActivity.this, ShopOrderActivity.class));
                    } else if (list!=null&&list.get(0)!=null&&!TextUtils.isEmpty(list.get(0).getInnerType())&&list.get(0).getInnerType().equals("3")) {
                        startActivity(new Intent(AdvertActivity.this, FindLoanActivity.class));
                    } else if (list!=null&&list.get(0)!=null&&!TextUtils.isEmpty(list.get(0).getInnerType())&&list.get(0).getInnerType().equals("4")) {
                        startActivity(new Intent(AdvertActivity.this, ShopDetailActivity.class).putExtra("shopId", list.get(0).getBusinessId()));
                    } else if (list!=null&&list.get(0)!=null&&!TextUtils.isEmpty(list.get(0).getInnerType())&&list.get(0).getInnerType().equals("5")) {
                        startActivity(new Intent(AdvertActivity.this, MainActivity.class).putExtra("tab", MainActivity.BUSINESS));
                    } else if (list!=null&&list.get(0)!=null&&!TextUtils.isEmpty(list.get(0).getInnerType())&&list.get(0).getInnerType().equals("6")) {
                        startActivity(new Intent(AdvertActivity.this, MainActivity.class).putExtra("tab", MainActivity.SERVICE));
                    }
                }
                AdvertActivity.this.finish();
                break;
            case R.id.skip:
                Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                startActivity(intent);
                AdvertActivity.this.finish();
                break;
        }
    }



}
