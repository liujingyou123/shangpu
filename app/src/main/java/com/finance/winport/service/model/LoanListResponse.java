package com.finance.winport.service.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class LoanListResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : {"query":4,"data":[{"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"200000","loanMaturity":"2","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":"2017-05-12 11:10:52"},{"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"100000","loanMaturity":"30","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":"2017-05-01 11:05:37"},{"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"100000","loanMaturity":"20","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":"2017-05-01 11:05:37"},{"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"50000","loanMaturity":"40","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":null}],"totalSize":4,"pageSize":20,"pageNumber":1,"totalPage":1,"start":0}
     */

    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * query : 4
         * data : [{"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"200000","loanMaturity":"2","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":"2017-05-12 11:10:52"},{"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"100000","loanMaturity":"30","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":"2017-05-01 11:05:37"},{"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"100000","loanMaturity":"20","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":"2017-05-01 11:05:37"},{"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"50000","loanMaturity":"40","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":null}]
         * totalSize : 4
         * pageSize : 20
         * pageNumber : 1
         * totalPage : 1
         * start : 0
         */

        private int query;
        private int totalSize;
        private int pageSize;
        private int pageNumber;
        private int totalPage;
        private int start;
        private List<DataBean> data;

        public int getQuery() {
            return query;
        }

        public void setQuery(int query) {
            this.query = query;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * smsVerifyCode : null
             * messageId : null
             * picVerifyCode : null
             * picVerifyId : null
             * customerId : null
             * loanLimit : 200000
             * loanMaturity : 2
             * contactName : null
             * contactMobile : null
             * subscribeTime : null
             * status : 已受理
             * applyTime : 2017-05-12 11:10:52
             */

            private Object smsVerifyCode;
            private Object messageId;
            private Object picVerifyCode;
            private Object picVerifyId;
            private Object customerId;
            private String loanLimit;
            private String loanMaturity;
            private Object contactName;
            private Object contactMobile;
            private Object subscribeTime;
            private String status;
            private String applyTime;

            public Object getSmsVerifyCode() {
                return smsVerifyCode;
            }

            public void setSmsVerifyCode(Object smsVerifyCode) {
                this.smsVerifyCode = smsVerifyCode;
            }

            public Object getMessageId() {
                return messageId;
            }

            public void setMessageId(Object messageId) {
                this.messageId = messageId;
            }

            public Object getPicVerifyCode() {
                return picVerifyCode;
            }

            public void setPicVerifyCode(Object picVerifyCode) {
                this.picVerifyCode = picVerifyCode;
            }

            public Object getPicVerifyId() {
                return picVerifyId;
            }

            public void setPicVerifyId(Object picVerifyId) {
                this.picVerifyId = picVerifyId;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public String getLoanLimit() {
                return loanLimit;
            }

            public void setLoanLimit(String loanLimit) {
                this.loanLimit = loanLimit;
            }

            public String getLoanMaturity() {
                return loanMaturity;
            }

            public void setLoanMaturity(String loanMaturity) {
                this.loanMaturity = loanMaturity;
            }

            public Object getContactName() {
                return contactName;
            }

            public void setContactName(Object contactName) {
                this.contactName = contactName;
            }

            public Object getContactMobile() {
                return contactMobile;
            }

            public void setContactMobile(Object contactMobile) {
                this.contactMobile = contactMobile;
            }

            public Object getSubscribeTime() {
                return subscribeTime;
            }

            public void setSubscribeTime(Object subscribeTime) {
                this.subscribeTime = subscribeTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getApplyTime() {
                return applyTime;
            }

            public void setApplyTime(String applyTime) {
                this.applyTime = applyTime;
            }
        }
    }
}
