package com.example.yiuhet.ktreader.presenter.listener;

import com.example.yiuhet.ktreader.model.entity.DoubanBook;

/**
 * Created by yiuhet on 2017/6/1.
 */

public interface OnDoubanBookListener {
    void onLoadSearchSuccess(DoubanBook doubanBook);//book搜索
    void onLoadDataError(String error);
}
