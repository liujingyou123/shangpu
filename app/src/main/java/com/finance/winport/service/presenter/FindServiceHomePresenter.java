package com.finance.winport.service.presenter;

import com.finance.winport.net.NetSubscriber;
import com.finance.winport.service.api.FindServices;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.FindServiceResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;
import com.finance.winport.util.ToolsUtil;

/**
 * Created by jge on 17/5/17.
 */

public class FindServiceHomePresenter {
    private IFindServiceHomeView mServiceView;

    public FindServiceHomePresenter(IFindServiceHomeView mServiceView) {
        this.mServiceView = mServiceView;
    }

    public void getFindServiceHome() {

        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).getFindService(), new NetSubscriber<FindServiceResponse>() {
            @Override
            public void response(FindServiceResponse response) {
                if (mServiceView != null) {
                    mServiceView.showServiceHome(response);
                }
            }
        });

    }


}
