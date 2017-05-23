package com.finance.winport.trade.model;

import java.util.List;

/**
 * Created by liuworkmac on 17/5/23.
 */

public class CommentResponse {


    /**
     * data : [{"commentatorId":6,"content":"业务员评论内容","dateTime":"2017-05-10 14:59:31","headPicture":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E4%B8%9A%E5%8A%A1%E5%91%98/2017/05/15/IMG_1494818789325_12268.jpg","id":"10","isOwn":0,"phone":"13681656106"},{"commentatorId":1,"content":"业务员评论内容","dateTime":"2017-05-10 14:59:31","headPicture":"www.baidu.com","id":"19","isOwn":0,"phone":"13681656100"},{"commentatorId":2,"content":"业务员评论内容","dateTime":"2017-05-10 14:59:31","headPicture":"www.baidu.com","id":"20","isOwn":0,"phone":"13681656101"}]
     * pageNumber : 1
     * pageSize : 3
     * query : {}
     * start : 0
     * totalPage : 1
     * totalSize : 4
     */

    private DataBean data;
    /**
     * data : {"data":[{"commentatorId":6,"content":"业务员评论内容","dateTime":"2017-05-10 14:59:31","headPicture":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E4%B8%9A%E5%8A%A1%E5%91%98/2017/05/15/IMG_1494818789325_12268.jpg","id":"10","isOwn":0,"phone":"13681656106"},{"commentatorId":1,"content":"业务员评论内容","dateTime":"2017-05-10 14:59:31","headPicture":"www.baidu.com","id":"19","isOwn":0,"phone":"13681656100"},{"commentatorId":2,"content":"业务员评论内容","dateTime":"2017-05-10 14:59:31","headPicture":"www.baidu.com","id":"20","isOwn":0,"phone":"13681656101"}],"pageNumber":1,"pageSize":3,"query":{},"start":0,"totalPage":1,"totalSize":4}
     * errCode : 0
     * errMsg : null
     * success : false
     */

    private int errCode;
    private Object errMsg;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public Object getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(Object errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        private int pageNumber;
        private int pageSize;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * commentatorId : 6
         * content : 业务员评论内容
         * dateTime : 2017-05-10 14:59:31
         * headPicture : http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E4%B8%9A%E5%8A%A1%E5%91%98/2017/05/15/IMG_1494818789325_12268.jpg
         * id : 10
         * isOwn : 0
         * phone : 13681656106
         */

        private List<Comment> data;

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

        public List<Comment> getData() {
            return data;
        }

        public void setData(List<Comment> data) {
            this.data = data;
        }

        public static class Comment {
            private int commentatorId;
            private String content;
            private String dateTime;
            private String headPicture;
            private String id;
            private int isOwn;
            private String phone;

            public int getCommentatorId() {
                return commentatorId;
            }

            public void setCommentatorId(int commentatorId) {
                this.commentatorId = commentatorId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }

            public String getHeadPicture() {
                return headPicture;
            }

            public void setHeadPicture(String headPicture) {
                this.headPicture = headPicture;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getIsOwn() {
                return isOwn;
            }

            public void setIsOwn(int isOwn) {
                this.isOwn = isOwn;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
