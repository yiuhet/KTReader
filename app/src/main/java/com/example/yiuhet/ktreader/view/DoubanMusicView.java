package com.example.yiuhet.ktreader.view;

import com.example.yiuhet.ktreader.model.entity.DoubanMusic;

/**
 * Created by yiuhet on 2017/6/4.
 */

public interface DoubanMusicView {
    void onStartGetData();

    void onGetSearchSuccess(DoubanMusic doubanMusic);

    void onGetDataFailed(String error);
}
