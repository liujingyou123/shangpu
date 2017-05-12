package com.finance.winport.base;

import android.app.Application;

import com.finance.winport.aliyunoss.AliOss;
import com.finance.winport.image.Batman;
import com.finance.winport.net.NetworkClient;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by liuworkmac on 17/5/2.
 */

public class WinPortApplication extends Application {
    private static WinPortApplication businessApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkClient.init(this);
        Batman.getInstance().init(this);
        ShareSDK.initSDK(this);
        AliOss.getInstance().init(this);
    }

    public static WinPortApplication getInstance() {
        return businessApplication;
    }
}
