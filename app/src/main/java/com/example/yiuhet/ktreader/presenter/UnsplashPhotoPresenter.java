package com.example.yiuhet.ktreader.presenter;

/**
 * Created by yiuhet on 2017/6/8.
 */

public interface UnsplashPhotoPresenter {
    void getPhoto(String id);
    void getRandomPhoto();
    void downLoadPhoto(String id,String photoName);
    void getPhotoSize(String urlString,int pos);
}
