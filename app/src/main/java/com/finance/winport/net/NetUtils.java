package com.finance.winport.net;

import com.finance.winport.BuildConfig;

public class NetUtils {

    private static String baseUrl = BuildConfig.BASEURL;
    private static String webUrl = BuildConfig.WEBURL;


    public static String baseUrl() {
        return baseUrl;
    }
    public static String webUrl() {
        return webUrl;
    }
}
