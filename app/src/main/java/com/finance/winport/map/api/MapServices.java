package com.finance.winport.map.api;

import com.finance.winport.map.model.MapAreaRequest;
import com.finance.winport.map.model.MapAreaResponse;
import com.finance.winport.map.model.MapShopDetailResponse;
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
    @POST("customerapp/api/map/shop")
    Observable<MapShopResponse> getMapShop(@Body MapShopRequest request);


    //街铺地图-区域板块层级店铺数量
    @POST("customerapp/api/map/regionShop")
    Observable<MapAreaResponse> getMapArea(@Body MapAreaRequest request);


    //街铺地图-点击商铺查看概要信息
    @POST("customerapp/api/map/summary")
    Observable<MapShopDetailResponse> getMapShopDetail(@Body HashMap map);







}
