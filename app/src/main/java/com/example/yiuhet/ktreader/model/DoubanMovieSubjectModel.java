package com.example.yiuhet.ktreader.model;

import com.example.yiuhet.ktreader.presenter.listener.OnDoubanMovieSubjectListener;

/**
 * Created by yiuhet on 2017/6/3.
 */

public interface DoubanMovieSubjectModel {

    void loadSearch(String id, OnDoubanMovieSubjectListener listener);
}
