package com.finance.winport.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.finance.winport.account.model.UserInfo;
import com.google.gson.Gson;

public class SharedPrefsUtil {
    /**
     * 保存用户信息
     */
    public static final String USER_INFO = "user_info";

    private static SharedPreferences sp;

    public static void initSharedPrefers(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_APPEND);
        }
    }


    /**
     * 保存用户信息
     */
    public static void saveUserInfo(UserInfo userInfo) {
        String jsonStr = "";
        if (userInfo != null) {
            Gson g = new Gson();
            jsonStr = g.toJson(userInfo);
        }
        sp.edit().putString(USER_INFO, jsonStr).commit();
    }

    /**
     * 获取用户信息
     */
    public static UserInfo getUserInfo() {
        String s = "";
        UserInfo userInfo;
        s = sp.getString(USER_INFO, "");
        if (TextUtils.isEmpty(s)) {
            return null;
        } else {
            Gson g = new Gson();
            userInfo = g.fromJson(s, UserInfo.class);
            return userInfo;
        }

    }

    public static boolean isFirstPost() {
        return sp.getBoolean("isFirstPost", true);
    }

    public static void setFirstPost(boolean isFirst) {
        sp.edit().putBoolean("isFirstPost", isFirst).commit();
    }

    public static void clearUserInfo() {
        sp.edit().putString(USER_INFO, "").commit();
    }

}
