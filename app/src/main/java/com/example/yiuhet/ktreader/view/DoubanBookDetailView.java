package com.example.yiuhet.ktreader.view;

import com.example.yiuhet.ktreader.model.entity.DoubanBookDetail;

/**
 * Created by yiuhet on 2017/6/1.
 */

public interface DoubanBookDetailView {
    void onStartGetData();

    void onGetSearchSuccess(DoubanBookDetail doubanBookDetail);

    void onGetDataFailed(String error);
}
