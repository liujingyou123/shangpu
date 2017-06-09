package com.finance.winport.mine.presenter;

import com.finance.winport.mine.api.MineServices;
import com.finance.winport.mine.model.CommitFocusRequest;
import com.finance.winport.mine.model.IndustryListResponse;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.service.api.FindServices;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;
import com.finance.winport.service.presenter.IFindServiceView;
import com.finance.winport.util.ToolsUtil;
import com.sina.weibo.sdk.api.share.BaseResponse;

/**
 * Created by jge on 17/5/17.
 */

public class ShopFocusPresenter {
    private IShopFocusView mServiceView;

    public ShopFocusPresenter(IShopFocusView mServiceView) {
        this.mServiceView = mServiceView;
    }

    public void getIndustryList() {

        ToolsUtil.subscribe(ToolsUtil.createService(MineServices.class).getIndustryList(), new NetSubscriber<IndustryListResponse>() {
            @Override
            public void response(IndustryListResponse response) {
                if (mServiceView != null) {
                    mServiceView.shopIndustryList(response);
                }
            }
        });

    }

    public void commitFocus(CommitFocusRequest request) {

        ToolsUtil.subscribe(ToolsUtil.createService(MineServices.class).commitFocus(request), new NetSubscriber<com.finance.winport.base.BaseResponse>() {
            @Override
            public void response(com.finance.winport.base.BaseResponse response) {
                if (mServiceView != null) {
                    mServiceView.commitFocus(response);
                }
            }
        });

    }

    public void clear(){
        mServiceView = null;
    }

}
