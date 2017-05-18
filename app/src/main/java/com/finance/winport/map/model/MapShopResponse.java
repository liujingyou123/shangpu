package com.finance.winport.map.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class MapShopResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : [{"area":300,"rent":30000,"name":"杨浦第一店面","shopId":1,"longitude":"121.4919600000","latitude":"31.3046400000","shopStatus":3},{"area":300,"rent":30000,"name":"杨浦第一店面","shopId":1,"longitude":"121.4919600000","latitude":"31.3046400000","shopStatus":3},{"area":300,"rent":30000,"name":"手拉手餐厅","shopId":2,"longitude":"121.4919600000","latitude":"31.3046400000","shopStatus":1},{"area":300,"rent":30000,"name":"手拉手餐厅分店1号","shopId":3,"longitude":"121.3301320300","latitude":"31.2390107300","shopStatus":3},{"area":300,"rent":30000,"name":"手拉手餐厅分店2号","shopId":4,"longitude":"121.5130140700","latitude":"31.2120758500","shopStatus":1},{"area":300,"rent":30000,"name":"手拉手餐厅分店3号","shopId":5,"longitude":"121.4159403300","latitude":"31.2356533900","shopStatus":2},{"area":300,"rent":30000,"name":"手拉手餐厅分店4号","shopId":6,"longitude":"121.4607292200","latitude":"31.2062560000","shopStatus":1},{"area":300,"rent":30000,"name":"手拉手餐厅分店5号","shopId":7,"longitude":",121.4837189000","latitude":"31.2425734800","shopStatus":1},{"area":300,"rent":30000,"name":"手拉手餐厅分店6号","shopId":8,"longitude":"121.4381846200","latitude":"31.2263043500","shopStatus":1},{"area":300,"rent":30000,"name":"手拉手餐厅分店7号","shopId":9,"longitude":",121.4611991900","latitude":"31.2597385900","shopStatus":1},{"area":300,"rent":30000,"name":"手拉手餐厅分店8号","shopId":10,"longitude":"121.4874606300","latitude":"31.2584500000","shopStatus":1},{"area":300,"rent":30000,"name":"手拉手餐厅分店9号","shopId":11,"longitude":"121.3774364500","latitude":"31.1151632100","shopStatus":0},{"area":120,"rent":12000,"name":"达安广场","shopId":15,"longitude":"121.461666","latitude":"31.229691","shopStatus":0},{"area":120,"rent":12000,"name":"延安中路1000号","shopId":16,"longitude":"121.460220","latitude":"31.230726","shopStatus":1},{"area":120,"rent":12000,"name":"长乐路536号上海市第一妇婴保健院9号楼2楼","shopId":17,"longitude":"121.462798","latitude":"31.226851","shopStatus":2},{"area":120,"rent":12000,"name":"安区陕西南路30号马勒别墅饭店1楼(近延安中路)","shopId":18,"longitude":"121.462708","latitude":"31.229267","shopStatus":3},{"area":110,"rent":10000,"name":"安达广场","shopId":19,"longitude":"121.462708","latitude":"31.229267","shopStatus":1},{"area":110,"rent":10000,"name":"安达广场","shopId":20,"longitude":"121.462708","latitude":"31.229267","shopStatus":1},{"area":110,"rent":10000,"name":"安达广场","shopId":21,"longitude":"121.462708","latitude":"31.229267","shopStatus":1},{"area":110,"rent":10000,"name":"安达广场","shopId":22,"longitude":"121.462708","latitude":"31.229267","shopStatus":1},{"area":110,"rent":10000,"name":"安达广场","shopId":23,"longitude":"121.462708","latitude":"31.229267","shopStatus":1},{"area":120,"rent":12000,"name":"恒基名人商业商务中心","shopId":25,"longitude":"121.490769","latitude":"31.243668","shopStatus":0},{"area":120,"rent":12000,"name":"光启文化广场","shopId":26,"longitude":"121.447582","latitude":"31.191773","shopStatus":0},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1},{"area":100,"rent":5000,"name":"财大饭店","shopId":27,"longitude":"121.563724","latitude":"31.208911","shopStatus":1}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * area : 300
         * rent : 30000
         * name : 杨浦第一店面
         * shopId : 1
         * longitude : 121.4919600000
         * latitude : 31.3046400000
         * shopStatus : 3
         */

        private int area;
        private int rent;
        private String name;
        private int shopId;
        private String longitude;
        private String latitude;
        private int shopStatus;

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public int getRent() {
            return rent;
        }

        public void setRent(int rent) {
            this.rent = rent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getShopStatus() {
            return shopStatus;
        }

        public void setShopStatus(int shopStatus) {
            this.shopStatus = shopStatus;
        }
    }
}
