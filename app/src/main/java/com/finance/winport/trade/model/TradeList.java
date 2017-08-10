package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by xzw on 2017/8/4.
 * 生意圈列表（头条、宝典）
 */

public class TradeList extends BaseResponse {
    public DataBean data;

    public static class DataBean {
        public String id;
        public String title;
        public String content;
        public String source;
        public List<TradeTag.Tag> tagList;
        public String image;
        public boolean kind;
        public String viewCount;
        public String dateTime;
    }
}
