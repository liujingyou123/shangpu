package com.finance.winport.base;

import android.app.Application;

import com.finance.winport.aliyunoss.AliOss;
import com.baidu.mapapi.SDKInitializer;
import com.finance.winport.image.Batman;
import com.finance.winport.net.NetworkClient;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

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
        AliOss.getInstance().init(this);
        SDKInitializer.initialize(this);

        UMShareAPI.get(this);


    }

    {
        PlatformConfig.setWeixin("","");
        PlatformConfig.setQQZone("","");
        PlatformConfig.setSinaWeibo("4150536070","2386b3299bcd0be389a41e9e8436e91f","http://sns.whalecloud.com");

    }

    public static WinPortApplication getInstance() {
        return businessApplication;
    }
}
