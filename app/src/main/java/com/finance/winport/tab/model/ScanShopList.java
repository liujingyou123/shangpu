package com.finance.winport.tab.model;

import com.finance.winport.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xzw on 2017/5/18.
 * 浏览列表
 */

public class ScanShopList extends BaseResponse {

    /**
     * errMsg : null
     * errCode : 0
     * data : {"data":[{"id":1,"coverImg":"http://update 1.jpg","address":"上海武东路198号","rentTypeName":"直租","area":300,"districtId":310110,"districtName":null,"blockId":100,"blockName":null,"rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":"20小时前","modifyTime":1495025460000,"visitCount":10,"contactCount":7,"featureList":[{"id":3,"name":"煤气罐","color":null},{"id":4,"name":"天然气","color":null}],"browseId":4,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":36,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":37,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":38,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":39,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":40,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":41,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":42,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":43,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":44,"rentStatus":1}],"totalSize":28,"pageSize":10,"pageNumber":1,"totalPage":3,"start":0}
     */
    public DataBeanX data;

    public static class DataBeanX {
        /**
         * data : [{"id":1,"coverImg":"http://update 1.jpg","address":"上海武东路198号","rentTypeName":"直租","area":300,"districtId":310110,"districtName":null,"blockId":100,"blockName":null,"rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":"20小时前","modifyTime":1495025460000,"visitCount":10,"contactCount":7,"featureList":[{"id":3,"name":"煤气罐","color":null},{"id":4,"name":"天然气","color":null}],"browseId":4,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":36,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":37,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":38,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":39,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":40,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":41,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":42,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":43,"rentStatus":1},{"id":4,"coverImg":"http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg","address":"浦东南路2003号","rentTypeName":"直租","area":300,"districtId":310115,"districtName":"","blockId":17,"blockName":"","rentWay":0,"rent":30000,"isFace":1,"distance":null,"transferFee":300000,"updateTime":null,"modifyTime":null,"visitCount":47,"contactCount":33,"featureList":[{"id":3,"name":"煤气罐","color":null}],"browseId":44,"rentStatus":1}]
         * totalSize : 28
         * pageSize : 10
         * pageNumber : 1
         * totalPage : 3
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
             * id : 1
             * coverImg : http://update 1.jpg
             * address : 上海武东路198号
             * rentTypeName : 直租
             * area : 300
             * districtId : 310110
             * districtName : null
             * blockId : 100
             * blockName : null
             * rentWay : 0
             * rent : 30000
             * isFace : 1
             * distance : null
             * transferFee : 300000
             * updateTime : 20小时前
             * modifyTime : 1495025460000
             * visitCount : 10
             * contactCount : 7
             * featureList : [{"id":3,"name":"煤气罐","color":null},{"id":4,"name":"天然气","color":null}]
             * browseId : 4
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
            public int browseId;
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
