package com.example.yiuhet.ktreader.view;

import com.example.yiuhet.ktreader.model.entity.ZhihuDetail;

/**
 * Created by yiuhet on 2017/5/29.
 */

public interface ZhihuDetailView {
    void onStartGetData();

    void onGetDetailSuccess(ZhihuDetail zhihuDetail);

    void onGetDetailFailed(String error);
}
