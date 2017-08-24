package com.finance.winport.account.net;

import com.finance.winport.account.model.ImageVerifyCode;
import com.finance.winport.account.model.Message;
import com.finance.winport.account.model.UserInfo;
import com.finance.winport.base.BaseResponse;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xzw on 2017/5/17.
 */

public interface UserService {
    // 登录
    @POST("customerapp/api/user/verify/loginVerify")
    Observable<UserInfo> login(@Body HashMap<String, Object> params);

    // 发送验证码
    @POST("customerapp/api/user/verify/sendSmsVerifyCode")
    Observable<Message> getVerifyCode(@Body HashMap<String, Object> params);

    // 获取图片验证码
    @POST("customerapp/api/user/verify/getPicVerifyCode")
    Observable<ImageVerifyCode> getPicCode(@Body HashMap<String, Object> params);

    // 退出登录
    @POST("customerapp/api/user/verify/logoutVerify")
    Observable<BaseResponse> loginOut(@Body HashMap<String, Object> params);


}
