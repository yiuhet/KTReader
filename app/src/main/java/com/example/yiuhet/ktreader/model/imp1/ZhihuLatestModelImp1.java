package com.example.yiuhet.ktreader.model.imp1;

import com.example.yiuhet.ktreader.api.ZhihuApi;
import com.example.yiuhet.ktreader.model.ZhihuLatestModel;
import com.example.yiuhet.ktreader.model.entity.ZhihuLatest;
import com.example.yiuhet.ktreader.presenter.OnZhihuLatestListener;
import com.example.yiuhet.ktreader.utils.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yiuhet on 2017/5/28.
 */

public class ZhihuLatestModelImp1 implements ZhihuLatestModel {
    /*获取知乎日报数据的Model实现*/

    private ZhihuApi mZhihuApiService; //请求服务
    private List<ZhihuLatest.StoriesEntity> mZhihuLatestList; //储存entity的list。
    private String date; //网络请求的url参数，首次加载数据获取，调用getmore方法时，date-1.

    public ZhihuLatestModelImp1 () {
        mZhihuLatestList = new ArrayList<>();
        mZhihuApiService = RetrofitManager
                .getInstence()
                .getRetrofit("http://news-at.zhihu.com/api/4/news/")
                .create(ZhihuApi.class); //创建请求服务
    }

    public List<ZhihuLatest.StoriesEntity> getmZhihuLatestList(){
        return mZhihuLatestList;
    }

    @Override
    public void loadZhihuLatest(final OnZhihuLatestListener listener) {
        mZhihuLatestList.clear();
        //数据层的操作，网络请求数据
        if (mZhihuApiService != null) {
            mZhihuApiService.getZhihuLatest()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ZhihuLatest>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }
                        @Override
                        public void onNext(@NonNull ZhihuLatest zhihuLatest) {
                            date = zhihuLatest.date;
                            for (int i =0;i < zhihuLatest.stories.size(); i++) {
                                mZhihuLatestList.add(zhihuLatest.stories.get(i));
                            }
                            listener.onLoadZhihuLatestSuccess(); //加载成功时 回调接口方法。
                        }
                        @Override
                        public void onError(@NonNull Throwable e) {
                            listener.onLoadDataError(e.toString());//加载失败时 回调接口方法。
                        }
                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    @Override
    public void loadMore(final OnZhihuLatestListener listener) {
        date = String.valueOf(Integer.parseInt(date) - 1);
        //数据层的操作，网络请求数据
        if (mZhihuApiService != null) {
            mZhihuApiService.getBefore(date)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ZhihuLatest>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull ZhihuLatest zhihuLatest) {
                            for (int i =0;i < zhihuLatest.stories.size(); i++) {
                                mZhihuLatestList.add(zhihuLatest.stories.get(i));
                            }
                            listener.onLoadMoreSuccess();//加载成功时 回调接口方法。
                        }
                        @Override
                        public void onError(@NonNull Throwable e) {
                            listener.onLoadDataError(e.toString());//加载失败时 回调接口方法。
                        }
                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }
}
