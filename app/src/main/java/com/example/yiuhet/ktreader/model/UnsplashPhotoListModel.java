package com.example.yiuhet.ktreader.model;

import com.example.yiuhet.ktreader.presenter.listener.OnUnsplashPhotoListListener;

/**
 * Created by yiuhet on 2017/6/6.
 */

public interface UnsplashPhotoListModel {
    void loadSearchPhotoList(OnUnsplashPhotoListListener listener,String query);
    void loadPhotoList(OnUnsplashPhotoListListener listener);
}
