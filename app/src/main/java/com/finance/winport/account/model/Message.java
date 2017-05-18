package com.finance.winport.account.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by xzw on 2017/5/18.
 */

public class Message extends BaseResponse {
    public DataBean data;

    public class DataBean {
        public String messageId;
    }

}
