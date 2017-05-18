package com.finance.winport.home.view;

import com.finance.winport.home.model.ShopDetail;

/**
 * Created by liuworkmac on 17/5/17.
 */

public interface IShopDetailView {
    void collectedShop(boolean isSuccess);
    void getShopDetail(ShopDetail shopDetail);
}
