package com.finance.winport.home.api;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.model.RegionResponse;
import com.finance.winport.home.model.ShopDetail;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;

import java.util.HashMap;

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
    Observable<ShopListResponse> getShops(@Body ShopRequset requset);

    //区域板块
    @POST("customerapp/api/base/area/v1.0.0")
    Observable<RegionResponse> getDistrict(@Body HashMap params);


    //收藏
    @POST("customerapp/api/shop/createCollected/v1.0.0")
    Observable<BaseResponse> collectShop(@Body HashMap params);

    //商铺详情
    @POST("customerapp/api/shop/queryShopDetail/v1.0.0")
    Observable<ShopDetail> getShopDetail(@Body HashMap params);

    //直拨房东电话记录接口
    @POST("customerapp/api/shop/createLiaisonRecord/v1.0.0")
    Observable<BaseResponse> recordCall(@Body HashMap params);

}
