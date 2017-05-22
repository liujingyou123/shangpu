package com.finance.winport.service.presenter;

import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;
import com.finance.winport.service.model.ShopRentCountResponse;

/**
 * Created by liuworkmac on 17/5/16.
 */

public interface IFindServiceView {
    void shopOrderCount(ShopOrderCountResponse response);
    void findLoanCount(FindLoanCountResponse response);
    void showRentCount(ShopRentCountResponse response);

}
