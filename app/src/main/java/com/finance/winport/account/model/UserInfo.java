package com.finance.winport.account.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by xzw on 2017/5/18.
 */

public class UserInfo extends BaseResponse {
    public DataBean data;

    public static class DataBean {
        public String accessToken;
        public String headPortrait;
    }


}
