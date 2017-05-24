package com.finance.winport.home.view;

import com.finance.winport.home.model.BannerResponse;
import com.finance.winport.home.model.RegionResponse;
import com.finance.winport.home.model.ShopCount;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.tab.model.UnReadMsg;

/**
 * Created by liuworkmac on 17/5/16.
 */

public interface IHomeView {
    void showShopList(ShopListResponse response);

    void showMoreList(ShopListResponse response);

    void showShopCount(ShopCount response);

    void showBanners(BannerResponse response);

    void isUnReadMsg(UnReadMsg readMsg);

    void onError();

    void showPersonalInfo(PersonalInfoResponse response);
}
