package com.finance.winport.account.net;

import com.finance.winport.account.model.ImageVerifyCode;
import com.finance.winport.account.model.Message;
import com.finance.winport.account.model.UserInfo;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.tab.model.AppointRanking;
import com.finance.winport.tab.model.AppointShopList;
import com.finance.winport.tab.model.AppointStatistics;
import com.finance.winport.tab.model.Prediction;
import com.finance.winport.tab.model.ScanCount;
import com.finance.winport.tab.model.WinportList;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xzw on 2017/5/17.
 */

public interface UserService {
    // 登录
    @POST("customerapp/api/user/verify/loginVerify/v1.0.0")
    Observable<UserInfo> login(@Body HashMap<String, Object> params);

    // 发送验证码
    @POST("customerapp/api/user/verify/sendSmsVerifyCode/v1.0.0")
    Observable<Message> getVerifyCode(@Body HashMap<String, Object> params);

    // 获取图片验证码
    @POST("customerapp/api/user/verify/getPicVerify/v1.0.0")
    Observable<ImageVerifyCode> getPicCode(@Body HashMap<String, Object> params);


}
