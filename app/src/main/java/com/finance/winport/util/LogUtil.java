package com.finance.winport.util;

import android.util.Log;

import com.finance.winport.BuildConfig;


/**
 * Created by liuworkmac on 17/3/21.
 */

public class LogUtil {

    private static boolean isOpen = BuildConfig.DEBUG;

    public static void e(String msg) {
        if (isOpen) {
            Log.e("Consultant", msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isOpen) {
            Log.e(tag, msg);
        }
    }

}
