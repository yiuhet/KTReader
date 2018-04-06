package com.example.yiuhet.ktreader.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.yiuhet.ktreader.app.MyApplication;

/**
 * Created by yiuhet on 2017/6/14.
 */

public class SharedPreferenceUtil {

    public static final String SETTINGS_SAFE= "settings_safe";
    public static final String SETTINGS_THEME = "settings_theme";
    private SharedPreferences mPrefs;
    private static SharedPreferenceUtil sSharedPreferenceUtil;

    private SharedPreferenceUtil() {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()) ;
    }

    public static SharedPreferenceUtil getInstence() {
        if (sSharedPreferenceUtil == null) {
            synchronized (DBUtils.class) {
                if (sSharedPreferenceUtil == null) {
                    sSharedPreferenceUtil = new SharedPreferenceUtil();
                }
            }
        }
        return sSharedPreferenceUtil;
    }

   public boolean getSettingsSafe() {
        return mPrefs.getBoolean(SETTINGS_SAFE, false);
    }


    public String getSettingsTheme() {
        return mPrefs.getString(SETTINGS_THEME, "indigo");
    }

}
