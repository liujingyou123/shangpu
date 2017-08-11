package com.finance.winport.trade.view;

import com.finance.winport.trade.model.TradeDetails;
import com.finance.winport.trade.model.TradeSubList;
import com.finance.winport.trade.model.TradeTag;

/**
 */

public interface ITradeSubDetailsView {


    void showError(String errorMsg);

    void setDetails(TradeDetails response);

    void praise(boolean success);

    void downPraise(boolean success);
}
