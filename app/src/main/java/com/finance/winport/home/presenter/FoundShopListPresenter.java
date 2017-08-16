package com.finance.winport.home.presenter;

import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.FoundShopListResponse;
import com.finance.winport.home.model.ShopDetail;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.view.IFoundShopListView;
import com.finance.winport.home.view.IShopListView;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/5/19.
 */

public class FoundShopListPresenter {
    private IFoundShopListView mIFoundShopListView;

    public FoundShopListPresenter(IFoundShopListView mIFoundShopListView) {
        this.mIFoundShopListView = mIFoundShopListView;
    }

    public void getFoundShopList(int pageNum) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("pageSize",10);
        map.put("pageNumber",pageNum);

        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getFoundShopList(map), new NetSubscriber<FoundShopListResponse>() {
            @Override
            public void response(FoundShopListResponse response) {
                if (mIFoundShopListView != null) {
                    mIFoundShopListView.showFoundShopList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIFoundShopListView != null) {
                    mIFoundShopListView.onError();
                }
            }
        });


    }


}
