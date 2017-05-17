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
                .addHeader("X-token", tokenKey).build();
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
        String ret = "C8AD6FDEEF19FB0224E31C218E2C4623";
        return ret;
    }
}
