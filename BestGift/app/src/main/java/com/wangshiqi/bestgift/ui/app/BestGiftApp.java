package com.wangshiqi.bestgift.ui.app;

/**
 * Created by dllo on 16/9/9.
 */

import android.app.Application;
import android.content.Context;

import cn.sharesdk.framework.ShareSDK;

/**

 - Created by dllo on 16/9/8.
 */
public class BestGiftApp extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ShareSDK.initSDK(this);
    }
    public static Context getContext() {
        return context;
    }
}
