package com.example.yiuhet.ktreader.model;

import com.example.yiuhet.ktreader.presenter.listener.OnDoubanBookDetailListener;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanBookListener;

/**
 * Created by yiuhet on 2017/6/2.
 */

public interface DoubanBookDetailModel {
    void loadSearch(String id, OnDoubanBookDetailListener listener);//图书搜索
}
