package com.example.yiuhet.ktreader.view;

/**
 * Created by yiuhet on 2017/5/22.
 */

public interface ZhihuView {

    void onStartGetData();

    void onGetZhihuLatestSuccess();

    void onGetMoreSuccess();

    void onGetDataFailed(String error);

}
