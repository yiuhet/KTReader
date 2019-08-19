package com.example.yiuhet.ktreader.app;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.example.yiuhet.ktreader.CrashHandler;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.User;
import com.example.yiuhet.ktreader.utils.SharedPreferenceUtil;

/**
 * Created by yiuhet on 2017/5/23.
 */

public class MyApplication extends Application {
    private static Context sContext ;
    private static String sCacheDir;
    public static boolean isLogin;

    public static void setUser(User user) {
        MyApplication.user = user;
    }

    private static User user;
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
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance(this));
        sContext = getApplicationContext();
        if (getExternalCacheDir() != null && ExistSDCard()){
            sCacheDir = getExternalCacheDir().toString();
        } else {
            sCacheDir = getCacheDir().toString();
        }
        isLogin = SharedPreferenceUtil.getInstence().getLogin();
        if (isLogin) {
            user = SharedPreferenceUtil.getInstence().getUser();
        }
    }

    public static User getUser() {
        return user;
    }


    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}
