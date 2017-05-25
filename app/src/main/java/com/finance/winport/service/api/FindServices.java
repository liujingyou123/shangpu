package com.finance.winport.service.api;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.service.model.CalendarListResponse;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.FindLoanRequest;
import com.finance.winport.service.model.FindServiceResponse;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.service.model.OrderShopRequest;
import com.finance.winport.service.model.RentShopRequest;
import com.finance.winport.service.model.SendOrderShopResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;
import com.finance.winport.service.model.ShopRentCountResponse;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by liuworkmac on 17/5/15.
 */

public interface FindServices {
    //预约统计
    @POST("customerapp/api/customer/visitStatistics/v1.0.0")
    Observable<ShopOrderCountResponse> getOrderCount();

    //找资金统计
    @POST("customerapp/api/customer/loadStatistics/v1.0.0")
    Observable<FindLoanCountResponse> getFindLoanCount();

    //金铺寻租统计
    @POST("customerapp/api/clerkHint/getReleaseTotal/v1.0.0")
    Observable<ShopRentCountResponse> getRentCount();

    //我的贷款申请列表
    @POST("customerapp/api/customer/loadList/v1.0.0")
    Observable<LoanListResponse> getLoanList(@Body HashMap map);

    //找服务首页
    @POST("customerapp/api/customer/serviceInfo/v1.0.0")
    Observable<FindServiceResponse> getFindService();

    //找服务首页日历日程
    @POST("customerapp/api/user/schedule/calendar/list/v1.0.0")
    Observable<CalendarListResponse> getCalendar(@Body HashMap map);


    //预约看铺
    @POST("customerapp/api/customer/applyVisit/v1.0.0")
    Observable<SendOrderShopResponse> sendOrderShop(@Body OrderShopRequest request);

    //签约租铺
    @POST("customerapp/api/customer/applySign/v1.0.0")
    Observable<SendOrderShopResponse> sendSignShop(@Body OrderShopRequest request);

    //找资金-申请贷款
    @POST("customerapp/api/customer/applyLoad/v1.0.0")
    Observable<BaseResponse> sendFindLoan(@Body FindLoanRequest request);

    //旺铺寻租
    @POST("customerapp/api/clerkHint/addClerkHint/v1.0.0")
    Observable<SendOrderShopResponse> sendRentShop(@Body RentShopRequest request);




}
