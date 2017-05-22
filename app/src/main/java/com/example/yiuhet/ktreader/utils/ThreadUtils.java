package com.example.yiuhet.ktreader.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by yiuhet on 2017/5/6.
 */

public class ThreadUtils {
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static Executor sExecutor = Executors.newSingleThreadExecutor();

    public static void runOnUiThread(Runnable runnable) {
        sHandler.post(runnable);
    }

    public static void runOnBackgroundThread(Runnable runnable) {
        sExecutor.execute(runnable);
    }
}
