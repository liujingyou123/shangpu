package com.finance.winport.service.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by jge on 17/5/17.
 */

public class FindLoanCountResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : {"people":17,"sum":30}
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
         * people : 17
         * sum : 30
         */

        private int people;
        private int sum;

        public int getPeople() {
            return people;
        }

        public void setPeople(int people) {
            this.people = people;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }
    }
}
