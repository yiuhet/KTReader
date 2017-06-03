package com.example.yiuhet.ktreader.presenter.listener;

import com.example.yiuhet.ktreader.model.entity.ZhihuDetail;

/**
 * Created by yiuhet on 2017/5/29.
 */

public interface OnZhihuDetailListener {

    void onLoadZhihuDetailSuccess(ZhihuDetail zhihuDetail);

    void onLoadDataError(String error);
}
