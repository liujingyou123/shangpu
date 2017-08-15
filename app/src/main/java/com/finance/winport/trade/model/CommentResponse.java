package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/5/23.
 */

public class CommentResponse extends BaseResponse{


    /**
     * query : {}
     * data : [{"id":"9","headPicture":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E4%B8%9A%E5%8A%A1%E5%91%98/2017/05/15/IMG_1494818789325_12268.jpg","phone":"13681656104","dateTime":"2017-05-10 14:59:31","content":"业务员评论内容","isOwn":0,"commentatorId":4},{"id":"10","headPicture":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E4%B8%9A%E5%8A%A1%E5%91%98/2017/05/15/IMG_1494818789325_12268.jpg","phone":"13681656106","dateTime":"2017-05-10 14:59:31","content":"业务员评论内容","isOwn":0,"commentatorId":6},{"id":"19","headPicture":"www.baidu.com","phone":"13681656100","dateTime":"2017-05-10 14:59:31","content":"业务员评论内容","isOwn":0,"commentatorId":1},{"id":"20","headPicture":"www.baidu.com","phone":"13681656101","dateTime":"2017-05-10 14:59:31","content":"业务员评论内容","isOwn":0,"commentatorId":2}]
     * totalSize : 5
     * pageSize : 4
     * pageNumber : 1
     * totalPage : 1
     * start : 0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int totalSize;
        private int pageSize;
        private int pageNumber;
        private int totalPage;
        private int start;
        /**
         * id : 9
         * headPicture : http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E4%B8%9A%E5%8A%A1%E5%91%98/2017/05/15/IMG_1494818789325_12268.jpg
         * phone : 13681656104
         * dateTime : 2017-05-10 14:59:31
         * content : 业务员评论内容
         * isOwn : 0
         * commentatorId : 4
         */

        private List<Comment> data;

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

        public List<Comment> getData() {
            return data;
        }

        public void setData(List<Comment> data) {
            this.data = data;
        }

        public static class Comment {
            private String id;
            private String headPicture;
            private String nickName;
            private String signature;
            private String dateTime;
            private String content;
            private int isOwn;
            private int commentatorId;
            private int isReply;
            private int parentId;
            private int parentNickName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getHeadPicture() {
                return headPicture;
            }

            public void setHeadPicture(String headPicture) {
                this.headPicture = headPicture;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getIsOwn() {
                return isOwn;
            }

            public void setIsOwn(int isOwn) {
                this.isOwn = isOwn;
            }

            public int getCommentatorId() {
                return commentatorId;
            }

            public void setCommentatorId(int commentatorId) {
                this.commentatorId = commentatorId;
            }

            public int getIsReply() {
                return isReply;
            }

            public void setIsReply(int isReply) {
                this.isReply = isReply;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getParentNickName() {
                return parentNickName;
            }

            public void setParentNickName(int parentNickName) {
                this.parentNickName = parentNickName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Comment comment = (Comment) o;

                return id != null ? id.equals(comment.id) : comment.id == null;

            }

            @Override
            public int hashCode() {
                return id != null ? id.hashCode() : 0;
            }
        }
    }
}
