package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by xzw on 2017/8/4.
 */

public class TradeHome extends BaseResponse {
    public DataBean data;

    public static class DataBean {
        public List<TradeHead> headlineList;
        public List<TradeBible> bibleList;
        public List<TradeTopic> topicList;
    }
}
