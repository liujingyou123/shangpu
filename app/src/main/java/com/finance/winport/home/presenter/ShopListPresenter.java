package com.finance.winport.home.presenter;

import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.view.IShopListView;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;

/**
 * Created by liuworkmac on 17/5/19.
 */

public class ShopListPresenter {
    private IShopListView mIShopListView;

    public ShopListPresenter(IShopListView mIShopListView) {
        this.mIShopListView = mIShopListView;
    }

    public void getShopList(ShopRequset requset) {
        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getShops(requset), new NetSubscriber<ShopListResponse>() {
            @Override
            public void response(ShopListResponse response) {
                if (mIShopListView != null) {
                    mIShopListView.showShopList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIShopListView != null) {
                    mIShopListView.onError();
                }
            }
        });

    }

    public void getMoreShopList(ShopRequset requset) {
        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getShops(requset), new NetSubscriber<ShopListResponse>() {
            @Override
            public void response(ShopListResponse response) {
                if (mIShopListView != null) {
                    mIShopListView.showMoreList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIShopListView != null) {
                    mIShopListView.onError();
                }
            }
        });

    }
}
