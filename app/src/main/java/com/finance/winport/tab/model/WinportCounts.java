package com.finance.winport.tab.model;

import com.finance.winport.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xzw on 2017/5/20.
 */

public class WinportCounts extends BaseResponse {

    /**
     * errCode : 0
     * data : {"issuerCount":0,"browseCount":298,"scheduleCount":0,"collectedCount":31,"visitCount":1}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * issuerCount : 0
         * browseCount : 298
         * scheduleCount : 0
         * collectedCount : 31
         * visitCount : 1
         */

        public int issuerCount;
        public int browseCount;
        public int scheduleCount;
        public int collectedCount;
        public int visitCount;
    }
}
