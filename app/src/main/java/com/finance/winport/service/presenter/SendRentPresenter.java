package com.finance.winport.service.presenter;

import com.finance.winport.net.NetSubscriber;
import com.finance.winport.service.api.FindServices;
import com.finance.winport.service.model.OrderShopRequest;
import com.finance.winport.service.model.SendOrderShopResponse;
import com.finance.winport.util.ToolsUtil;

/**
 * Created by jge on 17/5/17.
 */

public class SendRentPresenter {
    private ISendOrderView mServiceView;

    public SendRentPresenter(ISendOrderView mServiceView) {
        this.mServiceView = mServiceView;
    }



    public void getShopOrderResult(OrderShopRequest request) {

        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).sendOrderShop(request), new NetSubscriber<SendOrderShopResponse>() {
            @Override
            public void response(SendOrderShopResponse response) {
                if (mServiceView != null) {
                    mServiceView.shopSendOrderResult(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    public void getShopSignrResult(OrderShopRequest request) {

        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).sendSignShop(request), new NetSubscriber<SendOrderShopResponse>() {
            @Override
            public void response(SendOrderShopResponse response) {
                if (mServiceView != null) {
                    mServiceView.shopSendOrderResult(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }
}
