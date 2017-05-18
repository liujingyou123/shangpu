package com.finance.winport.mine.presenter;

import com.finance.winport.mine.model.IndustryListResponse;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;

/**
 * Created by liuworkmac on 17/5/16.
 */

public interface IShopFocusView {
    void shopIndustryList(IndustryListResponse response);

}
