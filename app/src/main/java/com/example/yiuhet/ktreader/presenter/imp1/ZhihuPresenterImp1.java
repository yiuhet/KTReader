package com.example.yiuhet.ktreader.presenter.imp1;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.api.ZhihuApi;
import com.example.yiuhet.ktreader.model.entity.ZhihuLatest;
import com.example.yiuhet.ktreader.model.imp1.ZhihuLatestModelImp1;
import com.example.yiuhet.ktreader.presenter.OnZhihuLatestListener;
import com.example.yiuhet.ktreader.presenter.ZhihuPresenter;
import com.example.yiuhet.ktreader.utils.RetrofitManager;
import com.example.yiuhet.ktreader.view.ZhihuView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yiuhet on 2017/5/22.
 */

public class ZhihuPresenterImp1 extends BasePresenter<ZhihuView> implements ZhihuPresenter,OnZhihuLatestListener{
    /*Presenter作为中间层，持有View和Model的引用*/
    private ZhihuView mZhihuView;
    private ZhihuLatestModelImp1 zhihuLatestModelImp1;


    public ZhihuPresenterImp1(ZhihuView zhihuView) {
        mZhihuView = zhihuView;
        zhihuLatestModelImp1 = new ZhihuLatestModelImp1();
    }

    public List<ZhihuLatest.StoriesEntity> getmZhihuLatestList() {
        return zhihuLatestModelImp1.getmZhihuLatestList();
    }
    @Override
    public void getLatest() {
        mZhihuView.onStartGetData();
        zhihuLatestModelImp1.loadZhihuLatest(this);
    }

    @Override
    public void getMore() {
        mZhihuView.onStartGetData();
        zhihuLatestModelImp1.loadMore(this);
    }


    @Override
    public void onLoadZhihuLatestSuccess() {
        mZhihuView.onGetZhihuLatestSuccess();
    }

    @Override
    public void onLoadMoreSuccess() {
        mZhihuView.onGetMoreSuccess();
    }


    @Override
    public void onLoadDataError(String error) {
        mZhihuView.onGetDataFailed(error);
    }

}
