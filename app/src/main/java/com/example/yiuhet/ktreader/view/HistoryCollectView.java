package com.example.yiuhet.ktreader.view;

import com.example.yiuhet.ktreader.model.entity.HistoryCollect;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/12.
 */

public interface HistoryCollectView {

    void onStartGetData();
    void onGetHistorySuccess(List<HistoryCollect> history);
    void onGetCollectSuccess(List<HistoryCollect> collect);
    void onGetUnsplashSuccess(List<HistoryCollect> unsplash);
    void onGetDataFailed(String error);
}
