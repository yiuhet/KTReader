package com.example.yiuhet.ktreader.model;

import com.example.yiuhet.ktreader.presenter.listener.OnZhihuLatestListener;

/**
 * Created by yiuhet on 2017/5/28.
 * 知乎日报Model接口
 */

public interface ZhihuLatestModel {

    void loadZhihuLatest(OnZhihuLatestListener listener);

    void loadMore(OnZhihuLatestListener listener);
}
