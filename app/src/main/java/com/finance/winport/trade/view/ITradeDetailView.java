package com.finance.winport.trade.view;

import com.finance.winport.trade.model.TradeDetailResponse;

/**
 * Created by liuworkmac on 17/5/22.
 */

public interface ITradeDetailView {
    void showTradeDetail(TradeDetailResponse response);

    void zanTopic(boolean isSuccess, String topId);

    void cancelTopic(boolean isSuccess, String topId);

    void commentTopic(boolean isSuccess);

}
