package com.finance.winport.map.api;

import com.finance.winport.map.model.MapAreaRequest;
import com.finance.winport.map.model.MapAreaResponse;
import com.finance.winport.map.model.MapShopRequest;
import com.finance.winport.map.model.MapShopResponse;
import com.finance.winport.mine.model.IndustryListResponse;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jge on 17/5/16.
 */

public interface MapServices {
    //街铺地图-店铺级别的地图
    @POST("customerapp/api/map/shop/v1.0.0")
    Observable<MapShopResponse> getMapShop(@Body MapShopRequest request);


    //街铺地图-区域板块层级店铺数量
    @POST("customerapp/api/map/regionShop/v1.0.0")
    Observable<MapAreaResponse> getMapArea(@Body MapAreaRequest request);







}
