package com.finance.winport.service.presenter;

import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.view.IHomeView;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.service.api.FindServices;
import com.finance.winport.service.model.ShopOrderCountResponse;
import com.finance.winport.util.ToolsUtil;

/**
 * Created by jge on 17/5/17.
 */

public class ServicePresenter {
    private IFindServiceView mServiceView;

    public ServicePresenter(IFindServiceView mServiceView) {
        this.mServiceView = mServiceView;
    }

    public void getOrderShopCount() {

        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).getOrderCount(), new NetSubscriber<ShopOrderCountResponse>() {
            @Override
            public void response(ShopOrderCountResponse response) {
                if (mServiceView != null) {
                    mServiceView.shopOrderCount(response);
                }
            }
        });

    }
}
