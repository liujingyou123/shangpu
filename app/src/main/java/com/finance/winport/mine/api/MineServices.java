package com.finance.winport.mine.api;

import com.finance.winport.mine.model.IndustryListResponse;
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







}
