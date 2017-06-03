package com.example.yiuhet.ktreader.model.imp1;

import com.example.yiuhet.ktreader.api.DoubanApi;
import com.example.yiuhet.ktreader.app.Constant;
import com.example.yiuhet.ktreader.model.DoubanBookDetailModel;
import com.example.yiuhet.ktreader.model.entity.DoubanBookDetail;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanBookDetailListener;
import com.example.yiuhet.ktreader.utils.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yiuhet on 2017/6/2.
 */

public class DoubanBookDetailModelImp1 implements DoubanBookDetailModel {
    private DoubanApi mDoubanApiService; //请求服务

    public DoubanBookDetailModelImp1() {
        mDoubanApiService = RetrofitManager
                .getInstence()
                .getDoubanService(Constant.DOUBAN_BASE_URL);
    }

    @Override
    public void loadSearch(String id, final OnDoubanBookDetailListener listener) {
        if (mDoubanApiService != null) {
            mDoubanApiService.getSearchBookDetail(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<DoubanBookDetail>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull DoubanBookDetail doubanBookDetail) {
                            listener.onLoadDOubanBookDetailSuccess(doubanBookDetail);
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
