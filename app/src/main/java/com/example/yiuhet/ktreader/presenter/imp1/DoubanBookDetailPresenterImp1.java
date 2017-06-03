package com.example.yiuhet.ktreader.presenter.imp1;

import com.example.yiuhet.ktreader.BaseFragment;
import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.DoubanBookDetailModel;
import com.example.yiuhet.ktreader.model.entity.DoubanBookDetail;
import com.example.yiuhet.ktreader.model.imp1.DoubanBookDetailModelImp1;
import com.example.yiuhet.ktreader.presenter.DoubanBookDetailPresenter;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanBookDetailListener;
import com.example.yiuhet.ktreader.view.DoubanBookDetailView;

/**
 * Created by yiuhet on 2017/6/2.
 */

public class DoubanBookDetailPresenterImp1 extends BasePresenter<DoubanBookDetailView> implements DoubanBookDetailPresenter,OnDoubanBookDetailListener{

    private DoubanBookDetailView mDoubanBookDetailView;
    private DoubanBookDetailModelImp1 mDoubanBookDetailModelImp1;

    public DoubanBookDetailPresenterImp1(DoubanBookDetailView doubanBookDetailView) {
        mDoubanBookDetailModelImp1 = new DoubanBookDetailModelImp1();
        mDoubanBookDetailView = doubanBookDetailView;
    }

    @Override
    public void getDetail(String id) {
        mDoubanBookDetailView.onStartGetData();
        mDoubanBookDetailModelImp1.loadSearch(id, this);
    }

    @Override
    public void onLoadDOubanBookDetailSuccess(DoubanBookDetail doubanBookDetail) {
        mDoubanBookDetailView.onGetSearchSuccess(doubanBookDetail);
    }

    @Override
    public void onLoadDataError(String error) {
        mDoubanBookDetailView.onGetDataFailed(error);
    }
}
