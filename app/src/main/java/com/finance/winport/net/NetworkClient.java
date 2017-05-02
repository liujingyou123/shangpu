package com.finance.winport.net;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zgwong on 16/8/17.
 * singleton OkHttpClient
 */
public class NetworkClient {
    private static OkHttpClient okHttpClient;
    private static boolean DEBUG = false;

    private NetworkClient() {
    }

    public static void init(Context ctx) {
        Context appCtx = ctx.getApplicationContext();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);

        Cache cache = new Cache(new File(appCtx.getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

        okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            throw new IllegalStateException("okHttpClient is not init");
        }
        return okHttpClient;
    }
}
