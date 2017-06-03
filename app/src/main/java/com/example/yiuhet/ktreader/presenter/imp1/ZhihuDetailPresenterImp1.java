package com.example.yiuhet.ktreader.presenter.imp1;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.entity.ZhihuDetail;
import com.example.yiuhet.ktreader.model.imp1.ZhihuDetailModelImp1;
import com.example.yiuhet.ktreader.presenter.listener.OnZhihuDetailListener;
import com.example.yiuhet.ktreader.presenter.ZhihuDetailPresenter;
import com.example.yiuhet.ktreader.view.ZhihuDetailView;

/**
 * Created by yiuhet on 2017/5/29.
 */

public class ZhihuDetailPresenterImp1 extends BasePresenter<ZhihuDetailView> implements ZhihuDetailPresenter,OnZhihuDetailListener{
    /*Presenter作为中间层，持有View和Model的引用*/
    private ZhihuDetailView mZhihuDetailView;
    private ZhihuDetailModelImp1 zhihuDetailModelImp1;
    String id;

    public ZhihuDetailPresenterImp1(ZhihuDetailView zhihuDetailView) {
        mZhihuDetailView = zhihuDetailView;
        zhihuDetailModelImp1 = new ZhihuDetailModelImp1();
    }


    @Override
    public void getDetail(String id) {
        mZhihuDetailView.onStartGetData();
        zhihuDetailModelImp1.loadDetail(id, this);
    }


    @Override
    public void onLoadZhihuDetailSuccess(ZhihuDetail zhihuDetail) {

        mZhihuDetailView.onGetDetailSuccess(zhihuDetail);
    }

    @Override
    public void onLoadDataError(String error) {
        mZhihuDetailView.onGetDetailFailed(error);
    }
}
