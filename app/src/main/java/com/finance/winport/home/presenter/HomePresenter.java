package com.finance.winport.home.presenter;

import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.view.IHomeView;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;

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
        });

    }
}
