package com.finance.winport.trade.view;

import com.finance.winport.trade.model.TradeHome;

/**
 */

public interface ITradeHomeView {

    void setAdapter(TradeHome data);

    void showError(String errorMsg);

    void zanTopic(boolean success, String topicId, int groupPosition, int childPosition);

    void cancelZanTopic(boolean success, String topicId, int groupPosition, int childPosition);

    void deleteTopic(boolean success, String topicId, int groupPosition, int childPosition);
}
