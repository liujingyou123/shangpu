package com.finance.winport.trade.model;

import java.util.List;

/**
 * Created by liuworkmac on 17/5/10.
 */

public class Trade {

    private int commentNumber;
    private String content;
    private String dateTime;
    private String likeStatus;
    private int praiseNumber;
    private String title;
    private int topicId;
    private String topicType;
    private String url;
    private List<imgBean> imgList;
    private Href h5obj;
    private String kind;
    private String canBeDelete;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCanBeDelete() {
        return canBeDelete;
    }

    public void setCanBeDelete(String canBeDelete) {
        this.canBeDelete = canBeDelete;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
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

    public String getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(String likeStatus) {
        this.likeStatus = likeStatus;
    }

    public int getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(int praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<imgBean> getImgList() {
        return imgList;
    }

    public void setImgList(List<imgBean> imgList) {
        this.imgList = imgList;
    }

    public Href getH5obj() {
        return h5obj;
    }

    public void setH5obj(Href h5obj) {
        this.h5obj = h5obj;
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
