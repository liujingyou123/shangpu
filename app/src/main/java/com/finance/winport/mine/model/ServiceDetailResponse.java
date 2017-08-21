package com.finance.winport.mine.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by jge on 17/5/17.
 */

public class ServiceDetailResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : {"id":246,"address":"上海市国定路777号","createTime":"2017-08-16 19:00:55","type":0,"shopStatus":0,"clerkName":"陈双双","clerkHead":"http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/shop/clerk/photo/1502863956563_identity.jpg","clerkPhone":"18717703112","contactPerson":"恩","contactPhone":"15692105593","shopId":null,"discardReason":null}
     * exception : null
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
         * id : 246
         * address : 上海市国定路777号
         * createTime : 2017-08-16 19:00:55
         * type : 0
         * shopStatus : 0
         * clerkName : 陈双双
         * clerkHead : http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/shop/clerk/photo/1502863956563_identity.jpg
         * clerkPhone : 18717703112
         * contactPerson : 恩
         * contactPhone : 15692105593
         * shopId : null
         * discardReason : null
         */

        private int id;
        private String address;
        private String createTime;
        private int type;
        private int shopStatus;
        private String clerkName;
        private String clerkHead;
        private String clerkPhone;
        private String contactPerson;
        private String contactPhone;
        private String shopId;
        private String discardReason;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getShopStatus() {
            return shopStatus;
        }

        public void setShopStatus(int shopStatus) {
            this.shopStatus = shopStatus;
        }

        public String getClerkName() {
            return clerkName;
        }

        public void setClerkName(String clerkName) {
            this.clerkName = clerkName;
        }

        public String getClerkHead() {
            return clerkHead;
        }

        public void setClerkHead(String clerkHead) {
            this.clerkHead = clerkHead;
        }

        public String getClerkPhone() {
            return clerkPhone;
        }

        public void setClerkPhone(String clerkPhone) {
            this.clerkPhone = clerkPhone;
        }

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getDiscardReason() {
            return discardReason;
        }

        public void setDiscardReason(String discardReason) {
            this.discardReason = discardReason;
        }
    }
}
