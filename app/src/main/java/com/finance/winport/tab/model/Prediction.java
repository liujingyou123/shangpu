package com.finance.winport.tab.model;

import com.finance.winport.base.BaseResponse;

import java.io.Serializable;

/**
 * Created by xzw on 2017/5/17.
 * 测吉凶
 */

public class Prediction extends BaseResponse implements Serializable {

    public DataBean data;

    public static class DataBean {
        public int moneyLuck;//财运
        public int guestLuck;//客运
        public int future;//前景
        public String description;//描述
    }
}
