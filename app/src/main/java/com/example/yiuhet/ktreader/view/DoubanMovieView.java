package com.example.yiuhet.ktreader.view;

/**
 * Created by yiuhet on 2017/5/31.
 */

public interface DoubanMovieView {
    void onStartGetData();

    void onGetInTheatersSuccess();
    void onGetComingSoonSuccess();
    void onGgetTop250Success();
    void onGetWeeklySuccess();
    void onGetNewMoviesSuccess();

    void onGetDataFailed(String error);
}
