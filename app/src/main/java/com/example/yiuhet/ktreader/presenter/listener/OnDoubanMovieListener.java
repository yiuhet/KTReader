package com.example.yiuhet.ktreader.presenter.listener;

import com.example.yiuhet.ktreader.model.entity.DoubanMovieDetail;

/**
 * Created by yiuhet on 2017/5/31.
 */

public interface OnDoubanMovieListener {

    void onLoadSearchSuccess();//电影搜索
    void onLoadInTheatersSuccess(DoubanMovieDetail doubanMovieDetail);//正在热映
    void onLoadComingSoonSuccess();//即将上映
    void onLoadTop250Success();//Top250
    void onLoadWeeklySuccess();// 口碑榜
    void onLoadNewMoviesSuccess();//新片榜

    void onLoadDataError(String error);
}
