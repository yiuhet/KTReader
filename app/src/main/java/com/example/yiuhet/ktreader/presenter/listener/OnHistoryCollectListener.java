package com.example.yiuhet.ktreader.presenter.listener;

import com.example.yiuhet.ktreader.model.entity.HistoryCollect;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/12.
 */

public interface OnHistoryCollectListener {
    void onHistorySuccess(List<HistoryCollect> historyList);
    void onCollectSuccess(List<HistoryCollect> collectList);
    void onUnsplashSuccess(List<HistoryCollect> unsplashList);
    void onError(String error);
}
