package com.finance.winport.mine.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class MyServiceListResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : {"query":null,"data":[{"totalCount":0,"pageSize":10,"pageNumber":1,"id":76,"address":null,"createTime":"2017-08-16 17:57:15","type":1,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":32,"address":null,"createTime":null,"type":2,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":75,"address":null,"createTime":"2017-08-16 17:50:17","type":1,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":245,"address":"上海市徐汇区宛平南路420弄1-14号","createTime":"2017-08-16 18:46:33","type":0,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":74,"address":null,"createTime":"2017-08-16 17:45:27","type":1,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":77,"address":null,"createTime":"2017-08-16 17:58:07","type":1,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":73,"address":null,"createTime":"2017-08-16 17:45:14","type":1,"shopStatus":0}],"totalSize":7,"pageSize":20,"pageNumber":1,"totalPage":1,"start":0}
     * exception : null
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
         * query : null
         * data : [{"totalCount":0,"pageSize":10,"pageNumber":1,"id":76,"address":null,"createTime":"2017-08-16 17:57:15","type":1,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":32,"address":null,"createTime":null,"type":2,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":75,"address":null,"createTime":"2017-08-16 17:50:17","type":1,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":245,"address":"上海市徐汇区宛平南路420弄1-14号","createTime":"2017-08-16 18:46:33","type":0,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":74,"address":null,"createTime":"2017-08-16 17:45:27","type":1,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":77,"address":null,"createTime":"2017-08-16 17:58:07","type":1,"shopStatus":0},{"totalCount":0,"pageSize":10,"pageNumber":1,"id":73,"address":null,"createTime":"2017-08-16 17:45:14","type":1,"shopStatus":0}]
         * totalSize : 7
         * pageSize : 20
         * pageNumber : 1
         * totalPage : 1
         * start : 0
         */

        private Object query;
        private int totalSize;
        private int pageSize;
        private int pageNumber;
        private int totalPage;
        private int start;
        private List<DataBean> data;

        public Object getQuery() {
            return query;
        }

        public void setQuery(Object query) {
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
             * totalCount : 0
             * pageSize : 10
             * pageNumber : 1
             * id : 76
             * address : null
             * createTime : 2017-08-16 17:57:15
             * type : 1
             * shopStatus : 0
             */

            private int id;
            private String address;
            private String createTime;
            private int type;
            private int shopStatus;

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
        }
    }
}
