package com.finance.winport.net;

import android.text.TextUtils;
import android.util.Log;

import com.finance.winport.util.SharedPrefsUtil;

import java.io.IOException;
import java.lang.reflect.Field;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.Headers;

/**
 * Created by liuworkmac on 17/5/15.
 */

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String tokenKey = getTokenKey();
        Request newRequest = chain.request();
        if (!TextUtils.isEmpty(tokenKey)) {
            newRequest = newRequest.newBuilder()
                    .addHeader("X-token", tokenKey).build();
        }

        newRequest  = newRequest.newBuilder().addHeader("Content-Type","application/json").build();
        MediaType mediaType = newRequest.body().contentType();
        try {
            if (null != mediaType) {
                Field field = mediaType.getClass().getDeclaredField("mediaType");
                field.setAccessible(true);
                field.set(mediaType, "application/json");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return chain.proceed(newRequest);
    }

    private String getTokenKey() {
        String token;
        try {
            token = SharedPrefsUtil.getUserInfo().data.accessToken;
        } catch (Exception e) {
            token = "";
            e.printStackTrace();
        }
        Log.d(getClass().getSimpleName(), "token-->" + token);
        return "0CB9F815528983E3707F944A9113AADD";  //50EA1E6878E56AC4877FE9DCF9E3730E
    }
}
