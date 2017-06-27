package com.finance.winport.service.presenter;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.service.api.FindServices;
import com.finance.winport.service.model.FindLoanRequest;
import com.finance.winport.service.model.OrderShopRequest;
import com.finance.winport.util.ToolsUtil;

/**
 * Created by jge on 17/5/17.
 */

public class FindLoanPresenter {
    private IFindLoanView mServiceView;

    public FindLoanPresenter(IFindLoanView mServiceView) {
        this.mServiceView = mServiceView;
    }



    public void getFindLoanResult(FindLoanRequest request) {

        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).sendFindLoan(request), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mServiceView != null) {
                    mServiceView.showSendFindLoanResult(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }


}
