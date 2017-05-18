package com.finance.winport.net;

import android.text.TextUtils;

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
        Request original = chain.request();
        if (TextUtils.isEmpty(tokenKey)) {
            return chain.proceed(original);
        }
        Request newRequest = original.newBuilder()
                .addHeader("X-token", tokenKey).addHeader("Content-Type","application/json").build();

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
        String ret = "B589650D58D553EDE28D1F5E14E8CC33";  //C8AD6FDEEF19FB0224E31C218E2C4623
        return ret;
    }
}
