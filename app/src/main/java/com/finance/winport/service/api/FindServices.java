package com.finance.winport.service.api;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.FindServiceResponse;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;

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

    //我的贷款申请列表
    @POST("customerapp/api/customer/loadList/v1.0.0")
    Observable<LoanListResponse> getLoanList(@Body HashMap map);

    //找服务首页
    @POST("customerapp/api/customer/serviceInfo/v1.0.0")
    Observable<FindServiceResponse> getFindService();




}
