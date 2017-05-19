package com.finance.winport.tab.model;

import com.finance.winport.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xzw on 2017/5/17.
 * 预约统计
 */

public class AppointStatistics extends BaseResponse {

    /**
     * errMsg : null
     * errCode : 0
     * data : {"amount":20,"visit":21}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * amount : 20
         * visit : 21
         */

        public int amount;
        public int visit;
    }
}
