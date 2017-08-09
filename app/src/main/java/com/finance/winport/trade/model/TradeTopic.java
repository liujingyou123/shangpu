package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/5/10.
 */

public class TradeTopic extends BaseResponse {

    public String publisherId;
    public String nickName;
    public String signature;
    public String headPicture;
    public int commentNumber;
    public String content;
    public String dateTime;
    public String likeStatus;
    public int praiseNumber;
    public String title;
    public int topicId;
    public String publishType;
    public String url;
    public List<imgBean> imgList;
    public Href h5obj;
    public String kind;
    public String canBeDelete;

    public String getPublishType() {
        return publishType;
    }

    public void setPublishType(String publishType) {
        this.publishType = publishType;
    }

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

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
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

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public static class imgBean {
        public String topicId;
        public String imgUrl;
        public String imgIndex;

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
        public String title;
        public String imageUrl;
        public String content;
        public String url;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeTopic trade = (TradeTopic) o;

        return topicId == trade.topicId;

    }

    @Override
    public int hashCode() {
        return topicId;
    }
}
