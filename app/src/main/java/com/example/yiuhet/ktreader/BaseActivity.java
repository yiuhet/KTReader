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

/**
 * Created by yiuhet on 2017/5/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
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
