package com.example.yiuhet.ktreader.model.imp1;

import android.os.SystemClock;
import android.util.Log;

import com.example.yiuhet.ktreader.api.TupianApi;
import com.example.yiuhet.ktreader.model.UnsplashPhotoListModel;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhoto;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotoSearch;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotosList;
import com.example.yiuhet.ktreader.presenter.listener.OnUnsplashPhotoListListener;
import com.example.yiuhet.ktreader.utils.RetrofitManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yiuhet on 2017/6/6.
 */

public class UnsplashPhotoListModelImp1 implements UnsplashPhotoListModel{

    private static final String UNSPLASH_APPLICATION_ID = "c38ecf4661326d08903149c16235862997f1afc00ed5e294a550f4305e59dcdf";
    private static final String UNSPLASH_BASE_URL = "https://api.unsplash.com/";
    private int PAGE = 1;
    private int PAGE_SEARCH = 1;
    private int PER_PAGE = 10;
    private String[] ORDERBY = {"latest", "oldest", "popular"};
    //排序方式 ，0，1，2分别为"latest", "oldest", "popular"。
    private int STATE = 0;
    private TupianApi mTupianApi;

    public UnsplashPhotoListModelImp1() {
        mTupianApi = RetrofitManager
                .getInstence()
                .getTupianService(UNSPLASH_BASE_URL);
    }

    public void setState(int state) {
        STATE = state;
    }

    @Override
    public void loadSearchPhotoList(final OnUnsplashPhotoListListener listener, String query) {
        if (mTupianApi != null) {
            mTupianApi.getSearchUnsplashPhotosList(UNSPLASH_APPLICATION_ID, query, PAGE_SEARCH, PER_PAGE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UnsplashPhotoSearch>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull UnsplashPhotoSearch unsplashPhotosLists) {
                            Log.d("testquery","onNext");
                            listener.onLoadSearchPhotoListSuccess(unsplashPhotosLists);
                            PAGE_SEARCH ++;
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d("testquery",e.toString());
                            listener.onLoadDataError(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void loadPhotoList(final OnUnsplashPhotoListListener listener) {
        if (mTupianApi != null) {
            mTupianApi.getUnsplashPhotosList(UNSPLASH_APPLICATION_ID, PAGE, PER_PAGE, ORDERBY[STATE])
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<UnsplashPhotosList>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull List<UnsplashPhotosList> unsplashPhotosLists) {
                            listener.onLoadPhotoListSuccess(unsplashPhotosLists);
                            PAGE++;
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
