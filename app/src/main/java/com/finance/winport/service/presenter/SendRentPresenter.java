package com.finance.winport.service.presenter;

import com.finance.winport.net.NetSubscriber;
import com.finance.winport.service.api.FindServices;
import com.finance.winport.service.model.OrderShopRequest;
import com.finance.winport.service.model.RentShopRequest;
import com.finance.winport.service.model.SendOrderShopResponse;
import com.finance.winport.util.ToolsUtil;

/**
 * Created by jge on 17/5/17.
 */

public class SendRentPresenter {
    private ISendRentView mServiceView;

    public SendRentPresenter(ISendRentView mServiceView) {
        this.mServiceView = mServiceView;
    }



    public void getShopRentResult(RentShopRequest request) {

        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).sendRentShop(request), new NetSubscriber<SendOrderShopResponse>() {
            @Override
            public void response(SendOrderShopResponse response) {
                if (mServiceView != null) {
                    mServiceView.shopSendRentResult(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }


}
