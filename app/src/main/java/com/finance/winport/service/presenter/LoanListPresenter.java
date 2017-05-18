package com.finance.winport.service.presenter;

import com.finance.winport.net.NetSubscriber;
import com.finance.winport.service.api.FindServices;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by jge on 17/5/17.
 */

public class LoanListPresenter {
    private ILoanListView mServiceView;

    public LoanListPresenter(ILoanListView mServiceView) {
        this.mServiceView = mServiceView;
    }



    public void getLoanList(int pageNum) {

        HashMap<String,Object> map = new HashMap<>();
        map.put("pageSize",20);
        map.put("pageNumber",pageNum);
        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).getLoanList(map), new NetSubscriber<LoanListResponse>() {
            @Override
            public void response(LoanListResponse response) {
                if (mServiceView != null) {
                    mServiceView.shopLoanList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }
}
