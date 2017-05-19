package com.finance.winport.service.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by jge on 17/5/18.
 */

public class FindServiceResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : {"loadDTO":{"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"200000","loanMaturity":"2","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":"2017-05-12 11:10:52"},"shopObject":{"id":27,"coverImg":"http://test3.jpg","visitCount":null,"address":"武东路2012号"},"isNew":0}
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
         * loadDTO : {"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"200000","loanMaturity":"2","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":"2017-05-12 11:10:52"}
         * shopObject : {"id":27,"coverImg":"http://test3.jpg","visitCount":null,"address":"武东路2012号"}
         * isNew : 0
         */

        private LoadDTOBean loadDTO;
        private ShopObjectBean shopObject;
        private int isNew;

        public LoadDTOBean getLoadDTO() {
            return loadDTO;
        }

        public void setLoadDTO(LoadDTOBean loadDTO) {
            this.loadDTO = loadDTO;
        }

        public ShopObjectBean getShopObject() {
            return shopObject;
        }

        public void setShopObject(ShopObjectBean shopObject) {
            this.shopObject = shopObject;
        }

        public int getIsNew() {
            return isNew;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
        }

        public static class LoadDTOBean {
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

        public static class ShopObjectBean {
            /**
             * id : 27
             * coverImg : http://test3.jpg
             * visitCount : null
             * address : 武东路2012号
             */

            private int id;
            private String coverImg;
            private Object visitCount;
            private String address;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public Object getVisitCount() {
                return visitCount;
            }

            public void setVisitCount(Object visitCount) {
                this.visitCount = visitCount;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
