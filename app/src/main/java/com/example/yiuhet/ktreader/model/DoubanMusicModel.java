package com.example.yiuhet.ktreader.model;

import com.example.yiuhet.ktreader.presenter.listener.OnDoubanMusicListener;

/**
 * Created by yiuhet on 2017/6/4.
 */

public interface DoubanMusicModel {
    void loadSearch(String id, OnDoubanMusicListener listener);
}
