package com.finance.winport.service.api;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.service.model.ShopOrderCountResponse;

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



}
