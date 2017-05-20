package com.finance.winport.mine.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class ScheduleListResponse extends BaseResponse {


    /**
     * errCode : 0
     * data : {"totalCount":21,"pageSize":10,"pageNumber":1,"scheduleList":[{"scheduleId":2,"serviceType":1,"province":"山东省","city":"济南","district":null,"address":null,"orderTime":"2017-05-17 16:12:41","status":0},{"scheduleId":3,"serviceType":2,"province":"河南省","city":"郑州","district":null,"address":null,"orderTime":"2017-05-17 16:12:44","status":2},{"scheduleId":5,"serviceType":1,"province":"江苏省","city":"南京","district":null,"address":null,"orderTime":null,"status":1},{"scheduleId":6,"serviceType":2,"province":"江苏省","city":"南京","district":null,"address":null,"orderTime":null,"status":0},{"scheduleId":7,"serviceType":1,"province":"安徽省","city":"合肥","district":null,"address":null,"orderTime":null,"status":1},{"scheduleId":8,"serviceType":2,"province":"安徽省","city":"合肥","district":null,"address":null,"orderTime":null,"status":1},{"scheduleId":10,"serviceType":1,"province":"安徽省","city":"合肥","district":null,"address":null,"orderTime":null,"status":0},{"scheduleId":25,"serviceType":1,"province":null,"city":null,"district":null,"address":"武东路198号","orderTime":"2017-05-17 19:00:00","status":0},{"scheduleId":26,"serviceType":2,"province":null,"city":null,"district":null,"address":"武东路198号","orderTime":"2017-05-12 18:00:00","status":0},{"scheduleId":27,"serviceType":1,"province":null,"city":null,"district":null,"address":"武东路198号","orderTime":"2017-06-12 19:00:00","status":0}]}
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
         * totalCount : 21
         * pageSize : 10
         * pageNumber : 1
         * scheduleList : [{"scheduleId":2,"serviceType":1,"province":"山东省","city":"济南","district":null,"address":null,"orderTime":"2017-05-17 16:12:41","status":0},{"scheduleId":3,"serviceType":2,"province":"河南省","city":"郑州","district":null,"address":null,"orderTime":"2017-05-17 16:12:44","status":2},{"scheduleId":5,"serviceType":1,"province":"江苏省","city":"南京","district":null,"address":null,"orderTime":null,"status":1},{"scheduleId":6,"serviceType":2,"province":"江苏省","city":"南京","district":null,"address":null,"orderTime":null,"status":0},{"scheduleId":7,"serviceType":1,"province":"安徽省","city":"合肥","district":null,"address":null,"orderTime":null,"status":1},{"scheduleId":8,"serviceType":2,"province":"安徽省","city":"合肥","district":null,"address":null,"orderTime":null,"status":1},{"scheduleId":10,"serviceType":1,"province":"安徽省","city":"合肥","district":null,"address":null,"orderTime":null,"status":0},{"scheduleId":25,"serviceType":1,"province":null,"city":null,"district":null,"address":"武东路198号","orderTime":"2017-05-17 19:00:00","status":0},{"scheduleId":26,"serviceType":2,"province":null,"city":null,"district":null,"address":"武东路198号","orderTime":"2017-05-12 18:00:00","status":0},{"scheduleId":27,"serviceType":1,"province":null,"city":null,"district":null,"address":"武东路198号","orderTime":"2017-06-12 19:00:00","status":0}]
         */

        private int totalCount;
        private int pageSize;
        private int pageNumber;
        private List<ScheduleListBean> scheduleList;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
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

        public List<ScheduleListBean> getScheduleList() {
            return scheduleList;
        }

        public void setScheduleList(List<ScheduleListBean> scheduleList) {
            this.scheduleList = scheduleList;
        }

        public static class ScheduleListBean {
            /**
             * scheduleId : 2
             * serviceType : 1
             * province : 山东省
             * city : 济南
             * district : null
             * address : null
             * orderTime : 2017-05-17 16:12:41
             * status : 0
             */

            private int scheduleId;
            private int serviceType;
            private String province;
            private String city;
            private String district;
            private String address;
            private String orderTime;
            private int status;

            public int getScheduleId() {
                return scheduleId;
            }

            public void setScheduleId(int scheduleId) {
                this.scheduleId = scheduleId;
            }

            public int getServiceType() {
                return serviceType;
            }

            public void setServiceType(int serviceType) {
                this.serviceType = serviceType;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
