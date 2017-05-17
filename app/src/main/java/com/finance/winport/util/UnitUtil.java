package com.finance.winport.util;


import android.content.Context;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class UnitUtil {


    /**
     * 将dip转换为像素
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将像素转换为dip
     *
     * @param pxvalue
     * @return
     */
    public static float px2dip(Context context, int pxvalue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return pxvalue / scale;
    }

    /**
     * 获取屏宽度像素数
     *
     * @return
     */
    public static int getScreenWidthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度像素数
     *
     * @return
     */
    public static int getScreenHeightPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 米转公里
     *
     * @param meters
     * @return
     */
    public static double mToKm(float meters) {
        return Math.round(meters / 100d) / 10d;
    }

    /**
     * 去掉字符串中所有空格
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }

    public static String mTokm(String meters) {
        String ret;
        if (TextUtils.isEmpty(meters)) {
            return null;
        }
        BigDecimal bg = new BigDecimal(meters);

        if (bg.doubleValue() > 999) {
            ret = bg.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            ret = ret + "km";
        } else {
            ret = meters + "m";
        }
        return ret;
    }

    public static String formatNum(int num) {
        String ret;
        int yu = num % 10000;
        if (yu != 0) {
            ret = m2(num/10000f);
        } else {
            ret = num / 10000 + "";
        }

        return ret;
    }

    public static String m2(float num) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(num);

    }
}
