package com.finance.winport.net;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

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
        return chain.proceed(newRequest);
    }

    private String getTokenKey() {
        String ret = "B589650D58D553EDE28D1F5E14E8CC33";
        return ret;
    }
}
