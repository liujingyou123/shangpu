package com.finance.winport.service.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by jge on 17/5/17.
 */

public class ShopRentCountResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : {"releaseTotal":150,"avgPeople":30}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * releaseTotal : 150
         * avgPeople : 30
         */

        private int releaseTotal;
        private int avgPeople;

        public int getReleaseTotal() {
            return releaseTotal;
        }

        public void setReleaseTotal(int releaseTotal) {
            this.releaseTotal = releaseTotal;
        }

        public int getAvgPeople() {
            return avgPeople;
        }

        public void setAvgPeople(int avgPeople) {
            this.avgPeople = avgPeople;
        }
    }
}
