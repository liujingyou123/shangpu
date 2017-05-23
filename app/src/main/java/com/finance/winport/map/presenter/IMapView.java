package com.finance.winport.map.presenter;

import com.finance.winport.map.model.MapAreaResponse;
import com.finance.winport.map.model.MapShopDetailResponse;
import com.finance.winport.map.model.MapShopResponse;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;

/**
 * Created by liuworkmac on 17/5/16.
 */

public interface IMapView {
    void showMapShop(MapShopResponse response);
    void showRemoveMapShop(MapShopResponse response);
    void showMapArea(MapAreaResponse response);
    void showMapPlate(MapAreaResponse response);
    void showRemoveMapPlate(MapAreaResponse response);
    void showMapShopDetail(MapShopDetailResponse response);

}
