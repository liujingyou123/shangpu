package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by xzw on 2017/8/4.
 * 生意宝典、行业头条
 */

public class TradeSub extends BaseResponse {
    public String id;
    public String title;
    public String content;
    public String source;
    public List<TradeTag.Tag> tagList;
    public String image;
    public int kind;//0-否，1-是
    public String viewCount;
    public String dateTime;
}
