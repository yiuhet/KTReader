package com.example.yiuhet.ktreader.ui.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.yiuhet.ktreader.BaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.ui.fragment.SettingsFragment;
import com.example.yiuhet.ktreader.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mToolbar.setTitle("设置");
        setSupportActionBar(mToolbar);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_settings;
    }
}
