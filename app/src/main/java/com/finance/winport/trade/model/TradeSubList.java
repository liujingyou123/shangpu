package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by xzw on 2017/8/4.
 * 生意圈列表（头条、宝典）
 */

public class TradeSubList extends BaseResponse {
    public DataBean data;

    public static class DataBean {
        public int totalSize;
        public int totalPage;
        public int pageSize;
        public int pageNumber;
        public List<TradeSub> data;
    }
}
