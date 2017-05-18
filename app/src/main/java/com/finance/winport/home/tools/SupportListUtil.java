package com.finance.winport.home.tools;

import android.text.TextUtils;

import com.finance.winport.R;

/**
 * Created by liuworkmac on 17/5/17.
 */

public class SupportListUtil {
    public static String[] names = {"下水","上水","煤气罐","天然气","三相电","烟管道","排污管道","停车位","外摆区","独卫"};
    public static int[] res = {R.mipmap.icon_sewer, R.mipmap.icon_water, R.mipmap.icon_gas_canisters, R.mipmap.icon_gas
    ,R.mipmap.icon_electricity, R.mipmap.icon_smoke, R.mipmap.icon_sewage, R.mipmap.icon_parking, R.mipmap.icon_pendulum, R.mipmap.icon_toilet};

    public static int getResByName(String name) {
        if (TextUtils.isEmpty(name)) {
            return -1;
        }
        int ret = -1;
        for (int i=0;i<names.length;i++) {
            if (name.equals(names[i])) {
                ret = res[i];
                break;
            }
        }

        return ret;
    }
}
