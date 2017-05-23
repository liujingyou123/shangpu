package com.finance.winport.home.model;

import android.support.annotation.NonNull;

import com.finance.winport.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuworkmac on 17/5/17.
 */

public class ShopDetail extends BaseResponse implements Serializable{


    /**
     * address : 浦东南路2003号
     * area : 300
     * blockId : 17
     * blockName :
     * clerkId : 12
     * clerkName : 黄小哈
     * compactResidue : 12
     * contactTel : 17301878912
     * contacter : 黄哈哈
     * coverImg : null
     * deposit : 30000
     * depth : null
     * distance : null
     * districtId : 310115
     * districtName :
     * electricRate : 5.6
     * featureList : [{"color":null,"id":3,"name":"煤气罐"}]
     * floor : 5
     * gasRate : 5
     * height : null
     * id : 4
     * imageList : [{"imgIndex":1,"imgUrl":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","isCover":1}]
     * industryList : [{"name":"西餐","parentId":1}]
     * isCollected : 4
     * isDelete : 0
     * isFace : 1
     * isShow : 1
     * isVisit : 4
     * issueShopTime : 2017.05.11
     * latitude : 31.2120758500
     * longitude : 121.5130140700
     * modifyTime : null
     * name : 餐馆
     * nearInfoList : [{"industryId":13,"industryName":"电影演出","name":"百丽宫影城(环贸iapm店)","nearId":12,"nearImg":[],"nearSeat":0,"parentId":null},{"industryId":15,"industryName":"餐馆","name":"haxnbauer海森堡™现代德国餐厅(环球金融中心店)","nearId":13,"nearImg":[],"nearSeat":2,"parentId":1}]
     * operateStatus : 1
     * parentId : 1
     * propertyRate : 1.5
     * rent : 30000
     * rentStatus : 1
     * rentTypeName : 房东直租
     * rentWay : 0
     * shopCode : null
     * shopName : 手拉手餐厅分店2号
     * supportList : [{"color":null,"id":5,"name":"三相电380V"}]
     * totalFloor : 13
     * transferFee : 300000
     * updateTime : null
     * waterRate : 2.4
     * width : 60
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean implements Serializable{
        private String address;
        private float area;
        private int blockId;
        private String blockName;
        private int clerkId;
        private String clerkName;
        private int compactResidue;
        private String contactTel;
        private String contacter;
        private Object coverImg;
        private int deposit;
        private float depth;
        private Object distance;
        private int districtId;
        private String districtName;
        private double electricRate;
        private int floor;
        private int gasRate;
        private float height;
        private int id;
        private int isCollected;
        private int isDelete;
        private int isFace;
        private int isShow;
        private int isVisit;
        private String issueShopTime;
        private String latitude;
        private String longitude;
        private Object modifyTime;
        private String name;
        private int operateStatus;
        private int parentId;
        private double propertyRate;
        private int rent;
        private int rentStatus;
        private String rentTypeName;
        private int rentWay;
        private Object shopCode;
        private String shopName;
        private int totalFloor;
        private int transferFee;
        private Object updateTime;
        private double waterRate;
        private int width;
        private int visitCount;
        private int contactCount;
        /**
         * color : null
         * id : 3
         * name : 煤气罐
         */

        private List<Tag> featureList;
        /**
         * imgIndex : 1
         * imgUrl : http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg
         * isCover : 1
         */

        private List<ImageListBean> imageList;
        /**
         * name : 西餐
         * parentId : 1
         */

        private List<Tag> industryList;
        /**
         * industryId : 13
         * industryName : 电影演出
         * name : 百丽宫影城(环贸iapm店)
         * nearId : 12
         * nearImg : []
         * nearSeat : 0
         * parentId : null
         */

        private List<NearInfoListBean> nearInfoList;
        /**
         * color : null
         * id : 5
         * name : 三相电380V
         */

        private List<Tag> supportList;

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

        public int getClerkId() {
            return clerkId;
        }

        public void setClerkId(int clerkId) {
            this.clerkId = clerkId;
        }

        public String getClerkName() {
            return clerkName;
        }

        public void setClerkName(String clerkName) {
            this.clerkName = clerkName;
        }

        public int getCompactResidue() {
            return compactResidue;
        }

        public void setCompactResidue(int compactResidue) {
            this.compactResidue = compactResidue;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public String getContacter() {
            return contacter;
        }

        public void setContacter(String contacter) {
            this.contacter = contacter;
        }

        public Object getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(Object coverImg) {
            this.coverImg = coverImg;
        }

        public int getDeposit() {
            return deposit;
        }

        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }

        public float getDepth() {
            return depth;
        }

        public void setDepth(float depth) {
            this.depth = depth;
        }

        public Object getDistance() {
            return distance;
        }

        public void setDistance(Object distance) {
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

        public double getElectricRate() {
            return electricRate;
        }

        public void setElectricRate(double electricRate) {
            this.electricRate = electricRate;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public int getGasRate() {
            return gasRate;
        }

        public void setGasRate(int gasRate) {
            this.gasRate = gasRate;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsCollected() {
            return isCollected;
        }

        public void setIsCollected(int isCollected) {
            this.isCollected = isCollected;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getIsFace() {
            return isFace;
        }

        public void setIsFace(int isFace) {
            this.isFace = isFace;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }

        public int getIsVisit() {
            return isVisit;
        }

        public void setIsVisit(int isVisit) {
            this.isVisit = isVisit;
        }

        public String getIssueShopTime() {
            return issueShopTime;
        }

        public void setIssueShopTime(String issueShopTime) {
            this.issueShopTime = issueShopTime;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOperateStatus() {
            return operateStatus;
        }

        public String getOperateStatusName() {
            String ret = null;
            if (operateStatus == 0) {
                ret = "正在经营";
            } else if (operateStatus ==1) {
                ret = "停业";
            }
            return ret;
        }

        public void setOperateStatus(int operateStatus) {
            this.operateStatus = operateStatus;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public double getPropertyRate() {
            return propertyRate;
        }

        public void setPropertyRate(double propertyRate) {
            this.propertyRate = propertyRate;
        }

        public int getRent() {
            return rent;
        }

        public void setRent(int rent) {
            this.rent = rent;
        }

        public int getRentStatus() {
            return rentStatus;
        }

        public String getRentWayName() {
            String ret = "";
            if (rentWay == 0) {
                ret = "月付";
            } else if (rentWay == 1) {
                ret = "季付";
            } else if (rentWay == 2) {
                ret = "半年付";
            } else if (rentWay == 3) {
                ret = "年付";
            }
            return ret;
        }

        public void setRentStatus(int rentStatus) {
            this.rentStatus = rentStatus;
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

        public Object getShopCode() {
            return shopCode;
        }

        public void setShopCode(Object shopCode) {
            this.shopCode = shopCode;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getTotalFloor() {
            return totalFloor;
        }

        public void setTotalFloor(int totalFloor) {
            this.totalFloor = totalFloor;
        }

        public int getTransferFee() {
            return transferFee;
        }

        public void setTransferFee(int transferFee) {
            this.transferFee = transferFee;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public double getWaterRate() {
            return waterRate;
        }

        public void setWaterRate(double waterRate) {
            this.waterRate = waterRate;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getVisitCount() {
            return visitCount;
        }

        public void setVisitCount(int visitCount) {
            this.visitCount = visitCount;
        }

        public int getContactCount() {
            return contactCount;
        }

        public void setContactCount(int contactCount) {
            this.contactCount = contactCount;
        }

        public List<Tag> getFeatureList() {
            return featureList;
        }

        public void setFeatureList(List<Tag> featureList) {
            this.featureList = featureList;
        }

        public List<ImageListBean> getImageList() {
            return imageList;
        }

        public void setImageList(List<ImageListBean> imageList) {
            this.imageList = imageList;
        }

        public List<Tag> getIndustryList() {
            return industryList;
        }

        public void setIndustryList(List<Tag> industryList) {
            this.industryList = industryList;
        }

        public List<NearInfoListBean> getNearInfoList() {
            return nearInfoList;
        }

        public void setNearInfoList(List<NearInfoListBean> nearInfoList) {
            this.nearInfoList = nearInfoList;
        }

        public List<Tag> getSupportList() {
            return supportList;
        }

        public void setSupportList(List<Tag> supportList) {
            this.supportList = supportList;
        }

        public static class ImageListBean implements Serializable{
            private int imgIndex;
            private String imgUrl;
            private int isCover;

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

            public int getIsCover() {
                return isCover;
            }

            public void setIsCover(int isCover) {
                this.isCover = isCover;
            }
        }

        public static class NearInfoListBean implements Comparable, Serializable{
            private int industryId;
            private String industryName;
            private String name;
            private int nearId;
            private int nearSeat;
            private String parentId;
            private List<ImageListBean> nearImg;

            public int getIndustryId() {
                return industryId;
            }

            public void setIndustryId(int industryId) {
                this.industryId = industryId;
            }

            public String getIndustryName() {
                return industryName;
            }

            public void setIndustryName(String industryName) {
                this.industryName = industryName;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNearId() {
                return nearId;
            }

            public void setNearId(int nearId) {
                this.nearId = nearId;
            }

            public int getNearSeat() {
                return nearSeat;
            }

            public void setNearSeat(int nearSeat) {
                this.nearSeat = nearSeat;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public List<ImageListBean> getNearImg() {
                return nearImg;
            }

            public void setNearImg(List<ImageListBean> nearImg) {
                this.nearImg = nearImg;
            }

            @Override
            public int compareTo(@NonNull Object o) {
                NearInfoListBean listBean = (NearInfoListBean) o;
                return this.nearSeat - listBean.nearSeat;
            }
        }


    }
}
