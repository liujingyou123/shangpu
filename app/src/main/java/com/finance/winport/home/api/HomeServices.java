package com.finance.winport.home.api;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.model.BannerResponse;
import com.finance.winport.home.model.CollectionResponse;
import com.finance.winport.home.model.MetroResponse;
import com.finance.winport.home.model.RegionResponse;
import com.finance.winport.home.model.ShopCount;
import com.finance.winport.home.model.ShopDetail;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.model.TagResponse;

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
    Observable<CollectionResponse> collectShop(@Body HashMap params);

    //商铺详情
    @POST("customerapp/api/shop/queryShopDetail/v1.0.0")
    Observable<ShopDetail> getShopDetail(@Body HashMap params);

    //直拨房东电话记录接口
    @POST("customerapp/api/shop/createLiaisonRecord/v1.0.0")
    Observable<BaseResponse> recordCall(@Body HashMap params);

    //今日新铺-无转让费等数据接口
    @Headers("Content-Type: application/json")
    @POST("customerapp/api/shop/countShopFromClient/v1.0.0")
    Observable<ShopCount> getShopCount();

    //广告banner
    @POST("customerapp/api/base/advList/v1.0.0")
    Observable<BannerResponse> getBanners(@Body HashMap params);

    //地铁
    @POST("customerapp/api/base/queryMetro/v1.0.0")
    Observable<MetroResponse> getMetros(@Body HashMap params);

    //标签接口
    @POST("customerapp/api/base/tagList/v1.0.0")
    Observable<TagResponse> getTagList(@Body HashMap params);
}
