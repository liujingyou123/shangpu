package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by xzw on 2017/8/7.
 */

public class TradeTag extends BaseResponse {
    public List<Tag> data;

    public static class Tag {
        public String tagId;
        public String tagIcon;
        public String tagName;
    }
}
