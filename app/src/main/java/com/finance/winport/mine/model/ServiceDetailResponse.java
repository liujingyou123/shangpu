package com.finance.winport.mine.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by jge on 17/5/17.
 */

public class ServiceDetailResponse extends BaseResponse {


    /**
     * errCode : 0
     * data : {"scheduleId":"2","contactPhone":"13612245672","picUrl":"www.baidu.com","status":0,"contactName":"alisa测试09","clerkName":"小明","province":"山东省","applyTime":"2017-05-12 10:58:23","orderTime":"2017-05-17 16:12:41","serviceType":1,"city":"济南"}
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
         * scheduleId : 2
         * contactPhone : 13612245672
         * picUrl : www.baidu.com
         * status : 0
         * contactName : alisa测试09
         * clerkName : 小明
         * province : 山东省
         * applyTime : 2017-05-12 10:58:23
         * orderTime : 2017-05-17 16:12:41
         * serviceType : 1
         * city : 济南
         */

        private String scheduleId;
        private String contactPhone;
        private String picUrl;
        private int status;
        private String contactName;
        private String clerkName;
        private String clerkPhone;
        private String province;
        private String applyTime;
        private String orderTime;
        private int serviceType;
        private String city;
        private String district;

        public String getClerkPhone() {
            return clerkPhone;
        }

        public void setClerkPhone(String clerkPhone) {
            this.clerkPhone = clerkPhone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        private String address;

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(String scheduleId) {
            this.scheduleId = scheduleId;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getClerkName() {
            return clerkName;
        }

        public void setClerkName(String clerkName) {
            this.clerkName = clerkName;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public int getServiceType() {
            return serviceType;
        }

        public void setServiceType(int serviceType) {
            this.serviceType = serviceType;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
