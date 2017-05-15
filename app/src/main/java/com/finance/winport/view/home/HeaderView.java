package com.finance.winport.view.home;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.finance.winport.R;
import com.finance.winport.image.GlideImageLoader;
import com.finance.winport.view.StopWatchTextView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/5/3.
 * 首页头部
 */

public class HeaderView extends RelativeLayout {
    @BindView(R.id.tv_today_shop)
    StopWatchTextView tvTodayShop;
    @BindView(R.id.ll_today)
    LinearLayout llToday;
    @BindView(R.id.ll_nomeney)
    LinearLayout llNomeney;
    @BindView(R.id.ll_baiping)
    LinearLayout llBaiping;
    @BindView(R.id.ll_nears)
    LinearLayout llNears;
    //    @BindView(R.id.ll_banner)
//    LinearLayout llBanner;
    @BindView(R.id.header_banner)
    Banner headerBanner;

    private List<String> mUrls = new ArrayList<>();
    private Context mContext;

    public HeaderView(Context context) {
        super(context);
        init(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_home_header, this);
        ButterKnife.bind(this, this);
        showBaner();
    }

    public void setUrls(List<String> urls) {
        mUrls.addAll(urls);
        showBaner();
    }

    private void showBaner() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                Banner banner = new Banner(mContext);
//                llBanner.addView(banner, lp);
//            }
//        }, 1000);

        headerBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        headerBanner.setIndicatorGravity(BannerConfig.CENTER);
        headerBanner.setImageLoader(new GlideImageLoader());
        ArrayList<String> list = new ArrayList<>();
        list.add("http://img5.imgtn.bdimg.com/it/u=611483611,2895064642&fm=23&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=3597903479,3025957499&fm=23&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=2110963888,887379731&fm=23&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=3161191814,1771697536&fm=23&gp=0.jpg");

        headerBanner.setImages(list);
        headerBanner.start();

    }


    public void setTodayShop(float number) {
        tvTodayShop.setShowNumber(number);
    }

    public void setNewShopsListener(OnClickListener onClickListener) {
        llToday.setOnClickListener(onClickListener);
    }

    public void setNoMenoyListener(OnClickListener onClickListener) {
        llNomeney.setOnClickListener(onClickListener);
    }

    public void setSmallShopListener(OnClickListener onClickListener) {
        llBaiping.setOnClickListener(onClickListener);
    }

    public void setNearMetroListener(OnClickListener onClickListener) {
        llNears.setOnClickListener(onClickListener);
    }

    public View getBannerView() {
        return headerBanner;
    }
}
