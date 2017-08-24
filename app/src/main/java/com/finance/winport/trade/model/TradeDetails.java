package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by xzw on 2017/8/4.
 * 生意圈详情（头条、宝典）
 */

public class TradeDetails extends BaseResponse {
    public DataBean data;

    public static class DataBean {
        public String contentId;
        public String title;
        public String content;
        public String desc;
        public String source;
        public List<TradeTag.Tag> tagList;
        public String image;
        public int kind;
        public int viewCount;
        public String dateTime;
        public int badCount;
        public int goodCount;
    }
}
