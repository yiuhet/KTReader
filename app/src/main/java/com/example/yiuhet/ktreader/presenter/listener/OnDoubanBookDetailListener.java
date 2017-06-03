package com.example.yiuhet.ktreader.presenter.listener;

import com.example.yiuhet.ktreader.model.entity.DoubanBookDetail;

/**
 * Created by yiuhet on 2017/6/2.
 */

public interface OnDoubanBookDetailListener {

    void onLoadDOubanBookDetailSuccess(DoubanBookDetail doubanBookDetail);

    void onLoadDataError(String error);
}
