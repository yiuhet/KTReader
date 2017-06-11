package com.example.yiuhet.ktreader.model.imp1;

import com.example.yiuhet.ktreader.api.DoubanApi;
import com.example.yiuhet.ktreader.app.Constant;
import com.example.yiuhet.ktreader.model.DoubanBookModel;
import com.example.yiuhet.ktreader.model.entity.DoubanBook;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanBookListener;
import com.example.yiuhet.ktreader.utils.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yiuhet on 2017/6/1.
 */

public class DoubanBookModelImp1 implements DoubanBookModel {

    private DoubanApi mDoubanApiService; //请求服务

    public DoubanBookModelImp1() {
        mDoubanApiService = RetrofitManager
                .getInstence()
                .getDoubanService(Constant.DOUBAN_BASE_URL); //创建请求服务
    }
    @Override
    public void loadSearch(String id, final OnDoubanBookListener listener) {
        if (mDoubanApiService != null) {
            mDoubanApiService.getSearchBookByName(id,"50")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<DoubanBook>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull DoubanBook doubanBook) {
                            listener.onLoadSearchSuccess(doubanBook);
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
