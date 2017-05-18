package com.finance.winport.map.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class MapAreaResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : [{"name":null,"bizId":1,"shopCount":512,"longitude":null,"latitude":null},{"name":"黄浦区","bizId":310101,"shopCount":1,"longitude":"121.479298","latitude":"31.217975"},{"name":"徐汇区","bizId":310104,"shopCount":1,"longitude":"121.435194","latitude":"31.164765"},{"name":"静安区","bizId":310106,"shopCount":7,"longitude":"121.44378","latitude":"31.231009"},{"name":"闸北区","bizId":310108,"shopCount":1,"longitude":"121.446778","latitude":"31.283737"},{"name":"虹口区","bizId":310109,"shopCount":1,"longitude":"121.481033","latitude":"31.278701"},{"name":"杨浦区","bizId":310110,"shopCount":1,"longitude":"121.524931","latitude":"31.300638"},{"name":"浦东新区","bizId":310115,"shopCount":1,"longitude":"121.751233","latitude":"31.087679"}]
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
         * name : null
         * bizId : 1
         * shopCount : 512
         * longitude : null
         * latitude : null
         */

        private String name;
        private int bizId;
        private int shopCount;
        private String longitude;
        private String latitude;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBizId() {
            return bizId;
        }

        public void setBizId(int bizId) {
            this.bizId = bizId;
        }

        public int getShopCount() {
            return shopCount;
        }

        public void setShopCount(int shopCount) {
            this.shopCount = shopCount;
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
    }
}
