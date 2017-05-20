package com.finance.winport.home.view;

import com.finance.winport.home.model.CollectionResponse;
import com.finance.winport.home.model.ShopDetail;

/**
 * Created by liuworkmac on 17/5/17.
 */

public interface IShopDetailView {
    void collectedShop(CollectionResponse response);
    void cancelCollectedShop(boolean isSuccess);
    void getShopDetail(ShopDetail shopDetail);
}
