package com.finance.winport.home.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by jge on 17/8/15.
 */

public class FoundShopListResponse extends BaseResponse{


    /**
     * errMsg : null
     * errCode : 0
     * data : {"query":{"contentType":3,"pageNumber":1,"pageSize":10},"data":[{"contentId":32,"title":"kjkjk3","content":"<p>jkjkjklsafs日日日<\/p><p><img src=\"http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/image/wangpu_file_img_633cc313-e079-74f4-d70a-d4bcaf4a0ef9.png\" alt=\"wangpu_file_img_633cc313-e079-74f4-d70a-d4bcaf4a0ef9.png\"><br><\/p>","image":"http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/image/wangpu_contentImg_img_154fa6ce-b43f-ec31-e92c-e9fbd1c7bb85.png","dateTime":"2017-08-08 10:03:00"},{"contentId":30,"title":"dfsafa","content":"fsfas","image":"rrrr","dateTime":"2017-08-17 09:51:00"},{"contentId":31,"title":"dfsafa","content":"fsfas","image":"rrrr","dateTime":"2017-08-17 09:51:00"}],"totalSize":3,"pageSize":10,"pageNumber":1,"totalPage":1,"start":0}
     * exception : null
     */

    private DataBeanX data;
    private Object exception;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }

    public static class DataBeanX {
        /**
         * query : {"contentType":3,"pageNumber":1,"pageSize":10}
         * data : [{"contentId":32,"title":"kjkjk3","content":"<p>jkjkjklsafs日日日<\/p><p><img src=\"http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/image/wangpu_file_img_633cc313-e079-74f4-d70a-d4bcaf4a0ef9.png\" alt=\"wangpu_file_img_633cc313-e079-74f4-d70a-d4bcaf4a0ef9.png\"><br><\/p>","image":"http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/image/wangpu_contentImg_img_154fa6ce-b43f-ec31-e92c-e9fbd1c7bb85.png","dateTime":"2017-08-08 10:03:00"},{"contentId":30,"title":"dfsafa","content":"fsfas","image":"rrrr","dateTime":"2017-08-17 09:51:00"},{"contentId":31,"title":"dfsafa","content":"fsfas","image":"rrrr","dateTime":"2017-08-17 09:51:00"}]
         * totalSize : 3
         * pageSize : 10
         * pageNumber : 1
         * totalPage : 1
         * start : 0
         */

        private QueryBean query;
        private int totalSize;
        private int pageSize;
        private int pageNumber;
        private int totalPage;
        private int start;
        private List<DataBean> data;

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
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

        public static class QueryBean {
            /**
             * contentType : 3
             * pageNumber : 1
             * pageSize : 10
             */

            private int contentType;
            private int pageNumber;
            private int pageSize;

            public int getContentType() {
                return contentType;
            }

            public void setContentType(int contentType) {
                this.contentType = contentType;
            }

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
        }

        public static class DataBean {
            /**
             * contentId : 32
             * title : kjkjk3
             * content : <p>jkjkjklsafs日日日</p><p><img src="http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/image/wangpu_file_img_633cc313-e079-74f4-d70a-d4bcaf4a0ef9.png" alt="wangpu_file_img_633cc313-e079-74f4-d70a-d4bcaf4a0ef9.png"><br></p>
             * image : http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/image/wangpu_contentImg_img_154fa6ce-b43f-ec31-e92c-e9fbd1c7bb85.png
             * dateTime : 2017-08-08 10:03:00
             */

            private int contentId;
            private String title;
            private String content;
            private String image;
            private String dateTime;

            public int getContentId() {
                return contentId;
            }

            public void setContentId(int contentId) {
                this.contentId = contentId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }
        }
    }
}
