package com.example.yiuhet.ktreader.presenter.imp1;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.imp1.SplashModelImp1;
import com.example.yiuhet.ktreader.presenter.listener.OnSplashListener;
import com.example.yiuhet.ktreader.presenter.SplashPresenter;
import com.example.yiuhet.ktreader.view.SplashView;

/**
 * Created by yiuhet on 2017/5/19.
 * 启动界面Prestener实现
 */

public class SplashPresenterImp1 extends BasePresenter<SplashView> implements SplashPresenter,OnSplashListener{
    /*Presenter作为中间层，持有View和Model的引用*/
    private SplashView mSplashView;
    private SplashModelImp1 splashModelImp1;

    public SplashPresenterImp1(SplashView splashView) {
        mSplashView = splashView;
        splashModelImp1 = new SplashModelImp1();
    }

    @Override
    public void loadSaying() {
        splashModelImp1.loadSaying(this);
    }

    @Override
    public void onSuccess(String saying) {
        mSplashView.onGetSayingSuccess(saying);
    }

    @Override
    public void onError() {
        mSplashView.onGetSayingFailed();
    }

}
