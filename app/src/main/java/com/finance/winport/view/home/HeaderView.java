package com.finance.winport.view.home;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.finance.winport.R;
import com.finance.winport.account.LoginActivity;
import com.finance.winport.home.H5Activity;
import com.finance.winport.home.MisTakeActivity;
import com.finance.winport.home.ShopDetailActivity;
import com.finance.winport.home.model.BannerResponse;
import com.finance.winport.image.GlideImageLoader;
import com.finance.winport.mine.MyNoticeActivity;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.view.StopWatchTextView;
import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.header_banner)
    Banner headerBanner;
    @BindView(R.id.tv_no_change)
    StopWatchTextView tvNoChange;
    @BindView(R.id.tv_limt_area)
    StopWatchTextView tvLimtArea;
    @BindView(R.id.tv_near_station)
    StopWatchTextView tvNearStation;
    @BindView(R.id.imv_notice)
    ImageView imvNotice;

    private List<BannerResponse.DataBean> mUrls = new ArrayList<>();
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

    public void setUrls(List<BannerResponse.DataBean> urls) {
        mUrls.clear();
        mUrls.addAll(urls);
        showBaner();
    }

    private void showBaner() {
        headerBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        headerBanner.setIndicatorGravity(BannerConfig.CENTER);
        headerBanner.setImageLoader(new GlideImageLoader());
        headerBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                MobclickAgent.onEvent(mContext, "shoplist_banner");
                if (!TextUtils.isEmpty(mUrls.get(position).getToUrl()) && !"null".equals(mUrls.get(position).getToUrl())) {
                    Intent bannerDetails = new Intent(mContext, H5Activity.class);
                    bannerDetails.putExtra("type", 4);
                    bannerDetails.putExtra("url", mUrls.get(position).getToUrl());
                    mContext.startActivity(bannerDetails);
                }

            }
        });

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < mUrls.size(); i++) {
            list.add(mUrls.get(i).getAdPicUrl());
        }

        headerBanner.setImages(list);
        headerBanner.start();

    }


    public void setTodayShop(int number) {
        tvTodayShop.setShowNumber(number);
    }

    public void setNoChange(int number) {
        tvNoChange.setShowNumber(number);
    }

    public void setTvLimtArea(int number) {
        tvLimtArea.setShowNumber(number);
    }

    public void setTvNearStation(int number) {
        tvNearStation.setShowNumber(number);
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

    public void setNotice(boolean isNotice) {
        if (isNotice) {
            imvNotice.setImageResource(R.mipmap.label_message_selecteed);
        } else {
            imvNotice.setImageResource(R.mipmap.icon_navigation_message);
        }
    }

    @OnClick(R.id.imv_notice)
    public void onViewClicked() {
        MobclickAgent.onEvent(mContext, "message");
        if (SharedPrefsUtil.getUserInfo() != null) {
            Intent intent = new Intent(mContext, MyNoticeActivity.class);
            mContext.startActivity(intent);
        } else {
            Intent intent1 = new Intent(mContext, LoginActivity.class);
            mContext.startActivity(intent1);
        }

    }
}
