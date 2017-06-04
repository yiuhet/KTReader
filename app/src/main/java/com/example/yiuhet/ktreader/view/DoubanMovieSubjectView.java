package com.example.yiuhet.ktreader.view;

import com.example.yiuhet.ktreader.model.entity.DoubanMovieSubject;

/**
 * Created by yiuhet on 2017/6/3.
 */

public interface DoubanMovieSubjectView {
    void onStartGetData();

    void onGetSearchSuccess(DoubanMovieSubject doubanMovieSubject);

    void onGetDataFailed(String error);
}
