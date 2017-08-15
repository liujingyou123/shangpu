package com.finance.winport.net;

import android.text.TextUtils;
import android.util.Log;

import com.finance.winport.util.SharedPrefsUtil;

import java.io.IOException;
import java.lang.reflect.Field;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuworkmac on 17/5/15.
 */

public class TokenInterceptor implements Interceptor {

    private final String VERSION_PATH_SEGMENT = "v1.2.0";

    @Override
    public Response intercept(Chain chain) throws IOException {
        String tokenKey = getTokenKey();
        Request newRequest = chain.request();
        HttpUrl url = newRequest.url().newBuilder().addPathSegment(VERSION_PATH_SEGMENT).build();
        Request.Builder newBuilder = newRequest.newBuilder().url(url);
        if (!TextUtils.isEmpty(tokenKey)) {
            newBuilder.addHeader("X-token", tokenKey);
//            newRequest = newRequest.newBuilder()
//                    .addHeader("X-token", tokenKey).build();
        }

        newBuilder.addHeader("Content-Type", "application/json");
//        newRequest = newRequest.newBuilder().addHeader("Content-Type", "application/json").build();
        newRequest = newBuilder.build();
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
        String token = null;
        try {
            if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().data != null) {
                token = SharedPrefsUtil.getUserInfo().data.accessToken;
            }
        } catch (Exception e) {
            token = "";
            e.printStackTrace();
        }
        Log.d(getClass().getSimpleName(), "token-->" + token);
        return token;
    }
}
