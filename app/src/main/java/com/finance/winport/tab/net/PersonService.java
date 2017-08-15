package com.finance.winport.tab.net;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.model.CheckVersionResponse;
import com.finance.winport.tab.model.AppointRanking;
import com.finance.winport.tab.model.AppointShopList;
import com.finance.winport.tab.model.CollectionShopList;
import com.finance.winport.tab.model.Lunar;
import com.finance.winport.tab.model.NotifyList;
import com.finance.winport.tab.model.NotifyType;
import com.finance.winport.tab.model.Prediction;
import com.finance.winport.tab.model.ScanCount;
import com.finance.winport.tab.model.ScanShopList;
import com.finance.winport.tab.model.UnReadMsg;
import com.finance.winport.tab.model.WinportCounts;
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
    @POST("customerapp/api/shop/queryMyPublishShop")
    Observable<WinportList> getWinportList(@Body HashMap<String, Object> params);

    // 我约看的列表
    @POST("customerapp/api/customer/shopVisitedList")
    Observable<AppointShopList> getAppointList(@Body HashMap<String, Object> params);

    // 收藏列表
    @POST("customerapp/api/shop/shopCollectedList")
    Observable<CollectionShopList> getCollectionList(@Body HashMap<String, Object> params);

    // 最近浏览列表
    @POST("customerapp/api/shop/shopBrowseList")
    Observable<ScanShopList> getScanList(@Body HashMap<String, Object> params);

    // 修改头像
    @POST("customerapp/api/customer/updateHeadPortrait")
    Observable<BaseResponse> updateHeadInfo(@Body HashMap<String, Object> params);

    // 店名测吉凶
    @POST("customerapp/api/user/fortune/shopNameIsGood")
    Observable<Prediction> predictionShop(@Body HashMap<String, Object> params);

    // 下架商铺
    @POST("customerapp/api/shop/unDoShop")
    Observable<BaseResponse> offShelfSHop(@Body HashMap<String, Object> params);

    // 直拨房东电话记录接口
    @POST("api/shop/createLiaisonRecord")
    Observable<BaseResponse> callRecord(@Body HashMap<String, Object> params);

    // 旺铺被看总数和排名统计接口
    @POST("customerapp/api/shop/queryMyShopScanCount")
    Observable<ScanCount> queryScanCount(@Body HashMap<String, Object> params);

    // 最近浏览总数和排名统计接口
    @POST("customerapp/api/shop/myBrowseStatistic")
    Observable<AppointRanking> queryBrowserCount(@Body HashMap<String, Object> params);

    // 收藏总数和排名统计接口
    @POST("customerapp/api/shop/myCollectStatistic")
    Observable<AppointRanking> queryCollectionCount(@Body HashMap<String, Object> params);

    // 约看次数和排名
    @POST("customerapp/api/customer/myVisitStatistic")
    Observable<AppointRanking> getAppointRanking(@Body HashMap<String, Object> params);

    // 删除约看
    @POST("customerapp/api/customer/deleteAppointment")
    Observable<BaseResponse> deleteAppoint(@Body HashMap<String, Object> params);

    // 取消收藏
    @POST("customerapp/api/shop/cancelShopCollected")
    Observable<BaseResponse> cancelCollection(@Body HashMap<String, Object> params);

    // 删除浏览
    @POST("customerapp/api/shop/deleteShopBrowse")
    Observable<BaseResponse> deleteScan(@Body HashMap<String, Object> params);

    // 通知列表
    @POST("customerapp/api/notice/getNoticeByTypeList")
    Observable<NotifyList> getNotifyList(@Body HashMap<String, Object> params);

    // 通知分类
    @POST("customerapp/api/notice/getNoticeList")
    Observable<NotifyType> getNotifyType(@Body HashMap<String, Object> params);

    // 我的未读消息
    @POST("customerapp/api/notice/isUnRead")
    Observable<UnReadMsg> getUnReadMsg(@Body HashMap<String, Object> params);

    // 我的发布、约看、收藏、浏览、未来日程 总数统计接口
    @POST("customerapp/api/customer/indexStatistics")
    Observable<WinportCounts> getWinportCounts(@Body HashMap<String, Object> params);

    // 农历接口
    @POST("customerapp/api/nongli/wnl?version=v1.0.0")
    Observable<Lunar> getLunar(@Body HashMap<String, Object> params);

    // 更新 registrationId
    @POST("customerapp/api/user/verify/updateDeviceId")
    Observable<BaseResponse> updateRegistrationId(@Body HashMap<String, Object> params);

    // 版本更新
    @POST("customerapp/api/user/app/checkUpdate")
    Observable<CheckVersionResponse> checkVersion(@Body HashMap<String, Object> params);

    //修改手机号
    @POST("")
    Observable<com.finance.winport.base.BaseResponse> modifyUserPhone(@Body HashMap map);

    //修改昵称
    @POST("")
    Observable<com.finance.winport.base.BaseResponse> modifyNickName(@Body HashMap map);

    //修改签名
    @POST("")
    Observable<com.finance.winport.base.BaseResponse> modifySign(@Body HashMap map);

}
