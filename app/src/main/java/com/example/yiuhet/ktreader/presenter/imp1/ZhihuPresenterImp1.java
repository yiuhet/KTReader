package com.example.yiuhet.ktreader.presenter.imp1;

import android.util.Log;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.entity.ZhihuLatest;
import com.example.yiuhet.ktreader.model.imp1.ZhihuLatestModelImp1;
import com.example.yiuhet.ktreader.presenter.listener.OnZhihuLatestListener;
import com.example.yiuhet.ktreader.presenter.ZhihuPresenter;
import com.example.yiuhet.ktreader.view.ZhihuView;

import java.util.List;

/**
 * Created by yiuhet on 2017/5/22.
 */

public class ZhihuPresenterImp1 extends BasePresenter<ZhihuView> implements ZhihuPresenter,OnZhihuLatestListener{
    /*Presenter作为中间层，持有View和Model的引用*/
    private ZhihuView mZhihuView;
    private ZhihuLatestModelImp1 mZhihuLatestModelImp1;


    public ZhihuPresenterImp1(ZhihuView zhihuView) {
        mZhihuView = zhihuView;
        mZhihuLatestModelImp1 = new ZhihuLatestModelImp1();
    }

    public List<ZhihuLatest.StoriesEntity> getmZhihuLatestList() {
        return mZhihuLatestModelImp1.getmZhihuLatestList();
    }
    @Override
    public void getLatest() {
        mZhihuView.onStartGetData();
        mZhihuLatestModelImp1.loadZhihuLatest(this);
    }

    @Override
    public void getMore() {
        mZhihuView.onStartGetData();
        Log.d("ppappp","getMore");
        mZhihuLatestModelImp1.loadMore(this);
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
