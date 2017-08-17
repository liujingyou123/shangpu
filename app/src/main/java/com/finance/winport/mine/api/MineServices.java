package com.finance.winport.mine.api;

import com.finance.winport.mine.model.CommitFocusRequest;
import com.finance.winport.mine.model.IndustryListResponse;
import com.finance.winport.mine.model.MyServiceListResponse;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.mine.model.ScheduleDetailResponse;
import com.finance.winport.mine.model.ServiceDetailResponse;
import com.finance.winport.mine.model.ScheduleListResponse;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jge on 17/5/16.
 */

public interface MineServices {
    //商铺关注设置---经营范围
    @POST("customerapp/api/base/industryList")
    Observable<IndustryListResponse> getIndustryList();

    //个人信息
    @POST("customerapp/api/customer/queryCustomerInfo")
    Observable<PersonalInfoResponse> getPersonalInfo();


    //我的日程
    @POST("customerapp/api/user/schedule/list")
    Observable<ScheduleListResponse> getScheduleList(@Body HashMap map);


    //日程详情
    @POST("customerapp/api/user/schedule/detail")
    Observable<ScheduleDetailResponse> getScheduleDetail(@Body HashMap map);

    //日程确定服务
    @POST("customerapp/api/user/schedule/ensure")
    Observable<com.finance.winport.base.BaseResponse> ensureSchedule(@Body HashMap map);

    //日程撤销
    @POST("customerapp/api/user/schedule/revoke")
    Observable<com.finance.winport.base.BaseResponse> revokeSchedule(@Body HashMap map);

    //商铺关注设置
    @POST("customerapp/api/customer/updateFollow")
    Observable<com.finance.winport.base.BaseResponse> commitFocus(@Body CommitFocusRequest request);

    //建议反馈
    @POST("customerapp/api/feedBack/addFeedBack")
    Observable<com.finance.winport.base.BaseResponse> commitFeedBack(@Body HashMap map);

    //我的服务列表
    @POST("customerapp/api/customer/myServiceList")
    Observable<MyServiceListResponse> getServiceList(@Body HashMap map);

}
