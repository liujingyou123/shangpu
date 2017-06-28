package com.finance.winport.home.api;

/**
 * Created by liuworkmac on 17/6/28.
 */

public class ApiUrl {
    public final static String version = "v1.0.0";
    //商铺筛选
    public final static String QUERYSHOP = "customerapp/api/shop/queryShop/" + version;
    //区域板块
    public final static String AREA = "customerapp/api/base/area/" + version;
    //收藏
    public final static String CREATECOLLECTED = "customerapp/api/shop/createCollected/" + version;
    //商铺详情
    public final static String QUERYSHOPDETAIL = "customerapp/api/shop/queryShopDetail/" + version;
    //直拨房东电话记录接口
    public final static String CREATELIAISONRECORD = "customerapp/api/shop/createLiaisonRecord/" + version;
    //今日新铺-无转让费等数据接口
    public final static String COUNTSHOPFROMCLIENT = "customerapp/api/shop/countShopFromClient/" + version;
    //广告banner
    public final static String ADVLIST = "customerapp/api/base/advList/" + version;
    //地铁
    public final static String QUERYMETRO = "customerapp/api/base/queryMetro/" + version;
    //标签接口
    public final static String TAGLIST = "customerapp/api/base/tagList/" + version;
    //商铺纠错接口
    public final static String CREATECORRECT = "customerapp/api/shop/createCorrect/" + version;
    //获取阿里云TOKEN
    public final static String GETTMPACCESSINFO = "customerapp/api/user/aliyun/getTmpAccessInfo/" + version;
}
