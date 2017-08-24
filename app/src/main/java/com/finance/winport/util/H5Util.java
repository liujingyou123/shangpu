package com.finance.winport.util;

import com.finance.winport.net.NetUtils;

/**
 * Created by liuworkmac on 17/5/31.
 */

public class H5Util {
    public static String IP_SHOP_DETAIL = "shopsInfo.html?";
    public static String IP_AGREEMENT_DETAIL = "agreement.html";
    public static String IP_FOUND_DETAIL = "findShopInfo.html?";
    public static String IP_TRADE_DETAIL = "trade.html?";

    public static String getServerIp() {
        return NetUtils.webUrl();
    }

    public static String getIpShopDetail(String shopId) {
        return getServerIp() + IP_SHOP_DETAIL + "shopId=" + shopId;
    }


    public static String getFoundShopDetail(String contentId) {
//        return getServerIp() + IP_SHOP_DETAIL + "contentId="+contentId +"&longitude="+ longitude+"&latitude=" + latitude;
        return getServerIp() + IP_FOUND_DETAIL + "contentId="+contentId;
    }

    public static String getIpAgreementDetail() {
        return getServerIp() + IP_AGREEMENT_DETAIL;
    }

    public static String getIpTradeDetail(String contentId) {
        return getServerIp() + IP_TRADE_DETAIL + "contentId=" + contentId;
    }
}
