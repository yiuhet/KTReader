package com.example.yiuhet.ktreader.presenter.listener;

import com.example.yiuhet.ktreader.model.entity.DoubanMusic;

/**
 * Created by yiuhet on 2017/6/4.
 */

public interface OnDoubanMusicListener {
    void onLoadSearchSuccess(DoubanMusic doubanMusic);//Music搜索
    void onLoadDataError(String error);
}
