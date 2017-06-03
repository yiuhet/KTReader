package com.example.yiuhet.ktreader.model.imp1;

import android.util.Log;

import com.example.yiuhet.ktreader.api.DoubanApi;
import com.example.yiuhet.ktreader.app.Constant;
import com.example.yiuhet.ktreader.model.DoubanMovieModel;
import com.example.yiuhet.ktreader.model.entity.DoubanMovieDetail;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanMovieListener;
import com.example.yiuhet.ktreader.utils.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yiuhet on 2017/5/31.
 */

public class DoubanMovieModelImp1 implements DoubanMovieModel {

    private DoubanApi mDoubanApiService; //请求服务

    public DoubanMovieModelImp1() {
        mDoubanApiService = RetrofitManager
                .getInstence()
                .getDoubanService(Constant.DOUBAN_BASE_URL); //创建请求服务
    }
    @Override
    public void loadSearch(String id, OnDoubanMovieListener listener) {

    }

    @Override
    public void loadInTheaters(final OnDoubanMovieListener listener) {
        Log.d("testTime", "开始下载数据， 时间： " + String.valueOf(System.currentTimeMillis()));
        if (mDoubanApiService != null) {
            mDoubanApiService.getInTheaters()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<DoubanMovieDetail>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull DoubanMovieDetail doubanMovieDetail) {
                            Log.d("testTime", "数据下载完成 时间： " + String.valueOf(System.currentTimeMillis()));
                            listener.onLoadInTheatersSuccess(doubanMovieDetail);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            listener.onLoadDataError(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void loadComingSoon(OnDoubanMovieListener listener) {

    }

    @Override
    public void loadTop250(OnDoubanMovieListener listener) {

    }

    @Override
    public void loadWeekly(OnDoubanMovieListener listener) {

    }

    @Override
    public void loadNewMovies(OnDoubanMovieListener listener) {

    }
}
