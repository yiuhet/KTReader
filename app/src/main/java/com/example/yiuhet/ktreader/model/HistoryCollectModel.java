package com.example.yiuhet.ktreader.model;

import com.example.yiuhet.ktreader.presenter.listener.OnHistoryCollectListener;

/**
 * Created by yiuhet on 2017/6/12.
 */

public interface HistoryCollectModel {
    void loadHistory(OnHistoryCollectListener listener);
    void loadCollect(OnHistoryCollectListener listener);
    void loadUnsplash(OnHistoryCollectListener listener);
}
