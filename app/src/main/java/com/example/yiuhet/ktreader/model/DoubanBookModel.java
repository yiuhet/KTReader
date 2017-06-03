package com.example.yiuhet.ktreader.model;

import com.example.yiuhet.ktreader.presenter.listener.OnDoubanBookListener;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanMovieListener;

/**
 * Created by yiuhet on 2017/6/1.
 */

public interface DoubanBookModel {

    void loadSearch(String id, OnDoubanBookListener listener);//图书搜索
}
