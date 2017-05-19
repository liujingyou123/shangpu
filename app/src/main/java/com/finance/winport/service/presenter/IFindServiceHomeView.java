package com.finance.winport.service.presenter;

import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.FindServiceResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;

/**
 * Created by jge on 17/5/18.
 */

public interface IFindServiceHomeView {
    void showServiceHome(FindServiceResponse response);

}
