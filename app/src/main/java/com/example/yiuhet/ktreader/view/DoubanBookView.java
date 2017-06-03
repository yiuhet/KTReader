package com.example.yiuhet.ktreader.view;

import com.example.yiuhet.ktreader.model.entity.DoubanBook;

/**
 * Created by yiuhet on 2017/6/1.
 */

public interface DoubanBookView {
    void onStartGetData();

    void onGetSearchSuccess(DoubanBook doubanBook);

    void onGetDataFailed(String error);
}
