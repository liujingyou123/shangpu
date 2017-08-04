package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by xzw on 2017/8/4.
 * 行业头条
 */

public class TradeHead extends BaseResponse {
    public String id;
    public String title;
    public String content;
    public String source;
    public String tags;
    public String image;
    public boolean kind;
    public String viewCount;
    public String dateTime;
}
