package com.finance.winport.home.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by jge on 17/8/15.
 */

public class FoundShopDetailResponse extends BaseResponse{


    /**
     * errMsg : null
     * errCode : 0
     * data : {"contentId":32,"title":"kjkjk3","content":"<p>jkjkjklsafs日日日<\/p><p><img src=\"http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/image/wangpu_file_img_633cc313-e079-74f4-d70a-d4bcaf4a0ef9.png\" alt=\"wangpu_file_img_633cc313-e079-74f4-d70a-d4bcaf4a0ef9.png\"><br><\/p>","image":"http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/image/wangpu_contentImg_img_154fa6ce-b43f-ec31-e92c-e9fbd1c7bb85.png","tagList":[],"dateTime":"2017-08-08 10:03:00","shopList":[{"recommendWord":"e","shopId":2,"title":null,"districtId":null,"districtName":null,"blockId":null,"blockName":null,"area":null,"rentType":1,"coverImg":"http://pic129.nipic.com/file/20170516/20614752_221945581000_2.jpg","rent":null,"transferFee":null,"isFace":null,"featureList":null,"distance":null,"updateTime":"2017-08-15 19:48:34.0"},{"recommendWord":"www","shopId":1,"title":null,"districtId":null,"districtName":null,"blockId":null,"blockName":null,"area":null,"rentType":1,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","rent":null,"transferFee":null,"isFace":null,"featureList":null,"distance":null,"updateTime":"2017-08-14 18:42:38.0"}]}
     * exception : null
     */

    private DataBean data;
    private Object exception;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }

    public static class DataBean {
        /**
         * contentId : 32
         * title : kjkjk3
         * content : <p>jkjkjklsafs日日日</p><p><img src="http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/image/wangpu_file_img_633cc313-e079-74f4-d70a-d4bcaf4a0ef9.png" alt="wangpu_file_img_633cc313-e079-74f4-d70a-d4bcaf4a0ef9.png"><br></p>
         * image : http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/image/wangpu_contentImg_img_154fa6ce-b43f-ec31-e92c-e9fbd1c7bb85.png
         * tagList : []
         * dateTime : 2017-08-08 10:03:00
         * shopList : [{"recommendWord":"e","shopId":2,"title":null,"districtId":null,"districtName":null,"blockId":null,"blockName":null,"area":null,"rentType":1,"coverImg":"http://pic129.nipic.com/file/20170516/20614752_221945581000_2.jpg","rent":null,"transferFee":null,"isFace":null,"featureList":null,"distance":null,"updateTime":"2017-08-15 19:48:34.0"},{"recommendWord":"www","shopId":1,"title":null,"districtId":null,"districtName":null,"blockId":null,"blockName":null,"area":null,"rentType":1,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","rent":null,"transferFee":null,"isFace":null,"featureList":null,"distance":null,"updateTime":"2017-08-14 18:42:38.0"}]
         */

        private int contentId;
        private String title;
        private String content;
        private String image;
        private String dateTime;
        private String desc;
        private List<?> tagList;
        private List<ShopListBean> shopList;

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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public List<?> getTagList() {
            return tagList;
        }

        public void setTagList(List<?> tagList) {
            this.tagList = tagList;
        }

        public List<ShopListBean> getShopList() {
            return shopList;
        }

        public void setShopList(List<ShopListBean> shopList) {
            this.shopList = shopList;
        }

        public static class ShopListBean {
            /**
             * recommendWord : e
             * shopId : 2
             * title : null
             * districtId : null
             * districtName : null
             * blockId : null
             * blockName : null
             * area : null
             * rentType : 1
             * coverImg : http://pic129.nipic.com/file/20170516/20614752_221945581000_2.jpg
             * rent : null
             * transferFee : null
             * isFace : null
             * featureList : null
             * distance : null
             * updateTime : 2017-08-15 19:48:34.0
             */

            private String recommendWord;
            private int shopId;
            private String title;
            private String districtId;
            private String districtName;
            private String blockId;
            private String blockName;
            private String area;
            private int rentType;
            private String coverImg;
            private double rent;
            private double transferFee;
            private int isFace;
            private List<Tag> featureList;
            private String distance;
            private String updateTime;

            public String getRecommendWord() {
                return recommendWord;
            }

            public void setRecommendWord(String recommendWord) {
                this.recommendWord = recommendWord;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDistrictId() {
                return districtId;
            }

            public void setDistrictId(String districtId) {
                this.districtId = districtId;
            }

            public String getDistrictName() {
                return districtName;
            }

            public void setDistrictName(String districtName) {
                this.districtName = districtName;
            }

            public String getBlockId() {
                return blockId;
            }

            public void setBlockId(String blockId) {
                this.blockId = blockId;
            }

            public String getBlockName() {
                return blockName;
            }

            public void setBlockName(String blockName) {
                this.blockName = blockName;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public int getRentType() {
                return rentType;
            }

            public void setRentType(int rentType) {
                this.rentType = rentType;
            }

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public double getRent() {
                return rent;
            }

            public void setRent(double rent) {
                this.rent = rent;
            }

            public double getTransferFee() {
                return transferFee;
            }

            public void setTransferFee(double transferFee) {
                this.transferFee = transferFee;
            }

            public int getIsFace() {
                return isFace;
            }

            public void setIsFace(int isFace) {
                this.isFace = isFace;
            }



            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public List<Tag> getFeatureList() {
                return featureList;
            }

            public void setFeatureList(List<Tag> featureList) {
                this.featureList = featureList;
            }
        }
    }
}
