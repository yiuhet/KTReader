package com.example.yiuhet.ktreader;

import android.content.Context;
import android.util.Log;

/**
 * Created by yiuhet on 2018/6/11.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private Context mContext;
    volatile private static CrashHandler sCrashHandler;

    private CrashHandler(Context context) {
        mContext = context;
    }

    public static CrashHandler getInstance(Context context){
        if (sCrashHandler == null) {
            synchronized (CrashHandler.class){
                if (sCrashHandler == null) {
                    //使用Application Context
                    sCrashHandler=new CrashHandler(context.getApplicationContext());
                }
            }
        }
        return sCrashHandler;
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
         Log.e(TAG, "uncaughtException: "+t.getName()+" "+e.getMessage());
        //这里可以将异常信息保存或上传
        //...

        //可根据情况选择是否干掉当前的进程
//        android.os.Process.killProcess(android.os.Process.myPid());
    }
}