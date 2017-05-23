package com.finance.winport.home.presenter;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.CollectionResponse;
import com.finance.winport.home.model.ShopDetail;
import com.finance.winport.home.view.IShopDetailView;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.tab.net.PersonService;
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

        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).collectShop(hashMap), new LoadingNetSubscriber<CollectionResponse>() {
            @Override
            public void response(CollectionResponse response) {
                if (mIShopDetailView != null) {
                    mIShopDetailView.collectedShop(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIShopDetailView != null) {
                    mIShopDetailView.collectedShop(null);
                }
            }
        });
    }

    public void cancelCollectShop(String collectId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("collectedId", collectId);
        ToolsUtil.subscribe(ToolsUtil.createService(PersonService.class).cancelCollection(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIShopDetailView != null) {
                    mIShopDetailView.cancelCollectedShop(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIShopDetailView != null) {
                    mIShopDetailView.cancelCollectedShop(false);
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

    public void recordCall(String shopId, String phone) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("shopId", shopId);
        hashMap.put("phone", phone);
        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).recordCall(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
            }
        });
    }

}
