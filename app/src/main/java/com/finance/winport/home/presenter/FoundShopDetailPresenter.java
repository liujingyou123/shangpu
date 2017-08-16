package com.finance.winport.home.presenter;

import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.FoundShopDetailResponse;
import com.finance.winport.home.model.FoundShopListResponse;
import com.finance.winport.home.view.IFoundShopDetailView;
import com.finance.winport.home.view.IFoundShopListView;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by jge on 17/8/16.
 */

public class FoundShopDetailPresenter {
    private IFoundShopDetailView mIFoundShopDetailView;

    public FoundShopDetailPresenter(IFoundShopDetailView mIFoundShopDetailView) {
        this.mIFoundShopDetailView = mIFoundShopDetailView;
    }

    public void getFoundShopList(int contentID) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("contentId",contentID);

        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getFoundShopDetail(map), new LoadingNetSubscriber<FoundShopDetailResponse>() {
            @Override
            public void response(FoundShopDetailResponse response) {
                if (mIFoundShopDetailView != null) {
                    mIFoundShopDetailView.showFoundShopDetail(response);
                }
            }


        });


    }


}
