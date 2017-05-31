package com.finance.winport.util;

/**
 * Created by liuworkmac on 17/5/31.
 */

public class H5Util {
    public static String ServerIp = "http://wph5.tst.shfc999.com/";
    public static String IP_SHOP_DETAIL = "shopsInfo.html?";

    public static String getIpShopDetail(String shopId) {
        return ServerIp+IP_SHOP_DETAIL+"shopId="+shopId;
    }
}
