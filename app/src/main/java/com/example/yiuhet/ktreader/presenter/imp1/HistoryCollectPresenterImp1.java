package com.example.yiuhet.ktreader.presenter.imp1;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.entity.HistoryCollect;
import com.example.yiuhet.ktreader.model.imp1.HistoryCollectModelImp1;
import com.example.yiuhet.ktreader.presenter.HistoryCollectPresenter;
import com.example.yiuhet.ktreader.presenter.listener.OnHistoryCollectListener;
import com.example.yiuhet.ktreader.utils.MyDataBaseHelper;
import com.example.yiuhet.ktreader.view.HistoryCollectView;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/12.
 */

public class HistoryCollectPresenterImp1 extends BasePresenter<HistoryCollectView> implements HistoryCollectPresenter,OnHistoryCollectListener {

    private HistoryCollectView mHistoryCollectView;
    private HistoryCollectModelImp1 mHistoryCollectModelImp1;


    public HistoryCollectPresenterImp1(HistoryCollectView historyCollectView) {
        mHistoryCollectView = historyCollectView;
        mHistoryCollectModelImp1 = new HistoryCollectModelImp1();
    }

    @Override
    public void getData(String table) {
        mHistoryCollectView.onStartGetData();
        switch (table) {
            case MyDataBaseHelper.HISTORY:
                mHistoryCollectModelImp1.loadHistory(this);
                break;
            case MyDataBaseHelper.COLLECT:
                mHistoryCollectModelImp1.loadCollect(this);
                break;
            case MyDataBaseHelper.UNSPLASH:
                mHistoryCollectModelImp1.loadUnsplash(this);
        }

    }


    @Override
    public void onHistorySuccess(List<HistoryCollect> historyList) {
        mHistoryCollectView.onGetHistorySuccess(historyList);
    }

    @Override
    public void onCollectSuccess(List<HistoryCollect> collectList) {
        mHistoryCollectView.onGetCollectSuccess(collectList);
    }

    @Override
    public void onUnsplashSuccess(List<HistoryCollect> unsplashList) {
        mHistoryCollectView.onGetUnsplashSuccess(unsplashList);
    }

    @Override
    public void onError(String error) {
        mHistoryCollectView.onGetDataFailed(error);
    }

    public void clearHistory() {
        mHistoryCollectModelImp1.clearHistory();
    }
}
