package com.example.yiuhet.ktreader.presenter.imp1;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.imp1.SplashModelImp1;
import com.example.yiuhet.ktreader.presenter.listener.NetCallback;
import com.example.yiuhet.ktreader.presenter.SplashPresenter;
import com.example.yiuhet.ktreader.view.SplashView;

/**
 * Created by yiuhet on 2017/5/19.
 * 启动界面Prestener实现
 */

public class SplashPresenterImp1 extends BasePresenter<SplashView> implements SplashPresenter, NetCallback {
    /*Presenter作为中间层，持有View和Model的引用*/
    private SplashView mSplashView;
    private SplashModelImp1 mSplashModelImpl;

    public SplashPresenterImp1(SplashView splashView) {
        mSplashView = splashView;
        mSplashModelImpl = new SplashModelImp1();
    }

    @Override
    public void loadData() {
        mSplashModelImpl.loadData(this);
    }

    @Override
    public void onSuccess(String saying) {
        mSplashView.onSuccess(saying);
    }

    @Override
    public void onError() {
        mSplashView.onError();
    }

}
