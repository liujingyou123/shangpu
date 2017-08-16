package com.finance.winport.home.view;

import com.finance.winport.home.model.FoundShopListResponse;
import com.finance.winport.home.model.ShopListResponse;

/**
 * Created by liuworkmac on 17/5/19.
 */

public interface IFoundShopListView {
    void showFoundShopList(FoundShopListResponse response);


    void onError();

}
