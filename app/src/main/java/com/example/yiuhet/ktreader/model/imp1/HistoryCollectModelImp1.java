package com.example.yiuhet.ktreader.model.imp1;

import android.app.Application;

import com.example.yiuhet.ktreader.app.MyApplication;
import com.example.yiuhet.ktreader.model.HistoryCollectModel;
import com.example.yiuhet.ktreader.model.entity.HistoryCollect;
import com.example.yiuhet.ktreader.presenter.listener.OnHistoryCollectListener;
import com.example.yiuhet.ktreader.utils.DBUtils;
import com.example.yiuhet.ktreader.utils.MyDataBaseHelper;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/12.
 */

public class HistoryCollectModelImp1 implements HistoryCollectModel {

    private List<HistoryCollect> mHistoryList;
    private List<HistoryCollect> mCollectList;
    private List<HistoryCollect> mUnsplashList;

    @Override
    public void loadHistory(OnHistoryCollectListener listener) {
        mHistoryList = DBUtils.getInstence(MyApplication.getContext()).getData(MyDataBaseHelper.HISTORY);
        if (mHistoryList.size() > 0) {
            listener.onHistorySuccess(mHistoryList);
        } else {
            listener.onError("未找到数据");
        }

    }

    @Override
    public void loadCollect(OnHistoryCollectListener listener) {
        mCollectList = DBUtils.getInstence(MyApplication.getContext()).getData(MyDataBaseHelper.COLLECT);
        if (mCollectList.size() > 0) {
            listener.onCollectSuccess(mCollectList);
        } else {
            listener.onError("未找到数据");
        }
    }

    @Override
    public void loadUnsplash(OnHistoryCollectListener listener) {
        mUnsplashList = DBUtils.getInstence(MyApplication.getContext()).getData(MyDataBaseHelper.UNSPLASH);
        if (mUnsplashList.size() > 0) {
            listener.onUnsplashSuccess(mUnsplashList);
        } else {
            listener.onError("未找到数据");
        }
    }

    public void clearHistory() {
        DBUtils.getInstence(MyApplication.getContext()).clearHistory();
    }
}
