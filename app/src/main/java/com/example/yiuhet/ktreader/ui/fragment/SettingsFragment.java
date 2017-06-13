package com.example.yiuhet.ktreader.ui.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.yiuhet.ktreader.R;

/**
 * Created by yiuhet on 2017/6/13.
 */

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    SwitchPreference mOptionBlack ;
    ListPreference mOptionLR ;
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
        mOptionLR = (ListPreference) findPreference("settings_lr");
        mOptionTheme = (ListPreference) findPreference("settings_theme");
        mOptionCache = findPreference("settings_cache");
        mOptionCheck = findPreference("settings_check");
        mOptionLook = findPreference("settings_look");

        mOptionBlack.setOnPreferenceChangeListener(this);
        mOptionLR.setOnPreferenceChangeListener(this);
        mOptionTheme.setOnPreferenceChangeListener(this);
        mOptionCache.setOnPreferenceClickListener(this);
        mOptionCheck.setOnPreferenceClickListener(this);
        mOptionLook.setOnPreferenceClickListener(this);
        mOptionTheme.setSummary(String.format("当前主题: %s",mOptionTheme.getEntry()));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mOptionBlack) {

        } else if (preference == mOptionLR) {

        } else if (preference == mOptionTheme) {

        }
        return true;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == mOptionCache) {

        } else if (preference == mOptionCheck) {

        } else if (preference == mOptionLook) {

        }
        return true;
    }
}
