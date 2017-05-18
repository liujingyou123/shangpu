package com.finance.winport.home.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by liuworkmac on 17/5/18.
 */

public class ShopCount extends BaseResponse {


    /**
     * countHundredArea : 7
     * countNearStation : 5
     * countNewShop : 0
     * countNoTransferFee : 0
     * countReleasel : null
     * countShop : null
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int countHundredArea;
        private int countNearStation;
        private int countNewShop;
        private int countNoTransferFee;

        public int getCountHundredArea() {
            return countHundredArea;
        }

        public void setCountHundredArea(int countHundredArea) {
            this.countHundredArea = countHundredArea;
        }

        public int getCountNearStation() {
            return countNearStation;
        }

        public void setCountNearStation(int countNearStation) {
            this.countNearStation = countNearStation;
        }

        public int getCountNewShop() {
            return countNewShop;
        }

        public void setCountNewShop(int countNewShop) {
            this.countNewShop = countNewShop;
        }

        public int getCountNoTransferFee() {
            return countNoTransferFee;
        }

        public void setCountNoTransferFee(int countNoTransferFee) {
            this.countNoTransferFee = countNoTransferFee;
        }
    }
}
