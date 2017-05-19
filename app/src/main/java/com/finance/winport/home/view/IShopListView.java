package com.finance.winport.home.view;

import com.finance.winport.home.model.ShopListResponse;

/**
 * Created by liuworkmac on 17/5/19.
 */

public interface IShopListView {
    void showShopList(ShopListResponse response);

    void showMoreList(ShopListResponse response);

    void onError();

}
