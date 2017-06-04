package com.example.yiuhet.ktreader.presenter.listener;

import com.example.yiuhet.ktreader.model.DoubanMovieSubjectModel;
import com.example.yiuhet.ktreader.model.entity.DoubanMovieSubject;

/**
 * Created by yiuhet on 2017/6/3.
 */

public interface OnDoubanMovieSubjectListener {
    void onLoadSearchSuccess(DoubanMovieSubject doubanMovieSubject);
    void onLoadDataError(String error);
}
