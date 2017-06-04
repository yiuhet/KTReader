package com.example.yiuhet.ktreader.model.imp1;

import com.example.yiuhet.ktreader.api.DoubanApi;
import com.example.yiuhet.ktreader.app.Constant;
import com.example.yiuhet.ktreader.model.DoubanMovieSubjectModel;
import com.example.yiuhet.ktreader.model.entity.DoubanMovieSubject;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanMovieSubjectListener;
import com.example.yiuhet.ktreader.utils.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yiuhet on 2017/6/3.
 */

public class DoubanMovieSubjectModelImp1 implements DoubanMovieSubjectModel{
    private DoubanApi mDoubanApiService; //请求服务

    public DoubanMovieSubjectModelImp1() {
        mDoubanApiService = RetrofitManager
                .getInstence()
                .getDoubanService(Constant.DOUBAN_BASE_URL);
    }
    @Override
    public void loadSearch(String id, final OnDoubanMovieSubjectListener listener) {
        if (mDoubanApiService != null) {
            mDoubanApiService.getMovieSubject(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<DoubanMovieSubject>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull DoubanMovieSubject doubanMovieSubject) {
                            listener.onLoadSearchSuccess(doubanMovieSubject);
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
}
