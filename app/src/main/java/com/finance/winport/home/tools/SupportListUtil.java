package com.finance.winport.home.tools;

import android.text.TextUtils;

import com.finance.winport.R;

/**
 * Created by liuworkmac on 17/5/17.
 */

public class SupportListUtil {
    public static String[] names = {"下水","上水","煤气罐","天然气","三相电","烟管道","排污管道","停车位","外摆区","独卫"};
    public static int[] resSelect = {R.mipmap.icon_sewer, R.mipmap.icon_water, R.mipmap.icon_gas_canisters, R.mipmap.icon_gas
    ,R.mipmap.icon_electricity, R.mipmap.icon_smoke, R.mipmap.icon_sewage, R.mipmap.icon_parking, R.mipmap.icon_pendulum, R.mipmap.icon_toilet};

    public static int[] resNormal = {R.mipmap.icon_sewer_normal, R.mipmap.icon_water_normal, R.mipmap.icon_gas_canisters_normal, R.mipmap.icon_gas_normal
            ,R.mipmap.icon_electricity_normal, R.mipmap.icon_smoke_normal, R.mipmap.icon_sewage_normal, R.mipmap.icon_parking_normal, R.mipmap.icon_pendulum_normal, R.mipmap.icon_toilet_normal};


    public static int getResSelctByName(String name) {
        if (TextUtils.isEmpty(name)) {
            return -1;
        }
        int ret = -1;
        for (int i=0;i<names.length;i++) {
            if (name.equals(names[i]) || name.contains(names[i])) {
                ret = resSelect[i];
                break;
            }
        }

        return ret;
    }

    public static int getResNormalByName(String name) {
        if (TextUtils.isEmpty(name)) {
            return -1;
        }
        int ret = -1;
        for (int i=0;i<names.length;i++) {
            if (name.equals(names[i]) || name.contains(names[i])) {
                ret = resNormal[i];
                break;
            }
        }

        return ret;
    }}
