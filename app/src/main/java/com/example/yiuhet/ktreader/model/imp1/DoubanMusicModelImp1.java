package com.example.yiuhet.ktreader.model.imp1;

import com.example.yiuhet.ktreader.api.DoubanApi;
import com.example.yiuhet.ktreader.app.Constant;
import com.example.yiuhet.ktreader.model.DoubanMusicModel;
import com.example.yiuhet.ktreader.model.entity.DoubanBook;
import com.example.yiuhet.ktreader.model.entity.DoubanMusic;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanMusicListener;
import com.example.yiuhet.ktreader.utils.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yiuhet on 2017/6/4.
 */

public class DoubanMusicModelImp1 implements DoubanMusicModel {

    private DoubanApi mDoubanApiService; //请求服务

    public DoubanMusicModelImp1() {
        mDoubanApiService = RetrofitManager
                .getInstence()
                .getDoubanService(Constant.DOUBAN_BASE_URL); //创建请求服务
    }
    @Override
    public void loadSearch(String id, final OnDoubanMusicListener listener) {
        if (mDoubanApiService != null) {
            mDoubanApiService.getSearchMusicByTag(id,"20")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<DoubanMusic>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull DoubanMusic doubanMusic) {
                            listener.onLoadSearchSuccess(doubanMusic);
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
