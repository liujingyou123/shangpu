package com.finance.winport.tab.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by xzw on 2017/5/19.
 */

public class NotifyList extends BaseResponse {

    public DataBean data;

    public static class DataBean {
        public int totalSize;
        public int totalPage;
        public int pageSize;
        public int pageNumber;
        public List<ServiceNoticeBean> serviceNoticeRespDTOList;
        public List<BussinessNoticeBean> bussinessNoticeRespDTOList;

        public static class ServiceNoticeBean {
            public String serviceType;
            public String digest;
            public int bussinessId;
            public String notifyTime;
            public int status;
            public String shopAddress;
            public String oldSchedule;
            public String currentSchedule;
            public int scheduleCount;
            public String serviceName;
            public String reason;
            public int serviceStatus;
            public String serviceTel;
        }

        public static class BussinessNoticeBean {
            public String bussinessType;
            public String digest;
            public int status;
            public int bussinessId;
            public String notifyTime;
            public String postName;
            public String contentOrReason;
            public String commentOrOprationTime;
        }

    }

}
