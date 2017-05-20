package com.finance.winport.trade.view;

import com.finance.winport.trade.model.TradeCircleResponse;

/**
 * Created by liuworkmac on 17/5/20.
 */

public interface ITradeCircleView {
    void showTradeCircle(TradeCircleResponse response);
    void showMoreTradeCircle(TradeCircleResponse response);
    void zanTopic(boolean isSuccess, int position , String topId);
    void cancelTopic(boolean isSuccess, int position , String topId);
    void onError();
}
