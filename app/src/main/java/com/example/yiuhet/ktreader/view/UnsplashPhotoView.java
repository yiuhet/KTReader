package com.example.yiuhet.ktreader.view;

import com.example.yiuhet.ktreader.model.entity.UnsplashPhoto;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/8.
 */

public interface UnsplashPhotoView {
    void onStartGetData();
    void onGetPhotoSuccess(UnsplashPhoto photo);
    void onGetDataFailed(String error);
    void onGetSizeSuccess(int size,int pos);
}
