package com.finance.winport.tab.model;

import com.finance.winport.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xzw on 2017/5/18.
 * 收藏列表
 */

public class CollectionShopList extends BaseResponse {

    /**
     * errMsg : null
     * errCode : 0
     * data : {"data":[{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":0,"contactCount":3,"featureList":[{"id":3,"name":"煤气罐","color":null}],"collectedId":4,"rentStatus":1}],"totalSize":1,"pageSize":10,"pageNumber":1,"totalPage":1,"start":0}
     */

    public DataBeanX data;

    public static class DataBeanX {
        /**
         * data : [{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":0,"contactCount":3,"featureList":[{"id":3,"name":"煤气罐","color":null}],"collectedId":4,"rentStatus":1}]
         * totalSize : 1
         * pageSize : 10
         * pageNumber : 1
         * totalPage : 1
         * start : 0
         */

        public int totalSize;
        public int pageSize;
        public int pageNumber;
        public int totalPage;
        public int start;
        public List<DataBean> data;

        public static class DataBean {
            /**
             * id : 4
             * coverImg : http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg
             * address : 浦东南路2003号
             * rentTypeName : 直租
             * area : 300
             * districtId : 310115
             * districtName :
             * blockId : 17
             * blockName :
             * rentWay : 0
             * rent : 30000
             * isFace : 1
             * distance : null
             * transferFee : 300000
             * updateTime : null
             * modifyTime : null
             * visitCount : 0
             * contactCount : 3
             * featureList : [{"id":3,"name":"煤气罐","color":null}]
             * collectedId : 4
             * rentStatus : 1
             */

            public String id;
            public String title;
            public String industryName;
            public String coverImg;
            public String address;
            public String rentTypeName;
            public float area;
            public int districtId;
            public String districtName;
            public int blockId;
            public String blockName;
            public int rentWay;
            public float rent;
            public int isFace;
            public float distance;
            public float transferFee;
            public String updateTime;
            public String modifyTime;
            public int visitCount;
            public int contactCount;
            public int collectedId;
            public int rentStatus;
            public int shelfStatus;//0 已上架、1 已下架
            public List<FeatureListBean> featureList;

            public static class FeatureListBean {
                /**
                 * id : 3
                 * name : 煤气罐
                 * color : null
                 */

                public int id;
                public String name;
                public String color;
            }
        }
    }
}
