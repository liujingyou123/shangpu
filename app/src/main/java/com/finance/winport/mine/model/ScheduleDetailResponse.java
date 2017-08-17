package com.finance.winport.mine.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by jge on 17/5/17.
 */

public class ScheduleDetailResponse extends BaseResponse {


    /**
     * errCode : 0
     * data : {"picUrl":"http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/shop/clerk/photo/1502863956563_identity.jpg","serviceStatus":null,"clerkName":"陈双双","clerkPhone":"18717703112","applyTime":"2017-08-16 14:30:00","scheduleId":"11","serviceType":3,"contactName":null,"contactPhone":null,"province":null,"city":null,"district":null,"address":"上海市杨浦区仁德路67弄-10支弄-1号","orderTime":"2017-08-16 14:30:00","completeTime":null,"status":0,"shopId":null}
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
         * picUrl : http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/shop/clerk/photo/1502863956563_identity.jpg
         * serviceStatus : null
         * clerkName : 陈双双
         * clerkPhone : 18717703112
         * applyTime : 2017-08-16 14:30:00
         * scheduleId : 11
         * serviceType : 3
         * contactName : null
         * contactPhone : null
         * province : null
         * city : null
         * district : null
         * address : 上海市杨浦区仁德路67弄-10支弄-1号
         * orderTime : 2017-08-16 14:30:00
         * completeTime : null
         * status : 0
         * shopId : null
         */

        private String picUrl;
        private String serviceStatus;
        private String clerkName;
        private String clerkPhone;
        private String applyTime;
        private String scheduleId;
        private int serviceType;
        private String contactName;
        private String contactPhone;
        private String province;
        private String city;
        private String district;
        private String address;
        private String orderTime;
        private String completeTime;
        private int status;
        private String shopId;
        private String bizId;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getServiceStatus() {
            return serviceStatus;
        }

        public void setServiceStatus(String serviceStatus) {
            this.serviceStatus = serviceStatus;
        }

        public String getClerkName() {
            return clerkName;
        }

        public void setClerkName(String clerkName) {
            this.clerkName = clerkName;
        }

        public String getClerkPhone() {
            return clerkPhone;
        }

        public void setClerkPhone(String clerkPhone) {
            this.clerkPhone = clerkPhone;
        }

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public String getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(String scheduleId) {
            this.scheduleId = scheduleId;
        }

        public int getServiceType() {
            return serviceType;
        }

        public void setServiceType(int serviceType) {
            this.serviceType = serviceType;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getCompleteTime() {
            return completeTime;
        }

        public void setCompleteTime(String completeTime) {
            this.completeTime = completeTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getBizId() {
            return bizId;
        }

        public void setBizId(String bizId) {
            this.bizId = bizId;
        }
    }
}
