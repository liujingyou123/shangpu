package com.finance.winport.home.api;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.model.ShopRequset;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by liuworkmac on 17/5/15.
 */

public interface HomeServices {
    //商铺筛选
    @POST("customerapp/api/shop/queryShop/v1.0.0")
    Observable<BaseResponse> getShops(@Body ShopRequset requset);

    //区域板块
    @FormUrlEncoded
    @POST("customerapp/api/base/area/v1.0.0")
    Observable<BaseResponse> getDistrict(@Field("reqStr") String params);

}
