package com.finance.winport.net;

import com.finance.winport.BuildConfig;

public class NetUtils {

    private static String baseUrl = BuildConfig.BASEURL;

    public static String baseUrl() {
        return baseUrl;
    }
}
