package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/5/20.
 */

public class TradeCircleResponse extends BaseResponse{

    /**
     * page : {"data":[{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":7,"topicType":"0","url":"www.baidu.com"},{"commentNumber":16,"content":"测试帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[{"imgIndex":0,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":1,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":2,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":3,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":4,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":5,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":6,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":7,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":8,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":9,"imgUrl":"http://temp.im/178x178","topicId":1}],"likeStatus":"1","praiseNumber":4,"title":"测试帖子标题","topicId":1,"topicType":"1","url":"www.baidu.com"},{"commentNumber":4,"content":"业务员资讯内容","dateTime":"2017-05-10 14:59:31","imgList":[{"imgIndex":1,"imgUrl":"http://temp.im/178x178","topicId":33},{"imgIndex":2,"imgUrl":"http://temp.im/178x178","topicId":33},{"imgIndex":3,"imgUrl":"http://temp.im/178x178","topicId":33}],"likeStatus":"1","praiseNumber":2,"title":"测试业务员资讯标题00","topicId":33,"topicType":"1","url":"www.jianshu.com"},{"commentNumber":3,"content":"业务员帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[{"imgIndex":1,"imgUrl":"http://temp.im/178x178","topicId":19},{"imgIndex":2,"imgUrl":"http://temp.im/178x178","topicId":19},{"imgIndex":3,"imgUrl":"http://temp.im/178x178","topicId":19},{"imgIndex":4,"imgUrl":"http://temp.im/178x178","topicId":19}],"likeStatus":"1","praiseNumber":2,"title":"测试业务员帖子标题00","topicId":19,"topicType":"1","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":2,"topicType":"0","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":3,"topicType":"0","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":4,"topicType":"0","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":5,"topicType":"0","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":6,"topicType":"0","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":8,"topicType":"0","url":"www.baidu.com"}],"pageNumber":1,"pageSize":10,"query":{"pageNumber":1,"pageSize":10,"type":1},"start":0,"totalPage":4,"totalSize":40}
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
         * data : [{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":7,"topicType":"0","url":"www.baidu.com"},{"commentNumber":16,"content":"测试帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[{"imgIndex":0,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":1,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":2,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":3,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":4,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":5,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":6,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":7,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":8,"imgUrl":"http://temp.im/178x178","topicId":1},{"imgIndex":9,"imgUrl":"http://temp.im/178x178","topicId":1}],"likeStatus":"1","praiseNumber":4,"title":"测试帖子标题","topicId":1,"topicType":"1","url":"www.baidu.com"},{"commentNumber":4,"content":"业务员资讯内容","dateTime":"2017-05-10 14:59:31","imgList":[{"imgIndex":1,"imgUrl":"http://temp.im/178x178","topicId":33},{"imgIndex":2,"imgUrl":"http://temp.im/178x178","topicId":33},{"imgIndex":3,"imgUrl":"http://temp.im/178x178","topicId":33}],"likeStatus":"1","praiseNumber":2,"title":"测试业务员资讯标题00","topicId":33,"topicType":"1","url":"www.jianshu.com"},{"commentNumber":3,"content":"业务员帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[{"imgIndex":1,"imgUrl":"http://temp.im/178x178","topicId":19},{"imgIndex":2,"imgUrl":"http://temp.im/178x178","topicId":19},{"imgIndex":3,"imgUrl":"http://temp.im/178x178","topicId":19},{"imgIndex":4,"imgUrl":"http://temp.im/178x178","topicId":19}],"likeStatus":"1","praiseNumber":2,"title":"测试业务员帖子标题00","topicId":19,"topicType":"1","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":2,"topicType":"0","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":3,"topicType":"0","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":4,"topicType":"0","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":5,"topicType":"0","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":6,"topicType":"0","url":"www.baidu.com"},{"commentNumber":0,"content":"商户帖子内容","dateTime":"2017-05-10 14:59:31","imgList":[],"likeStatus":"0","praiseNumber":0,"title":"测试商户帖子标题00","topicId":8,"topicType":"0","url":"www.baidu.com"}]
         * pageNumber : 1
         * pageSize : 10
         * query : {"pageNumber":1,"pageSize":10,"type":1}
         * start : 0
         * totalPage : 4
         * totalSize : 40
         */

        private PageBean page;
        private String createTopicOpen;

        public String getCreateTopicOpen() {
            return createTopicOpen;
        }

        public void setCreateTopicOpen(String createTopicOpen) {
            this.createTopicOpen = createTopicOpen;
        }

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public static class PageBean {
            private int pageNumber;
            private int pageSize;
            /**
             * pageNumber : 1
             * pageSize : 10
             * type : 1
             */

            private QueryBean query;
            private int start;
            private int totalPage;
            private int totalSize;
            /**
             * commentNumber : 0
             * content : 商户帖子内容
             * dateTime : 2017-05-10 14:59:31
             * imgList : []
             * likeStatus : 0
             * praiseNumber : 0
             * title : 测试商户帖子标题00
             * topicId : 7
             * topicType : 0
             * url : www.baidu.com
             */

            private List<TradeTopic> data;

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public QueryBean getQuery() {
                return query;
            }

            public void setQuery(QueryBean query) {
                this.query = query;
            }

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalSize() {
                return totalSize;
            }

            public void setTotalSize(int totalSize) {
                this.totalSize = totalSize;
            }

            public List<TradeTopic> getData() {
                return data;
            }

            public void setData(List<TradeTopic> data) {
                this.data = data;
            }

            public static class QueryBean {
                private int pageNumber;
                private int pageSize;
                private int type;

                public int getPageNumber() {
                    return pageNumber;
                }

                public void setPageNumber(int pageNumber) {
                    this.pageNumber = pageNumber;
                }

                public int getPageSize() {
                    return pageSize;
                }

                public void setPageSize(int pageSize) {
                    this.pageSize = pageSize;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }

        }
    }
}
