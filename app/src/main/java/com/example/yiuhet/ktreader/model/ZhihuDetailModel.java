package com.example.yiuhet.ktreader.model;

import com.example.yiuhet.ktreader.presenter.listener.OnZhihuDetailListener;

/**
 * Created by yiuhet on 2017/5/29.
 * 知乎日报详情Model接口
 */

public interface ZhihuDetailModel {

    void loadDetail(String id, OnZhihuDetailListener listener);
}
