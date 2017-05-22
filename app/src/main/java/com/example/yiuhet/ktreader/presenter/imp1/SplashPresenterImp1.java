package com.example.yiuhet.ktreader.presenter.imp1;

import android.content.Context;
import android.os.AsyncTask;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.presenter.SplashPresenter;
import com.example.yiuhet.ktreader.utils.ShowApiUtils;
import com.example.yiuhet.ktreader.view.SplashView;

/**
 * Created by yiuhet on 2017/5/19.
 */

public class SplashPresenterImp1 extends BasePresenter<SplashView> implements SplashPresenter{

    private SplashView mSplashView;

    public SplashPresenterImp1(SplashView splashView) {
        mSplashView = splashView;
    }

    @Override
    public void loadSaying() {
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
                mSplashView.onGetSayingSuccess(s);
            } else {
                mSplashView.onGetSayingFailed();
            }
        }
    }
}
