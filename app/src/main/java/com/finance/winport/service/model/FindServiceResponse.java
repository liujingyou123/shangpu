package com.finance.winport.service.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by jge on 17/5/18.
 */

public class FindServiceResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : {"loadObject":{"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"200000","loanMaturity":"2","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":"2017-05-12 11:10:52"},"shopObject":{"id":27,"coverImg":"http://test3.jpg","visitCount":null,"address":"武东路2012号"},"isNew":0}
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
         * loadObject : {"smsVerifyCode":null,"messageId":null,"picVerifyCode":null,"picVerifyId":null,"customerId":null,"loanLimit":"200000","loanMaturity":"2","contactName":null,"contactMobile":null,"subscribeTime":null,"status":"已受理","applyTime":"2017-05-12 11:10:52"}
         * shopObject : {"id":27,"coverImg":"http://test3.jpg","visitCount":null,"address":"武东路2012号"}
         * isNew : 0
         */

        private LoadDTOBean loadObject;
        private ShopObjectBean shopObject;
        private VisitShopObjectBean visitShopObject;
        private int isNew;

        public LoadDTOBean getLoadObject() {
            return loadObject;
        }

        public void setLoadObject(LoadDTOBean loadObject) {
            this.loadObject = loadObject;
        }

        public ShopObjectBean getShopObject() {
            return shopObject;
        }

        public void setShopObject(ShopObjectBean shopObject) {
            this.shopObject = shopObject;
        }

        public VisitShopObjectBean getVisitShopObject() {
            return visitShopObject;
        }

        public void setVisitShopObject(VisitShopObjectBean visitShopObject) {
            this.visitShopObject = visitShopObject;
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
            private String visitCount;
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

            public String getVisitCount() {
                return visitCount;
            }

            public void setVisitCount(String visitCount) {
                this.visitCount = visitCount;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }


        public static class VisitShopObjectBean {


//            {"customerId":36,"shopId":7,"coverImg":"http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/shop/clerk/shop/1477645744608.jpg","address":"武东路财大科技园198号","area":36.0,"rentType":1,"visitCount":1}
            private int customerId;
            private int shopId;
            private String coverImg;
            private String visitCount;
            private String address;
            private String area;
            private String rentType;

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public String getVisitCount() {
                return visitCount;
            }

            public void setVisitCount(String visitCount) {
                this.visitCount = visitCount;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getRentType() {
                return rentType;
            }

            public void setRentType(String rentType) {
                this.rentType = rentType;
            }
        }
    }
}
