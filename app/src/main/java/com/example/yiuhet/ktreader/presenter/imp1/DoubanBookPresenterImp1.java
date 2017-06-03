package com.example.yiuhet.ktreader.presenter.imp1;

import com.example.yiuhet.ktreader.BaseFragment;
import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.entity.DoubanBook;
import com.example.yiuhet.ktreader.model.imp1.DoubanBookModelImp1;
import com.example.yiuhet.ktreader.presenter.DoubanBookPresenter;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanBookListener;
import com.example.yiuhet.ktreader.view.DoubanBookView;

/**
 * Created by yiuhet on 2017/6/1.
 */

public class DoubanBookPresenterImp1 extends BasePresenter<DoubanBookView> implements DoubanBookPresenter,OnDoubanBookListener{
    private DoubanBookView mDoubanBookView;
    private DoubanBookModelImp1 mDoubanBookModelImp1;

    public DoubanBookPresenterImp1(DoubanBookView doubanBookView) {
        mDoubanBookView = doubanBookView;
        mDoubanBookModelImp1 = new DoubanBookModelImp1();
    }

    @Override
    public void getSearch(String id) {
        mDoubanBookView.onStartGetData();
        mDoubanBookModelImp1.loadSearch(id, this);
    }

    @Override
    public void onLoadSearchSuccess(DoubanBook doubanBook) {
        mDoubanBookView.onGetSearchSuccess(doubanBook);
    }

    @Override
    public void onLoadDataError(String error) {
        mDoubanBookView.onGetDataFailed(error);
    }
}
