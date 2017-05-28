package com.example.yiuhet.ktreader.model.imp1;

import android.os.AsyncTask;

import com.example.yiuhet.ktreader.model.SplashModel;
import com.example.yiuhet.ktreader.presenter.OnSplashListener;
import com.example.yiuhet.ktreader.utils.ShowApiUtils;

/**
 * Created by yiuhet on 2017/5/28.
 */

public class SplashModelImp1 implements SplashModel {
    /*获取易源api励志语句的Model实现*/

    private OnSplashListener listener; //回调接口
    @Override
    public void loadSaying(OnSplashListener listener) {
        this.listener = listener;
        new ShowAsyncTask().execute(ShowApiUtils.SAYING);
    }

    //使用基本的AsyncTask处理网络请求
    class ShowAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return ShowApiUtils.parseJsonFromSaying(ShowApiUtils.getData(params[0]));
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                listener.onSuccess(s); //网络请求数据不为空时 回调成功方法。
            } else {
                listener.onError(); //回调失败方法。
            }
        }
    }
}
