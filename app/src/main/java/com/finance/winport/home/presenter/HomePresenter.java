package com.finance.winport.home.presenter;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.BannerResponse;
import com.finance.winport.home.model.RegionResponse;
import com.finance.winport.home.model.ShopCount;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.view.IHomeView;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.net.NoneNetSubscriber;
import com.finance.winport.tab.model.UnReadMsg;
import com.finance.winport.tab.net.PersonService;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/5/16.
 */

public class HomePresenter {
    private IHomeView mIHomeView;

    public HomePresenter(IHomeView mIHomeView) {
        this.mIHomeView = mIHomeView;
    }

    public void getShopList(ShopRequset requset) {
        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getShops(requset), new NetSubscriber<ShopListResponse>() {
            @Override
            public void response(ShopListResponse response) {
                if (mIHomeView != null) {
                    mIHomeView.showShopList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIHomeView != null) {
                    mIHomeView.onError();
                }
            }
        });

    }

    public void getMoreShopList(ShopRequset requset) {
        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getShops(requset), new NetSubscriber<ShopListResponse>() {
            @Override
            public void response(ShopListResponse response) {
                if (mIHomeView != null) {
                    mIHomeView.showMoreList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIHomeView != null) {
                    mIHomeView.onError();
                }
            }
        });

    }

    /**
     * 今日新铺-无转让费等数据接口
     */
    public void getShopCount() {
        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getShopCount(), new NetSubscriber<ShopCount>() {
            @Override
            public void response(ShopCount response) {
                if (mIHomeView != null) {
                    mIHomeView.showShopCount(response);
                }
            }

        });
    }

    /**
     * 广告banner
     */
    public void getBanner() {
        HashMap<String ,String> hashMap = new HashMap<>();
        hashMap.put("position","0");
        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getBanners(hashMap), new NetSubscriber<BannerResponse>() {
            @Override
            public void response(BannerResponse response) {
                if (mIHomeView != null) {
                    mIHomeView.showBanners(response);
                }
            }

        });
    }

    public void getIsUnReader() {
        HashMap<String ,Object> hashMap = new HashMap<>();
        hashMap.put("receiveType","0");
        ToolsUtil.subscribe(ToolsUtil.createService(PersonService.class).getUnReadMsg(hashMap), new NoneNetSubscriber<UnReadMsg>() {
            @Override
            public void response(UnReadMsg response) {
                if (mIHomeView != null) {
                    mIHomeView.isUnReadMsg(response);
                }
            }

        });
    }

}
