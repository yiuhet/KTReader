package com.example.yiuhet.ktreader.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.yiuhet.ktreader.app.MyApplication;
import com.example.yiuhet.ktreader.model.entity.User;

/**
 * Created by yiuhet on 2017/6/14.
 */

public class SharedPreferenceUtil {

    public static final String SETTINGS_SAFE= "settings_safe";
    public static final String SETTINGS_THEME = "settings_theme";
    public static final String ISLOGIN = "info_islogin";
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

    public User getUser() {
        User user = new User();
        user.setSex(mPrefs.getString("user_sex", null));
        user.setName(mPrefs.getString("user_name", "游客"));
        user.setId(mPrefs.getString("user_id", "请登录..."));
        Log.e("yiuhet", "保存的user信息:" + user.toString());
        return user;
    }

    public void setUser(User user) {
        mPrefs.edit().putString("user_sex", user.getSex());
        mPrefs.edit().putString("user_name", user.getName());
        mPrefs.edit().putString("user_id", user.getId());
    }

    public void setLogin(boolean isLogin) {
        mPrefs.edit().putBoolean("user_islogin", isLogin);
    }
    public boolean getLogin() {
        return mPrefs.getBoolean("user_islogin", false);
    }



}
