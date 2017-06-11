package com.example.yiuhet.ktreader.model;

import com.example.yiuhet.ktreader.presenter.listener.OnUnsplashPhotoListener;

/**
 * Created by yiuhet on 2017/6/8.
 */

public interface UnsplashPhotoModel {
    void loadPhoto(OnUnsplashPhotoListener listener, String id);
    void loadRandomPhoto(OnUnsplashPhotoListener listener);
    void downloadPhoto(OnUnsplashPhotoListener listener, String id,String photoName);
    void getPhotoSize(OnUnsplashPhotoListener listener, String urlString,int pos);
}
