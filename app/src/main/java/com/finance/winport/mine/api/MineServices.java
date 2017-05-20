package com.finance.winport.mine.api;

import com.finance.winport.mine.model.IndustryListResponse;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.mine.model.ScheduleDetailResponse;
import com.finance.winport.mine.model.ScheduleListResponse;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;

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








}
