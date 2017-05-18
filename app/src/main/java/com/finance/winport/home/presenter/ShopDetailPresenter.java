package com.finance.winport.home.presenter;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.ShopDetail;
import com.finance.winport.home.view.IShopDetailView;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/5/17.
 */

public class ShopDetailPresenter {
    private IShopDetailView mIShopDetailView;

    public ShopDetailPresenter(IShopDetailView mIShopDetailView) {
        this.mIShopDetailView = mIShopDetailView;
    }

    public void collectShop(String shopId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("shopId", shopId);

        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).collectShop(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIShopDetailView != null) {
                    mIShopDetailView.collectedShop(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIShopDetailView != null) {
                    mIShopDetailView.collectedShop(false);
                }
            }
        });
    }

    public void getShopDetail(String shopId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("shopId", shopId);

        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getShopDetail(hashMap), new LoadingNetSubscriber<ShopDetail>() {
            @Override
            public void response(ShopDetail response) {
                if (mIShopDetailView != null) {
                    mIShopDetailView.getShopDetail(response);
                }
            }
        });
    }
}
