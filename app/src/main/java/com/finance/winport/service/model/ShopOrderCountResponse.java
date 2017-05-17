package com.finance.winport.service.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by jge on 17/5/17.
 */

public class ShopOrderCountResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : {"amount":20,"visit":16}
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
         * amount : 20
         * visit : 16
         */

        private int amount;
        private int visit;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getVisit() {
            return visit;
        }

        public void setVisit(int visit) {
            this.visit = visit;
        }
    }
}
