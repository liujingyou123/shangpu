package com.finance.winport.service.presenter;

import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.view.IHomeView;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.service.api.FindServices;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;
import com.finance.winport.service.model.ShopRentCountResponse;
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

    public void getFindLoanCount() {

        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).getFindLoanCount(), new NetSubscriber<FindLoanCountResponse>() {
            @Override
            public void response(FindLoanCountResponse response) {
                if (mServiceView != null) {
                    mServiceView.findLoanCount(response);
                }
            }
        });

    }

    public void getShopRentCount() {

        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).getRentCount(), new NetSubscriber<ShopRentCountResponse>() {
            @Override
            public void response(ShopRentCountResponse response) {
                if (mServiceView != null) {
                    mServiceView.showRentCount(response);
                }
            }
        });

    }
}
