package com.finance.winport.map.model;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.model.Tag;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class MapShopDetailResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : {"isFace":1,"rentWay":0,"updateTime":"2017-05-17","blockName":"五角场","contactCount":8,"featureList":[{"id":3,"color":"#FFA73B","name":"煤气罐"},{"id":4,"color":"#FFA73B","name":"天然气"}],"id":1,"rentTypeName":"直租","distance":null,"districtName":"杨浦区","area":300,"coverImg":null,"districtId":310110,"address":"上海武东路198号","blockId":100,"transferFee":300000,"visitCount":89,"rent":30000,"modifyTime":1495025460000}
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
         * isFace : 1
         * rentWay : 0
         * updateTime : 2017-05-17
         * blockName : 五角场
         * contactCount : 8
         * featureList : [{"id":3,"color":"#FFA73B","name":"煤气罐"},{"id":4,"color":"#FFA73B","name":"天然气"}]
         * id : 1
         * rentTypeName : 直租
         * distance : null
         * districtName : 杨浦区
         * area : 300
         * coverImg : null
         * districtId : 310110
         * address : 上海武东路198号
         * blockId : 100
         * transferFee : 300000
         * visitCount : 89
         * rent : 30000
         * modifyTime : 1495025460000
         */

        private String address;
        private float area;
        private int blockId;
        private String blockName;
        private int contactCount;
        private String coverImg;
        private String distance;
        private int districtId;
        private String districtName;
        private int id;
        private int isFace;
        private long modifyTime;
        private float rent;
        private String rentTypeName;
        private int rentWay;
        private int transferFee;
        private String updateTime;
        private int visitCount;
        /**
         * color : null
         * id : 1
         * name : 上水
         */

        private List<Tag> featureList;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public float getArea() {
            return area;
        }

        public void setArea(float area) {
            this.area = area;
        }

        public int getBlockId() {
            return blockId;
        }

        public void setBlockId(int blockId) {
            this.blockId = blockId;
        }

        public String getBlockName() {
            return blockName;
        }

        public void setBlockName(String blockName) {
            this.blockName = blockName;
        }

        public int getContactCount() {
            return contactCount;
        }

        public void setContactCount(int contactCount) {
            this.contactCount = contactCount;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsFace() {
            return isFace;
        }

        public void setIsFace(int isFace) {
            this.isFace = isFace;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public float getRent() {
            return rent;
        }

        public void setRent(float rent) {
            this.rent = rent;
        }

        public String getRentTypeName() {
            return rentTypeName;
        }

        public void setRentTypeName(String rentTypeName) {
            this.rentTypeName = rentTypeName;
        }

        public int getRentWay() {
            return rentWay;
        }

        public void setRentWay(int rentWay) {
            this.rentWay = rentWay;
        }

        public int getTransferFee() {
            return transferFee;
        }

        public void setTransferFee(int transferFee) {
            this.transferFee = transferFee;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getVisitCount() {
            return visitCount;
        }

        public void setVisitCount(int visitCount) {
            this.visitCount = visitCount;
        }

        public List<Tag> getFeatureList() {
            return featureList;
        }

        public void setFeatureList(List<Tag> featureList) {
            this.featureList = featureList;
        }
    }
}
