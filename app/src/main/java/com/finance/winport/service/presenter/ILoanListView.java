package com.finance.winport.service.presenter;

import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;

/**
 * Created by liuworkmac on 17/5/16.
 */

public interface ILoanListView {
    void shopLoanList(LoanListResponse response);

}
