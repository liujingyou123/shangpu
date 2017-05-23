package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/5/22.
 */

public class MyTopicResponse extends BaseResponse {


    /**
     * start : 0
     * query : {"userId":32,"pageSize":10,"pageNumber":1}
     * pageSize : 10
     * data : [{"praiseNumber":0,"publisherId":null,"canBeDelete":null,"dateTime":"2017-05-22 19:33:05","url":null,"kind":0,"likeStatus":"0","content":"这是我发的帖子内容","title":"我要发帖子","imgList":[],"topicType":null,"topicId":201,"h5obj":null,"commentNumber":0}]
     * pageNumber : 1
     * totalSize : 1
     * totalPage : 1
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int start;
        /**
         * userId : 32
         * pageSize : 10
         * pageNumber : 1
         */

        private QueryBean query;
        private int pageSize;
        private int pageNumber;
        private int totalSize;
        private int totalPage;
        /**
         * praiseNumber : 0
         * publisherId : null
         * canBeDelete : null
         * dateTime : 2017-05-22 19:33:05
         * url : null
         * kind : 0
         * likeStatus : 0
         * content : 这是我发的帖子内容
         * title : 我要发帖子
         * imgList : []
         * topicType : null
         * topicId : 201
         * h5obj : null
         * commentNumber : 0
         */

        private List<MyTopic> data;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
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

        public int getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<MyTopic> getData() {
            return data;
        }

        public void setData(List<MyTopic> data) {
            this.data = data;
        }

        public static class QueryBean {
            private int userId;
            private int pageSize;
            private int pageNumber;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
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
        }

        public static class MyTopic {
            private int praiseNumber;
            private String publisherId;
            private String canBeDelete;
            private String dateTime;
            private String url;
            private int kind;
            private String likeStatus;
            private String content;
            private String title;
            private Object topicType;
            private int topicId;
            private Href h5obj;
            private int commentNumber;
            private List<imgBean> imgList;

            public int getPraiseNumber() {
                return praiseNumber;
            }

            public void setPraiseNumber(int praiseNumber) {
                this.praiseNumber = praiseNumber;
            }

            public String getPublisherId() {
                return publisherId;
            }

            public void setPublisherId(String publisherId) {
                this.publisherId = publisherId;
            }

            public String getCanBeDelete() {
                return canBeDelete;
            }

            public void setCanBeDelete(String canBeDelete) {
                this.canBeDelete = canBeDelete;
            }

            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }

            public Object getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getKind() {
                return kind;
            }

            public void setKind(int kind) {
                this.kind = kind;
            }

            public String getLikeStatus() {
                return likeStatus;
            }

            public void setLikeStatus(String likeStatus) {
                this.likeStatus = likeStatus;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getTopicType() {
                return topicType;
            }

            public void setTopicType(Object topicType) {
                this.topicType = topicType;
            }

            public int getTopicId() {
                return topicId;
            }

            public void setTopicId(int topicId) {
                this.topicId = topicId;
            }

            public Href getH5obj() {
                return h5obj;
            }

            public void setH5obj(Href h5obj) {
                this.h5obj = h5obj;
            }

            public int getCommentNumber() {
                return commentNumber;
            }

            public void setCommentNumber(int commentNumber) {
                this.commentNumber = commentNumber;
            }

            public List<imgBean> getImgList() {
                return imgList;
            }

            public void setImgList(List<imgBean> imgList) {
                this.imgList = imgList;
            }

            public static class imgBean {
                private String topicId;
                private String imgUrl;
                private String imgIndex;

                public String getTopicId() {
                    return topicId;
                }

                public void setTopicId(String topicId) {
                    this.topicId = topicId;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getImgIndex() {
                    return imgIndex;
                }

                public void setImgIndex(String imgIndex) {
                    this.imgIndex = imgIndex;
                }
            }

            public static class Href {
                private String title;
                private String imageUrl;
                private String content;
                private String url;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getImageUrl() {
                    return imageUrl;
                }

                public void setImageUrl(String imageUrl) {
                    this.imageUrl = imageUrl;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
