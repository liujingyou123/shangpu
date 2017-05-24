package com.finance.winport.mine.api;

import com.finance.winport.mine.model.CommitFocusRequest;
import com.finance.winport.mine.model.IndustryListResponse;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.mine.model.ScheduleDetailResponse;
import com.finance.winport.mine.model.ScheduleListResponse;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;
import com.sina.weibo.sdk.api.share.BaseResponse;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jge on 17/5/16.
 */

public interface MineServices {
    //商铺关注设置---经营范围
    @POST("customerapp/api/base/industryList/v1.0.0")
    Observable<IndustryListResponse> getIndustryList();

    //个人信息
    @POST("customerapp/api/customer/queryCustomerInfo/v1.0.0")
    Observable<PersonalInfoResponse> getPersonalInfo();


    //我的日程
    @POST("customerapp/api/user/schedule/list/v1.0.0")
    Observable<ScheduleListResponse> getScheduleList(@Body HashMap map);


    //日程详情
    @POST("customerapp/api/user/schedule/detail/v1.0.0")
    Observable<ScheduleDetailResponse> getScheduleDetail(@Body HashMap map);

    //日程确定服务
    @POST("customerapp/api/user/schedule/ensure/v1.0.0")
    Observable<com.finance.winport.base.BaseResponse> ensureSchedule(@Body HashMap map);

    //日程撤销
    @POST("customerapp/api/user/schedule/revoke/v1.0.0")
    Observable<com.finance.winport.base.BaseResponse> revokeSchedule(@Body HashMap map);

    //商铺关注设置
    @POST("customerapp/api/customer/updateFollow/v1.0.0")
    Observable<com.finance.winport.base.BaseResponse> commitFocus(@Body CommitFocusRequest request);








}
