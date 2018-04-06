package com.example.yiuhet.ktreader.ui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.app.MyApplication;
import com.example.yiuhet.ktreader.ui.activity.MainActivity;
import com.example.yiuhet.ktreader.utils.FileSizeUtil;
import com.example.yiuhet.ktreader.utils.SharedPreferenceUtil;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yiuhet on 2017/6/13.
 */

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    SwitchPreference mOptionBlack ;
    ListPreference mOptionTheme;
    Preference mOptionCache;
    Preference mOptionCheck;
    Preference mOptionLook;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPer();
    }

    private void initPer() {
        mOptionBlack = (SwitchPreference) findPreference("settings_safe");
        mOptionTheme = (ListPreference) findPreference("settings_theme");
        mOptionCache = findPreference("settings_cache");
        mOptionCheck = findPreference("settings_check");
        mOptionLook = findPreference("settings_look");

        mOptionBlack.setOnPreferenceChangeListener(this);
        mOptionTheme.setOnPreferenceChangeListener(this);
        mOptionCache.setOnPreferenceClickListener(this);
        mOptionCheck.setOnPreferenceClickListener(this);
        mOptionLook.setOnPreferenceClickListener(this);


        mOptionTheme.setSummary(String.format("当前主题: %s",
                mOptionTheme.getEntry() == null ? "愣头青" : mOptionTheme.getEntry()));

        mOptionCache.setSummary(FileSizeUtil.getAutoFileOrFilesSize(MyApplication.getAppCacheDir() + "/KTReaderCache"));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mOptionTheme) {
           // mOptionTheme.setSummary("当前主题: " + mOptionTheme.getEntry());
            if (newValue.equals("indigo")) {
                mOptionTheme.setSummary("当前主题: 愣头青");
            } else if (newValue.equals("pink")) {
                mOptionTheme.setSummary("当前主题: 少女粉");
            }else if (newValue.equals("red")) {
                mOptionTheme.setSummary("当前主题: 画韩红");
            }else if (newValue.equals("green")) {
                mOptionTheme.setSummary("当前主题: 原谅绿");
            }else if (newValue.equals("purple")) {
                mOptionTheme.setSummary("当前主题: 基佬紫");
            }
        }
        Snackbar.make(getView(),"主题切换成" +
                "功,重启应用后生效",Snackbar.LENGTH_INDEFINITE)
                .setAction("重启", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }
                })
                .show();
        return true;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == mOptionCache) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage("是否清除缓存?")
                    .setPositiveButton("清除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearCache();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        } else if (preference == mOptionCheck) {
            Snackbar.make(getView(), "已是最新版本", Snackbar.LENGTH_SHORT).show();
        } else if (preference == mOptionLook) {
            goToHtml("https://github.com/yiuhet/KTReader");
        }
        return true;
    }

    private void goToHtml(String url) {
        Uri uri = Uri.parse(url);   //指定网址
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);           //指定Action
        intent.setData(uri);                            //设置Uri
        startActivity(intent);        //启动Activity
    }

    public void clearCache() {
        Observable.just(deleteFile(new File(MyApplication.getAppCacheDir() + "/KTReaderCache")))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        mOptionCache.setSummary(FileSizeUtil.getAutoFileOrFilesSize(MyApplication.getAppCacheDir() + "/KTReaderCache"));
                        Snackbar.make(getView(), "缓存已清除", Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    public boolean deleteFile(File file) {
        if (file.isFile()) {
            return file.delete();
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                return file.delete();
            }

            for (File childFile : childFiles) {
                deleteFile(childFile);
            }
            return file.delete();
        }
        return false;
    }
}
