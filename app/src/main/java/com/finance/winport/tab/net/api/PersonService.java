package com.finance.winport.tab.net.api;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.tab.model.AppointShopList;
import com.finance.winport.tab.model.WinportList;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xzw on 2017/5/17.
 */

public interface PersonService {
    // 我发布的旺铺列表
    @FormUrlEncoded
    @POST("customerapp/api/shop/queryMyPublishShop/v1.0.0")
    Observable<WinportList> getWinportList(@Body HashMap<String, Object> params);

    // 我约看的列表
    @FormUrlEncoded
    @POST("customerapp/api/customer/shopVisitedList/v1.0.0")
    Observable<AppointShopList> getAppointList(@Body HashMap<String, Object> params);

    // 修改头像
    @FormUrlEncoded
    @POST("customerapp/api/customer/updateHeadPortrait/v1.0.0")
    Observable<BaseResponse> updateHeadInfo(@Body HashMap<String, Object> params);

//    // 店名测吉凶
//    @FormUrlEncoded
//    @POST("api/user/fortune/shopNameIsGood/v1.0.0")
//    Observable<> predictionShop(@Body HashMap<String, Object> params);

    // 下架商铺
    @FormUrlEncoded
    @POST("customerapp/api/shop/unDoShop/v1.0.0 ")
    Observable<BaseResponse> offShelfSHop(@Body HashMap<String, Object> params);

    // 直拨房东电话记录接口
    @FormUrlEncoded
    @POST("api/shop/createLiaisonRecord/V1.0.0")
    Observable<BaseResponse> callRecord(@Body HashMap<String, Object> params);

    // 旺铺被看总数和排名统计接口
//    @FormUrlEncoded
//    @POST("customerapp/api/shop/queryMyShopScanCount/v1.0.0 ")
//    Observable<> queryScanCount(@Body HashMap<String, Object> params);

    // 预约统计
//    @FormUrlEncoded
//    @POST("customerapp/api/customer/visitStatistics/v1.0.0 ")
//    Observable<> getAppointStatistics(@Body HashMap<String, Object> params);

    // 约看次数和排名
//    @FormUrlEncoded
//    @POST("customerapp/api/customer/myVisitStatistic/v1.0.0")
//    Observable<> getAppointRanking(@Body HashMap<String, Object> params);

    // 删除约看
    @FormUrlEncoded
    @POST("customerapp/api/customer/deleteAppointment/v1.0.0")
    Observable<BaseResponse> deleteAppoint(@Body HashMap<String, Object> params);
}
