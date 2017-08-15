package com.finance.winport.view.home;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.account.LoginActivity;
import com.finance.winport.home.FoundShopListActivity;
import com.finance.winport.home.H5Activity;
import com.finance.winport.home.model.BannerResponse;
import com.finance.winport.home.model.HomeFoundShopResponse;
import com.finance.winport.image.Batman;
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
    @BindView(R.id.shop_area)
    LinearLayout shopArea;
    @BindView(R.id.mine_schedule)
    TextView mineSchedule;
    @BindView(R.id.found_more)
    RelativeLayout foundMore;
    @BindView(R.id.find_shop)
    LinearLayout findShop;
    @BindView(R.id.commend)
    RelativeLayout commend;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.scroll)
    HorizontalScrollView scroll;
    @BindView(R.id.found_img1)
    ImageView foundImg1;
    @BindView(R.id.found_title1)
    TextView foundTitle1;
    @BindView(R.id.found1)
    RelativeLayout found1;
    @BindView(R.id.found_img2)
    ImageView foundImg2;
    @BindView(R.id.found_title2)
    TextView foundTitle2;
    @BindView(R.id.found2)
    RelativeLayout found2;
    @BindView(R.id.found_img3)
    ImageView foundImg3;
    @BindView(R.id.found_title3)
    TextView foundTitle3;
    @BindView(R.id.found3)
    RelativeLayout found3;

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

    public View getScrollView() {
        return scroll;
    }

    public void setNotice(boolean isNotice) {
        if (isNotice) {
            imvNotice.setImageResource(R.mipmap.label_message_selecteed);
        } else {
            imvNotice.setImageResource(R.mipmap.icon_navigation_message);
        }
    }

    public void setFound(List<HomeFoundShopResponse.DataBean> list) {
        if (list!=null) {
            if(list.size()==0){
                scroll.setVisibility(View.GONE);
            }else if(list.size()==1){

                foundTitle1.setText(list.get(0).getTitle());
                Batman.getInstance().fromNet(list.get(0).getImage(), foundImg1);
                found2.setVisibility(View.GONE);
                found3.setVisibility(View.GONE);

            }else if(list.size()==2){

                foundTitle1.setText(list.get(0).getTitle());
                Batman.getInstance().fromNet(list.get(0).getImage(), foundImg1);
                foundTitle2.setText(list.get(1).getTitle());
                Batman.getInstance().fromNet(list.get(1).getImage(), foundImg2);
                found3.setVisibility(View.GONE);

            }else if(list.size()>=3){

                foundTitle1.setText(list.get(0).getTitle());
                Batman.getInstance().fromNet(list.get(0).getImage(), foundImg1);
                foundTitle2.setText(list.get(1).getTitle());
                Batman.getInstance().fromNet(list.get(1).getImage(), foundImg2);
                foundTitle3.setText(list.get(2).getTitle());
                Batman.getInstance().fromNet(list.get(2).getImage(), foundImg3);

            }
        }
    }


    @OnClick({R.id.imv_notice, R.id.found_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_notice:
                MobclickAgent.onEvent(mContext, "message");
                if (SharedPrefsUtil.getUserInfo() != null) {
                    Intent intent = new Intent(mContext, MyNoticeActivity.class);
                    mContext.startActivity(intent);
                } else {
                    Intent intent1 = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent1);
                }
                break;
            case R.id.found_more:
                Intent intent1 = new Intent(mContext, FoundShopListActivity.class);
                mContext.startActivity(intent1);
                break;
        }
    }
}
