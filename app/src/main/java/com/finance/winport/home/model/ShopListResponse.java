package com.finance.winport.home.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/5/16.
 */

public class ShopListResponse extends BaseResponse {

    /**
     * data : [{"address":"武东路2009号","area":100,"blockId":2,"blockName":"江湾镇板块","contactCount":0,"coverImg":"http://test3.jpg","distance":null,"districtId":1,"districtName":"杨浦区","featureList":[{"color":null,"id":1,"name":"上水"},{"color":null,"id":2,"name":"下水"},{"color":null,"id":3,"name":"煤气罐"}],"id":54,"isFace":0,"modifyTime":1494837731000,"rent":5000,"rentTypeName":"转租","rentWay":1,"transferFee":80000,"updateTime":"23小时前","visitCount":0},{"address":"武东路2008号","area":100,"blockId":2,"blockName":"江湾镇板块","contactCount":0,"coverImg":"http://test3.jpg","distance":null,"districtId":1,"districtName":"杨浦区","featureList":[{"color":null,"id":1,"name":"上水"},{"color":null,"id":2,"name":"下水"},{"color":null,"id":3,"name":"煤气罐"}],"id":53,"isFace":0,"modifyTime":1494837482000,"rent":5000,"rentTypeName":"转租","rentWay":1,"transferFee":80000,"updateTime":"23小时前","visitCount":0},{"address":"武东路2008号","area":100,"blockId":2,"blockName":"江湾镇板块","contactCount":0,"coverImg":"http://test3.jpg","distance":null,"districtId":1,"districtName":"杨浦区","featureList":[{"color":null,"id":1,"name":"上水"},{"color":null,"id":2,"name":"下水"},{"color":null,"id":3,"name":"煤气罐"}],"id":52,"isFace":0,"modifyTime":1494835368000,"rent":5000,"rentTypeName":"转租","rentWay":1,"transferFee":80000,"updateTime":"2017-05-15","visitCount":0},{"address":"武东路2008号","area":100,"blockId":2,"blockName":"江湾镇板块","contactCount":0,"coverImg":"http://test3.jpg","distance":null,"districtId":1,"districtName":"杨浦区","featureList":[{"color":null,"id":1,"name":"上水"},{"color":null,"id":2,"name":"下水"},{"color":null,"id":3,"name":"煤气罐"}],"id":51,"isFace":0,"modifyTime":1494835326000,"rent":5000,"rentTypeName":"转租","rentWay":1,"transferFee":80000,"updateTime":"2017-05-15","visitCount":0},{"address":"武东路200号","area":100,"blockId":2,"blockName":"江湾镇板块","contactCount":0,"coverImg":"http://test3.jpg","distance":null,"districtId":310107,"districtName":"普陀区","featureList":[{"color":null,"id":4,"name":"天然气"},{"color":null,"id":1,"name":"上水"},{"color":null,"id":2,"name":"下水"},{"color":null,"id":3,"name":"煤气罐"}],"id":47,"isFace":0,"modifyTime":1494401462000,"rent":5000,"rentTypeName":"转租","rentWay":1,"transferFee":80000,"updateTime":"2017-05-10","visitCount":0},{"address":"武东路199号","area":20,"blockId":150,"blockName":"江湾新城","contactCount":0,"coverImg":null,"distance":null,"districtId":310106,"districtName":"静安区","featureList":[],"id":13,"isFace":0,"modifyTime":1494316734000,"rent":0,"rentTypeName":"转租","rentWay":0,"transferFee":0,"updateTime":"2017-05-09","visitCount":0},{"address":"武东路200号","area":100,"blockId":2,"blockName":"江湾镇板块","contactCount":0,"coverImg":"http://test3.jpg","distance":null,"districtId":310107,"districtName":"普陀区","featureList":[{"color":null,"id":4,"name":"天然气"},{"color":null,"id":1,"name":"上水"},{"color":null,"id":2,"name":"下水"},{"color":null,"id":3,"name":"煤气罐"}],"id":49,"isFace":0,"modifyTime":1494315271000,"rent":5000,"rentTypeName":"转租","rentWay":1,"transferFee":80000,"updateTime":"2017-05-09","visitCount":0},{"address":"武东路200号","area":100,"blockId":2,"blockName":"江湾镇板块","contactCount":0,"coverImg":"http://test3.jpg","distance":null,"districtId":310107,"districtName":"普陀区","featureList":[{"color":null,"id":4,"name":"天然气"},{"color":null,"id":1,"name":"上水"},{"color":null,"id":2,"name":"下水"},{"color":null,"id":3,"name":"煤气罐"}],"id":48,"isFace":0,"modifyTime":1494315142000,"rent":5000,"rentTypeName":"转租","rentWay":1,"transferFee":80000,"updateTime":"2017-05-09","visitCount":0},{"address":"武东路200号","area":100,"blockId":2,"blockName":"江湾镇板块","contactCount":0,"coverImg":"http://test3.jpg","distance":null,"districtId":310107,"districtName":"普陀区","featureList":[{"color":null,"id":4,"name":"天然气"},{"color":null,"id":1,"name":"上水"},{"color":null,"id":2,"name":"下水"},{"color":null,"id":3,"name":"煤气罐"}],"id":45,"isFace":0,"modifyTime":1494312295000,"rent":5000,"rentTypeName":"转租","rentWay":1,"transferFee":80000,"updateTime":"2017-05-09","visitCount":0},{"address":"武东路199号","area":20,"blockId":150,"blockName":"江湾新城","contactCount":6,"coverImg":null,"distance":null,"districtId":310106,"districtName":"静安区","featureList":[],"id":4,"isFace":0,"modifyTime":1494237855000,"rent":0,"rentTypeName":"转租","rentWay":0,"transferFee":0,"updateTime":"2017-05-08","visitCount":0}]
     * pageNumber : 1
     * pageSize : 10
     * query : {"areaList":null,"blockId":null,"districtId":null,"featureTagList":null,"latitude":null,"longitude":null,"metroId":null,"pageNumber":1,"pageSize":10,"queryType":0,"rentList":null,"shopType":null,"sortType":null,"stationId":null,"supportTagList":null,"transferList":null,"width":null}
     * start : 0
     * totalPage : 2
     * totalSize : 18
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int pageNumber;
        private int pageSize;
        /**
         * areaList : null
         * blockId : null
         * districtId : null
         * featureTagList : null
         * latitude : null
         * longitude : null
         * metroId : null
         * pageNumber : 1
         * pageSize : 10
         * queryType : 0
         * rentList : null
         * shopType : null
         * sortType : null
         * stationId : null
         * supportTagList : null
         * transferList : null
         * width : null
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * address : 武东路2009号
         * area : 100
         * blockId : 2
         * blockName : 江湾镇板块
         * contactCount : 0
         * coverImg : http://test3.jpg
         * distance : null
         * districtId : 1
         * districtName : 杨浦区
         * featureList : [{"color":null,"id":1,"name":"上水"},{"color":null,"id":2,"name":"下水"},{"color":null,"id":3,"name":"煤气罐"}]
         * id : 54
         * isFace : 0
         * modifyTime : 1494837731000
         * rent : 5000
         * rentTypeName : 转租
         * rentWay : 1
         * transferFee : 80000
         * updateTime : 23小时前
         * visitCount : 0
         */

        private List<Shop> data;

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

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
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

        public List<Shop> getData() {
            return data;
        }

        public void setData(List<Shop> data) {
            this.data = data;
        }

        public static class QueryBean {
            private Object areaList;
            private Object blockId;
            private Object districtId;
            private Object featureTagList;
            private Object latitude;
            private Object longitude;
            private Object metroId;
            private int pageNumber;
            private int pageSize;
            private int queryType;
            private Object rentList;
            private Object shopType;
            private Object sortType;
            private Object stationId;
            private Object supportTagList;
            private Object transferList;
            private Object width;

            public Object getAreaList() {
                return areaList;
            }

            public void setAreaList(Object areaList) {
                this.areaList = areaList;
            }

            public Object getBlockId() {
                return blockId;
            }

            public void setBlockId(Object blockId) {
                this.blockId = blockId;
            }

            public Object getDistrictId() {
                return districtId;
            }

            public void setDistrictId(Object districtId) {
                this.districtId = districtId;
            }

            public Object getFeatureTagList() {
                return featureTagList;
            }

            public void setFeatureTagList(Object featureTagList) {
                this.featureTagList = featureTagList;
            }

            public Object getLatitude() {
                return latitude;
            }

            public void setLatitude(Object latitude) {
                this.latitude = latitude;
            }

            public Object getLongitude() {
                return longitude;
            }

            public void setLongitude(Object longitude) {
                this.longitude = longitude;
            }

            public Object getMetroId() {
                return metroId;
            }

            public void setMetroId(Object metroId) {
                this.metroId = metroId;
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

            public int getQueryType() {
                return queryType;
            }

            public void setQueryType(int queryType) {
                this.queryType = queryType;
            }

            public Object getRentList() {
                return rentList;
            }

            public void setRentList(Object rentList) {
                this.rentList = rentList;
            }

            public Object getShopType() {
                return shopType;
            }

            public void setShopType(Object shopType) {
                this.shopType = shopType;
            }

            public Object getSortType() {
                return sortType;
            }

            public void setSortType(Object sortType) {
                this.sortType = sortType;
            }

            public Object getStationId() {
                return stationId;
            }

            public void setStationId(Object stationId) {
                this.stationId = stationId;
            }

            public Object getSupportTagList() {
                return supportTagList;
            }

            public void setSupportTagList(Object supportTagList) {
                this.supportTagList = supportTagList;
            }

            public Object getTransferList() {
                return transferList;
            }

            public void setTransferList(Object transferList) {
                this.transferList = transferList;
            }

            public Object getWidth() {
                return width;
            }

            public void setWidth(Object width) {
                this.width = width;
            }
        }

        public static class Shop {
            private String address;
            private double area;
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
            private int rent;
            private String rentTypeName;
            private int rentWay;
            private int transferFee;
            private String updateTime;
            private int visitCount;
            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

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

            public double getArea() {
                return area;
            }

            public void setArea(double area) {
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

            public int getRent() {
                return rent;
            }

            public void setRent(int rent) {
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
}
