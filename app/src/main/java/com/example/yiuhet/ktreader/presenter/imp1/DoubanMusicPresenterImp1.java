package com.example.yiuhet.ktreader.presenter.imp1;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.entity.DoubanMusic;
import com.example.yiuhet.ktreader.model.imp1.DoubanMusicModelImp1;
import com.example.yiuhet.ktreader.presenter.DoubanMusicPresenter;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanMusicListener;
import com.example.yiuhet.ktreader.view.DoubanMusicView;

/**
 * Created by yiuhet on 2017/6/4.
 */

public class DoubanMusicPresenterImp1 extends BasePresenter<DoubanMusicView> implements DoubanMusicPresenter,OnDoubanMusicListener {

    private DoubanMusicView mDoubanMusicView;
    private DoubanMusicModelImp1 mDoubanMusicModelImp1;

    public DoubanMusicPresenterImp1(DoubanMusicView doubanMusicView) {
        mDoubanMusicView = doubanMusicView;
        mDoubanMusicModelImp1 = new DoubanMusicModelImp1();
    }

    @Override
    public void getSearch(String id) {
        mDoubanMusicView.onStartGetData();
        mDoubanMusicModelImp1.loadSearch(id,this);
    }

    @Override
    public void onLoadSearchSuccess(DoubanMusic doubanMusic) {
        mDoubanMusicView.onGetSearchSuccess(doubanMusic);
    }

    @Override
    public void onLoadDataError(String error) {
        mDoubanMusicView.onGetDataFailed(error);
    }
}
