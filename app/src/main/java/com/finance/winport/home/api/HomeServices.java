package com.finance.winport.home.api;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.model.AliTokenResponse;
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

import retrofit2.Call;
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
    @POST(ApiUrl.QUERYSHOP)
    Observable<ShopListResponse> getShops(@Body ShopRequset requset);

    //区域板块
    @POST(ApiUrl.AREA)
    Observable<RegionResponse> getDistrict(@Body HashMap params);

    //收藏
    @POST(ApiUrl.CREATECOLLECTED)
    Observable<CollectionResponse> collectShop(@Body HashMap params);

    //商铺详情
    @POST(ApiUrl.QUERYSHOPDETAIL)
    Observable<ShopDetail> getShopDetail(@Body HashMap params);

    //直拨房东电话记录接口
    @POST(ApiUrl.CREATELIAISONRECORD)
    Observable<BaseResponse> recordCall(@Body HashMap params);

    //今日新铺-无转让费等数据接口
    @Headers("Content-Type: application/json")
    @POST(ApiUrl.COUNTSHOPFROMCLIENT)
    Observable<ShopCount> getShopCount();

    //广告banner
    @POST(ApiUrl.ADVLIST)
    Observable<BannerResponse> getBanners(@Body HashMap params);

    //地铁
    @POST(ApiUrl.QUERYMETRO)
    Observable<MetroResponse> getMetros(@Body HashMap params);

    //标签接口
    @POST(ApiUrl.TAGLIST)
    Observable<TagResponse> getTagList(@Body HashMap params);

    //商铺纠错接口
    @POST(ApiUrl.CREATECORRECT)
    Observable<BaseResponse> createCorrect(@Body HashMap params);

    //获取阿里云TOKEN
    @POST(ApiUrl.GETTMPACCESSINFO)
    Call<AliTokenResponse> getAliToken(@Body HashMap params);
}
