package com.example.yiuhet.ktreader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.yiuhet.ktreader.utils.CommonUtils;
import com.example.yiuhet.ktreader.utils.SharedPreferenceUtil;

/**
 * Created by yiuhet on 2017/5/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        setContentView(getLayoutRes());
        //android 5.0 以上设置直接状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    protected void initTheme() {
        String theme = SharedPreferenceUtil.getInstence().getSettingsTheme();
        if (SharedPreferenceUtil.getInstence().getSettingsSafe()) {
            setTheme(R.style.AppThemeGrey);
        } else if (theme.equals("indigo")){
            setTheme(R.style.AppTheme);
        }else if (theme.equals("pink")){
            setTheme(R.style.AppThemePink);
        }else if (theme.equals("red")){
            setTheme(R.style.AppThemeRed);
        }else if (theme.equals("green")){
            setTheme(R.style.AppThemeGreen);
        }else if (theme.equals("purple")){
            setTheme(R.style.AppThemePurple);
        }
    }

    protected void showProgress(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    protected void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    protected void startActivity(Class activity) {
        startActivity(activity, true);
    }

    protected void startActivity(Class activity, boolean finish) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        if (finish) {
            finish();
        }
    }

    protected abstract int getLayoutRes();

    protected void toast(String msg) {
        CommonUtils.ShowTips(this, msg);
    }

}
