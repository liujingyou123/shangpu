package com.finance.winport.tab.model;

import com.finance.winport.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xzw on 2017/5/17.
 * 约看次数和排名,收藏次数和排名,浏览次数和排名
 */

public class AppointRanking extends BaseResponse {

    /**
     * errMsg : null
     * errCode : 0
     * data : {"rate":"64%","total":1}
     */
    public DataBean data;

    public static class DataBean {
        /**
         * rate : 64%
         * total : 1
         */

        public String rate;
        public String total;
    }
}
