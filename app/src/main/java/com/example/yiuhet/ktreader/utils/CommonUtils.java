package com.example.yiuhet.ktreader.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yiuhet on 2017/5/18.
 */

public class CommonUtils {

    private static Toast mToast;

    public static void ShowTips(Context context, String tips) {
        if (mToast == null) {
            mToast = Toast.makeText(context,tips,Toast.LENGTH_SHORT);
        } else {
            mToast.setText(tips);
        }
        mToast.show();
    }
}
