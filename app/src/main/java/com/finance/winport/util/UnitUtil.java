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
            ret = limitKNum(bg.doubleValue()) + "km";
        } else {
            ret = meters + "m";
        }
        return ret;
    }



    public static String limitNum(double num, double limit) {
        String ret;
        if (num > limit) {
            ret = formatNum(num)+"万元";
        } else {
            ret = num+"元";
        }
        return ret;
    }

    /**
     * 数字格式化（千） eg.   1234 = 1.23   1200 = 1.2
     * @param num
     * @return
     */
    public static String limitKNum(double num) {
        String ret;
        int qian = (int) (num/1000);
        int bai = (int) (num%1000)/100;
        int shi = (int)(num%100)/10;

        if (shi != 0) {
            ret = qian+"."+bai+""+shi;
        } else {
            if (bai!=0) {
                ret = qian+"."+bai+"";
            } else {
                ret = qian+"";
            }
        }

        return ret;
    }

    /**
     * 数字格式化（万） eg.   12345 = 1.23   12000 = 1.2
     * @param num
     * @return
     */
    public static String formatNum(double num) {
        String ret;
        int wang = (int) (num/10000);
        int qian = (int) (num%10000) / 1000;
        int bai = (int) (num%100)/10;

        if (bai != 0) {
            ret = wang+"."+qian+""+bai;
        } else {
            if (qian!=0) {
                ret = wang+"."+qian+"";
            } else {
                ret = wang+"";
            }
        }

        return ret;
    }

    /**
     * 处理小数点  1.0 ——> 1   1.10 ——> 1.1
     * @param num
     * @return
     */
    public static String formatMNum(float num) {
        String ret;
        DecimalFormat df = new DecimalFormat("#.##");
        ret = df.format(num);
        return ret;

    }
}
