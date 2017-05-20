package com.finance.winport.service.presenter;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.service.api.FindServices;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.service.model.OrderShopRequest;
import com.finance.winport.util.ToolsUtil;
import com.sina.weibo.sdk.api.share.Base;

import java.util.HashMap;

/**
 * Created by jge on 17/5/17.
 */

public class SendOrderPresenter {
    private ISendOrderView mServiceView;

    public SendOrderPresenter(ISendOrderView mServiceView) {
        this.mServiceView = mServiceView;
    }



    public void getShopOrderResult(OrderShopRequest request) {

        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).sendOrderShop(request), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
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
