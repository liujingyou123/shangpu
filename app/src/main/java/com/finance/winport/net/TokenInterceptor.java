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
        MediaType mediaType = newRequest.body().contentType();
        try {
            if (mediaType != null) {
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
//            token = SharedPrefsUtil.getUserInfo().data.accessToken;
            token = "B589650D58D553EDE28D1F5E14E8CC33";
        } catch (Exception e) {
            token = "";
            e.printStackTrace();
        }
        Log.d(getClass().getSimpleName(), "token-->" + token);
        return token;
    }
}
