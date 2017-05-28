package com.example.yiuhet.ktreader.app;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

/**
 * Created by yiuhet on 2017/5/23.
 */

public class MyApplication extends Application {
    private static Context sContext ;
    private static String sCacheDir;
    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }
    public static Context getContext() {
        return sContext;
    }
    public static String getAppCacheDir() {
        return sCacheDir;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        if (getExternalCacheDir() != null && ExistSDCard()){
            sCacheDir = getExternalCacheDir().toString();
        } else {
            sCacheDir = getCacheDir().toString();
        }
    }

    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}
