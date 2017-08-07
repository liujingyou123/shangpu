package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by xzw on 2017/8/4.
 */

public class TradeHome extends BaseResponse {
    public DataBean data;

    public static class DataBean {
        public TradeHead headlineList;
        public TradeCanon bibleList;
        public TradeCommunity topicList;
    }
}
