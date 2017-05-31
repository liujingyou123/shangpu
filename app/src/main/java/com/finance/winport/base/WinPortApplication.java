package com.finance.winport.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.finance.winport.BuildConfig;
import com.finance.winport.aliyunoss.AliOss;
import com.baidu.mapapi.SDKInitializer;
import com.finance.winport.image.Batman;
import com.finance.winport.net.NetworkClient;
import com.finance.winport.util.SelectDialogUtil;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.SpUtil;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by liuworkmac on 17/5/2.
 */

public class WinPortApplication extends Application {
    private static WinPortApplication businessApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        businessApplication = this;
        NetworkClient.init(this);
        Batman.getInstance().init(this);
        AliOss.getInstance().init(this);
        SDKInitializer.initialize(this);
        SharedPrefsUtil.initSharedPrefers(this);
        UMShareAPI.get(this);
        JPushInterface.setDebugMode(BuildConfig.DEBUG);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this); // 初始化 JPush
        SpUtil.getInstance().init(this);
        SelectDialogUtil.getInstance().init(this);

    }

    {
        PlatformConfig.setWeixin("wx504d47f2dd5f9ad9", "a65ea8940a6eec051805e24e4e906d67");
        PlatformConfig.setQQZone("1106108474APP", "uMa1SFYnLsp4hs0i");
        PlatformConfig.setSinaWeibo("4150536070", "2386b3299bcd0be389a41e9e8436e91f", "http://sns.whalecloud.com");
//        PlatformConfig.setSinaWeibo("4134918725", "9e35ab6be6dc22d85417f50f19f44527", "http://sns.whalecloud.com");

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static WinPortApplication getInstance() {
        return businessApplication;
    }
}
