package com.finance.winport.tab.net;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.tab.model.AppointRanking;
import com.finance.winport.tab.model.AppointShopList;
import com.finance.winport.tab.model.CollectionShopList;
import com.finance.winport.tab.model.NotifyList;
import com.finance.winport.tab.model.NotifyType;
import com.finance.winport.tab.model.Prediction;
import com.finance.winport.tab.model.ScanCount;
import com.finance.winport.tab.model.ScanShopList;
import com.finance.winport.tab.model.UnReadMsg;
import com.finance.winport.tab.model.WinportList;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xzw on 2017/5/17.
 */

public interface PersonService {
    // 我发布的旺铺列表
    @POST("customerapp/api/shop/queryMyPublishShop/v1.0.0")
    Observable<WinportList> getWinportList(@Body HashMap<String, Object> params);

    // 我约看的列表
    @POST("customerapp/api/customer/shopVisitedList/v1.0.0")
    Observable<AppointShopList> getAppointList(@Body HashMap<String, Object> params);

    // 收藏列表
    @POST("customerapp/api/shop/shopCollectedList/v1.0.0")
    Observable<CollectionShopList> getCollectionList(@Body HashMap<String, Object> params);

    // 最近浏览列表
    @POST("customerapp/api/shop/shopBrowseList/v1.0.0")
    Observable<ScanShopList> getScanList(@Body HashMap<String, Object> params);

    // 修改头像
    @POST("customerapp/api/customer/updateHeadPortrait/v1.0.0")
    Observable<BaseResponse> updateHeadInfo(@Body HashMap<String, Object> params);

    // 店名测吉凶
    @POST("customerapp/api/user/fortune/shopNameIsGood/v1.0.0")
    Observable<Prediction> predictionShop(@Body HashMap<String, Object> params);

    // 下架商铺
    @POST("customerapp/api/shop/unDoShop/v1.0.0 ")
    Observable<BaseResponse> offShelfSHop(@Body HashMap<String, Object> params);

    // 直拨房东电话记录接口
    @POST("api/shop/createLiaisonRecord/V1.0.0")
    Observable<BaseResponse> callRecord(@Body HashMap<String, Object> params);

    // 旺铺被看总数和排名统计接口
    @POST("customerapp/api/shop/queryMyShopScanCount/v1.0.0")
    Observable<ScanCount> queryScanCount(@Body HashMap<String, Object> params);

    // 最近浏览总数和排名统计接口
    @POST("customerapp/api/shop/myBrowseStatistic/v1.0.0")
    Observable<AppointRanking> queryBrowserCount(@Body HashMap<String, Object> params);

    // 收藏总数和排名统计接口
    @POST("customerapp/api/shop/myCollectStatistic/v1.0.0")
    Observable<AppointRanking> queryCollectionCount(@Body HashMap<String, Object> params);

    // 约看次数和排名
    @POST("customerapp/api/customer/myVisitStatistic/v1.0.0")
    Observable<AppointRanking> getAppointRanking(@Body HashMap<String, Object> params);

    // 删除约看
    @POST("customerapp/api/customer/deleteAppointment/v1.0.0")
    Observable<BaseResponse> deleteAppoint(@Body HashMap<String, Object> params);

    // 取消收藏
    @POST("customerapp/api/shop/cancelShopCollected/v1.0.0")
    Observable<BaseResponse> cancelCollection(@Body HashMap<String, Object> params);

    // 删除浏览
    @POST("customerapp/api/shop/deleteShopBrowse/v1.0.0")
    Observable<BaseResponse> deleteScan(@Body HashMap<String, Object> params);

    // 通知列表
    @POST("customerapp/api/shop/deleteShopBrowse/v1.0.0")
    Observable<NotifyList> getNotifyList(@Body HashMap<String, Object> params);

    // 通知分类
    @POST("customerapp/api/shop/deleteShopBrowse/v1.0.0")
    Observable<NotifyType> getNotifyType(@Body HashMap<String, Object> params);

    // 我的未读消息
    @POST("customerapp/api/notice/isUnRead/v1.0.0")
    Observable<UnReadMsg> getUnReadMsg(@Body HashMap<String, Object> params);
}
