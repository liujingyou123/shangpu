package com.finance.winport.service.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class CalendarListResponse extends BaseResponse {


    /**
     * errCode : 0
     * data : {"undoScheduleCount":13,"dateList":[{"scheduleList":[{"province":"山东省","orderTime":"2017-05-23 12:21:23","serviceType":1,"city":"济南"}],"dateString":"2017-05-23"},{"scheduleList":[],"dateString":"2017-05-24"},{"scheduleList":[],"dateString":"2017-05-25"},{"scheduleList":[],"dateString":"2017-05-26"},{"scheduleList":[],"dateString":"2017-05-27"},{"scheduleList":[],"dateString":"2017-05-28"},{"scheduleList":[],"dateString":"2017-05-29"},{"scheduleList":[],"dateString":"2017-05-30"},{"scheduleList":[],"dateString":"2017-05-31"},{"scheduleList":[],"dateString":"2017-06-01"},{"scheduleList":[],"dateString":"2017-06-02"},{"scheduleList":[],"dateString":"2017-06-03"},{"scheduleList":[],"dateString":"2017-06-04"},{"scheduleList":[],"dateString":"2017-06-05"},{"scheduleList":[],"dateString":"2017-06-06"},{"scheduleList":[],"dateString":"2017-06-07"},{"scheduleList":[],"dateString":"2017-06-08"},{"scheduleList":[],"dateString":"2017-06-09"},{"scheduleList":[{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"","orderTime":"2017-06-10 12:00:00","serviceType":1}],"dateString":"2017-06-10"},{"scheduleList":[],"dateString":"2017-06-11"},{"scheduleList":[{"address":"武东路198号","orderTime":"2017-06-12 19:00:00","serviceType":1},{"address":"武东路198号","orderTime":"2017-06-12 19:00:00","serviceType":1},{"address":"武东路198号","orderTime":"2017-06-12 19:00:00","serviceType":1}],"dateString":"2017-06-12"},{"scheduleList":[],"dateString":"2017-06-13"},{"scheduleList":[],"dateString":"2017-06-14"},{"scheduleList":[],"dateString":"2017-06-15"},{"scheduleList":[],"dateString":"2017-06-16"},{"scheduleList":[],"dateString":"2017-06-17"},{"scheduleList":[],"dateString":"2017-06-18"},{"scheduleList":[],"dateString":"2017-06-19"},{"scheduleList":[],"dateString":"2017-06-20"},{"scheduleList":[],"dateString":"2017-06-21"},{"scheduleList":[],"dateString":"2017-06-22"},{"scheduleList":[],"dateString":"2017-06-23"},{"scheduleList":[],"dateString":"2017-06-24"},{"scheduleList":[],"dateString":"2017-06-25"},{"scheduleList":[],"dateString":"2017-06-26"},{"scheduleList":[],"dateString":"2017-06-27"},{"scheduleList":[],"dateString":"2017-06-28"},{"scheduleList":[],"dateString":"2017-06-29"},{"scheduleList":[],"dateString":"2017-06-30"},{"scheduleList":[],"dateString":"2017-07-01"},{"scheduleList":[],"dateString":"2017-07-02"},{"scheduleList":[],"dateString":"2017-07-03"},{"scheduleList":[],"dateString":"2017-07-04"},{"scheduleList":[],"dateString":"2017-07-05"},{"scheduleList":[],"dateString":"2017-07-06"},{"scheduleList":[],"dateString":"2017-07-07"},{"scheduleList":[],"dateString":"2017-07-08"},{"scheduleList":[],"dateString":"2017-07-09"},{"scheduleList":[],"dateString":"2017-07-10"},{"scheduleList":[],"dateString":"2017-07-11"},{"scheduleList":[],"dateString":"2017-07-12"},{"scheduleList":[],"dateString":"2017-07-13"},{"scheduleList":[],"dateString":"2017-07-14"},{"scheduleList":[],"dateString":"2017-07-15"},{"scheduleList":[],"dateString":"2017-07-16"},{"scheduleList":[],"dateString":"2017-07-17"}]}
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
         * undoScheduleCount : 13
         * dateList : [{"scheduleList":[{"province":"山东省","orderTime":"2017-05-23 12:21:23","serviceType":1,"city":"济南"}],"dateString":"2017-05-23"},{"scheduleList":[],"dateString":"2017-05-24"},{"scheduleList":[],"dateString":"2017-05-25"},{"scheduleList":[],"dateString":"2017-05-26"},{"scheduleList":[],"dateString":"2017-05-27"},{"scheduleList":[],"dateString":"2017-05-28"},{"scheduleList":[],"dateString":"2017-05-29"},{"scheduleList":[],"dateString":"2017-05-30"},{"scheduleList":[],"dateString":"2017-05-31"},{"scheduleList":[],"dateString":"2017-06-01"},{"scheduleList":[],"dateString":"2017-06-02"},{"scheduleList":[],"dateString":"2017-06-03"},{"scheduleList":[],"dateString":"2017-06-04"},{"scheduleList":[],"dateString":"2017-06-05"},{"scheduleList":[],"dateString":"2017-06-06"},{"scheduleList":[],"dateString":"2017-06-07"},{"scheduleList":[],"dateString":"2017-06-08"},{"scheduleList":[],"dateString":"2017-06-09"},{"scheduleList":[{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"上海武东路198号","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"","orderTime":"2017-06-10 12:00:00","serviceType":1},{"address":"","orderTime":"2017-06-10 12:00:00","serviceType":1}],"dateString":"2017-06-10"},{"scheduleList":[],"dateString":"2017-06-11"},{"scheduleList":[{"address":"武东路198号","orderTime":"2017-06-12 19:00:00","serviceType":1},{"address":"武东路198号","orderTime":"2017-06-12 19:00:00","serviceType":1},{"address":"武东路198号","orderTime":"2017-06-12 19:00:00","serviceType":1}],"dateString":"2017-06-12"},{"scheduleList":[],"dateString":"2017-06-13"},{"scheduleList":[],"dateString":"2017-06-14"},{"scheduleList":[],"dateString":"2017-06-15"},{"scheduleList":[],"dateString":"2017-06-16"},{"scheduleList":[],"dateString":"2017-06-17"},{"scheduleList":[],"dateString":"2017-06-18"},{"scheduleList":[],"dateString":"2017-06-19"},{"scheduleList":[],"dateString":"2017-06-20"},{"scheduleList":[],"dateString":"2017-06-21"},{"scheduleList":[],"dateString":"2017-06-22"},{"scheduleList":[],"dateString":"2017-06-23"},{"scheduleList":[],"dateString":"2017-06-24"},{"scheduleList":[],"dateString":"2017-06-25"},{"scheduleList":[],"dateString":"2017-06-26"},{"scheduleList":[],"dateString":"2017-06-27"},{"scheduleList":[],"dateString":"2017-06-28"},{"scheduleList":[],"dateString":"2017-06-29"},{"scheduleList":[],"dateString":"2017-06-30"},{"scheduleList":[],"dateString":"2017-07-01"},{"scheduleList":[],"dateString":"2017-07-02"},{"scheduleList":[],"dateString":"2017-07-03"},{"scheduleList":[],"dateString":"2017-07-04"},{"scheduleList":[],"dateString":"2017-07-05"},{"scheduleList":[],"dateString":"2017-07-06"},{"scheduleList":[],"dateString":"2017-07-07"},{"scheduleList":[],"dateString":"2017-07-08"},{"scheduleList":[],"dateString":"2017-07-09"},{"scheduleList":[],"dateString":"2017-07-10"},{"scheduleList":[],"dateString":"2017-07-11"},{"scheduleList":[],"dateString":"2017-07-12"},{"scheduleList":[],"dateString":"2017-07-13"},{"scheduleList":[],"dateString":"2017-07-14"},{"scheduleList":[],"dateString":"2017-07-15"},{"scheduleList":[],"dateString":"2017-07-16"},{"scheduleList":[],"dateString":"2017-07-17"}]
         */

        private int undoScheduleCount;
        private List<DateListBean> dateList;

        public int getUndoScheduleCount() {
            return undoScheduleCount;
        }

        public void setUndoScheduleCount(int undoScheduleCount) {
            this.undoScheduleCount = undoScheduleCount;
        }

        public List<DateListBean> getDateList() {
            return dateList;
        }

        public void setDateList(List<DateListBean> dateList) {
            this.dateList = dateList;
        }

        public static class DateListBean {
            /**
             * scheduleList : [{"province":"山东省","orderTime":"2017-05-23 12:21:23","serviceType":1,"city":"济南"}]
             * dateString : 2017-05-23
             */

            private String dateString;
            private List<ScheduleListBean> scheduleList;

            public String getDateString() {
                return dateString;
            }

            public void setDateString(String dateString) {
                this.dateString = dateString;
            }

            public List<ScheduleListBean> getScheduleList() {
                return scheduleList;
            }

            public void setScheduleList(List<ScheduleListBean> scheduleList) {
                this.scheduleList = scheduleList;
            }

            public static class ScheduleListBean {
                /**
                 * province : 山东省
                 * orderTime : 2017-05-23 12:21:23
                 * serviceType : 1
                 * city : 济南
                 */

                private String province;
                private String scheduleId;
                private String address;
                private String status;
                private String district;
                private String orderTime;
                private int serviceType;
                private String city;

                public String getScheduleId() {
                    return scheduleId;
                }

                public void setScheduleId(String scheduleId) {
                    this.scheduleId = scheduleId;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
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
    }
}
