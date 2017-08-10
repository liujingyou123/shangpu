package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuworkmac on 17/5/22.
 */

public class TradeDetailResponse extends BaseResponse {


    /**
     * attentionContent :
     * commentList : [{"dateTime":"2017-05-10 14:59:31","headPicture":"https://b-ssl.duitang.com/uploads/item/201607/23/20160723192350_RCwMK.png","id":"1","phone":"1681656880"},{"dateTime":"2017-05-10 14:59:31","headPicture":"https://b-ssl.duitang.com/uploads/item/201607/23/20160723192350_RCwMK.png","id":"2","phone":"1681656884"},{"dateTime":"2017-05-10 14:59:31","headPicture":"https://b-ssl.duitang.com/uploads/item/201607/23/20160723192350_RCwMK.png","id":"3","phone":"1681656885"},{"dateTime":"2017-05-10 14:59:31","headPicture":"https://b-ssl.duitang.com/uploads/item/201607/23/20160723192350_RCwMK.png","id":"4","phone":"1681656887"},{"dateTime":"2017-05-10 14:59:31","headPicture":"https://b-ssl.duitang.com/uploads/item/201607/23/20160723192350_RCwMK.png","id":"12","phone":"1681656888"},{"dateTime":"2017-05-10 14:59:31","headPicture":"https://b-ssl.duitang.com/uploads/item/201607/23/20160723192350_RCwMK.png","id":"13","phone":"1681656810"},{"dateTime":"2017-05-10 14:59:31","headPicture":"https://b-ssl.duitang.com/uploads/item/201607/23/20160723192350_RCwMK.png","id":"14","phone":"1681656882"},{"dateTime":"2017-05-17 17:19:25","headPicture":"www.baidu.com","id":"21","phone":"13681656100"},{"dateTime":"2017-05-17 17:22:22","headPicture":"www.baidu.com","id":"22","phone":"13681656100"},{"dateTime":"2017-05-17 17:49:35","headPicture":"www.baidu.com","id":"23","phone":"13681656100"},{"dateTime":"2017-05-17 17:58:15","headPicture":"www.baidu.com","id":"24","phone":"13681656100"},{"dateTime":"2017-05-18 09:51:09","headPicture":"www.baidu.com","id":"25","phone":"13681656100"},{"dateTime":"2017-05-18 17:30:21","headPicture":"www.baidu.com","id":"26","phone":"13681656100"},{"dateTime":"2017-05-19 10:55:36","headPicture":"www.baidu.com","id":"27","phone":"13681656100"},{"dateTime":"2017-05-19 11:01:34","headPicture":"www.baidu.com","id":"28","phone":"13681656100"},{"dateTime":"2017-05-19 19:47:03","headPicture":"www.baidu.com","id":"50","phone":"13681656100"}]
     * commentNumber : 16
     * content : 测试帖子内容
     * dateTime : 2017-05-10 14:59:31
     * imgList : [{"creatTimeFormat":1494399571000,"id":1,"imgIndex":0,"imgUrl":"http://temp.im/178x178","topicId":1,"version":1},{"creatTimeFormat":1494399571000,"id":2,"imgIndex":1,"imgUrl":"http://temp.im/178x178","topicId":1,"version":1},{"creatTimeFormat":1494399571000,"id":3,"imgIndex":2,"imgUrl":"http://temp.im/178x178","topicId":1,"version":1},{"creatTimeFormat":1494399571000,"id":4,"imgIndex":3,"imgUrl":"http://temp.im/178x178","topicId":1,"version":1},{"creatTimeFormat":1494399571000,"id":5,"imgIndex":4,"imgUrl":"http://temp.im/178x178","topicId":1,"version":1},{"creatTimeFormat":1494399571000,"id":6,"imgIndex":5,"imgUrl":"http://temp.im/178x178","topicId":1,"version":1},{"creatTimeFormat":1494399571000,"id":7,"imgIndex":6,"imgUrl":"http://temp.im/178x178","topicId":1,"version":1},{"creatTimeFormat":1494399571000,"id":8,"imgIndex":7,"imgUrl":"http://temp.im/178x178","topicId":1,"version":1},{"creatTimeFormat":1494399571000,"id":9,"imgIndex":8,"imgUrl":"http://temp.im/178x178","topicId":1,"version":1},{"creatTimeFormat":1494399571000,"id":10,"imgIndex":9,"imgUrl":"http://temp.im/178x178","topicId":1,"version":1}]
     * likeStatus : 1
     * praiseNumber : 4
     * publishType : 0
     * title : 测试帖子标题
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
//        private String attentionContent;
        private int commentNumber;
        private String content;
        private String dateTime;
        private int likeStatus;
        private int praiseNumber;
        private int publishType;
        private String title;
        private H5Object h5obj;
        private String nickName;
        private String signature;
        private String headPicture;
        private String canBeDelete;
        private String kind;
        /**
         * dateTime : 2017-05-10 14:59:31
         * headPicture : https://b-ssl.duitang.com/uploads/item/201607/23/20160723192350_RCwMK.png
         * id : 1
         * phone : 1681656880
         */

//        private List<Comment> commentList;
        /**
         * creatTimeFormat : 1494399571000
         * id : 1
         * imgIndex : 0
         * imgUrl : http://temp.im/178x178
         * topicId : 1
         * version : 1
         */

        private ArrayList<Img> imgList;

//        public String getAttentionContent() {
//            return attentionContent;
//        }
//
//        public void setAttentionContent(String attentionContent) {
//            this.attentionContent = attentionContent;
//        }

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

        public int getLikeStatus() {
            return likeStatus;
        }

        public void setLikeStatus(int likeStatus) {
            this.likeStatus = likeStatus;
        }

        public int getPraiseNumber() {
            return praiseNumber;
        }

        public void setPraiseNumber(int praiseNumber) {
            this.praiseNumber = praiseNumber;
        }

        public int getPublishType() {
            return publishType;
        }

        public void setPublishType(int publishType) {
            this.publishType = publishType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHeadPicture() {
            return headPicture;
        }

        public void setHeadPicture(String headPicture) {
            this.headPicture = headPicture;
        }

        public ArrayList<Img> getImgList() {
            return imgList;
        }

        public void setImgList(ArrayList<Img> imgList) {
            this.imgList = imgList;
        }

        public H5Object getH5obj() {
            return h5obj;
        }

        public void setH5obj(H5Object h5obj) {
            this.h5obj = h5obj;
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

        public String getCanBeDelete() {
            return canBeDelete;
        }

        public void setCanBeDelete(String canBeDelete) {
            this.canBeDelete = canBeDelete;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public static class H5Object {
            private String title;
            private String imageUrl;

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

            private String content;
            private String url;


        }

//        public static class Comment {
//            private String dateTime;
//            private String headPicture;
//            private String id;
//            private String phone;
//            private String commentContent;
//            private String isOwn;
//
//            public String getDateTime() {
//                return dateTime;
//            }
//
//            public void setDateTime(String dateTime) {
//                this.dateTime = dateTime;
//            }
//
//            public String getHeadPicture() {
//                return headPicture;
//            }
//
//            public void setHeadPicture(String headPicture) {
//                this.headPicture = headPicture;
//            }
//
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public String getPhone() {
//                return phone;
//            }
//
//            public void setPhone(String phone) {
//                this.phone = phone;
//            }
//
//            public String getCommentContent() {
//                return commentContent;
//            }
//
//            public void setCommentContent(String commentContent) {
//                this.commentContent = commentContent;
//            }
//
//            public String getIsOwn() {
//                return isOwn;
//            }
//
//            public void setIsOwn(String isOwn) {
//                this.isOwn = isOwn;
//            }
//        }

        public static class Img {
            private long createTime;
            private int id;
            private int imgIndex;
            private String imgUrl;
            private int topicId;
            private int version;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getImgIndex() {
                return imgIndex;
            }

            public void setImgIndex(int imgIndex) {
                this.imgIndex = imgIndex;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getTopicId() {
                return topicId;
            }

            public void setTopicId(int topicId) {
                this.topicId = topicId;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }
        }
    }
}
