package com.finance.winport.home.view;

import com.finance.winport.home.model.ShopListResponse;

/**
 * Created by liuworkmac on 17/5/16.
 */

public interface IHomeView {
    void showShopList(ShopListResponse response);
    void showMoreList(ShopListResponse response);

    void onError();
}
