package com.finance.winport.trade.view;

import com.finance.winport.trade.model.TradeHead;
import com.finance.winport.trade.model.TradeHome;
import com.finance.winport.trade.model.TradeSub;
import com.finance.winport.trade.model.TradeTag;

/**
 */

public interface ITradeSubView {

    void setAdapter(TradeSub data);

    void setHeadInfo(TradeTag data);

    void showError(String errorMsg);

}
