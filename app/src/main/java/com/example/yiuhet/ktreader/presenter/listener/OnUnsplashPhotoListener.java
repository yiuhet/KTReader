package com.example.yiuhet.ktreader.presenter.listener;

import com.example.yiuhet.ktreader.model.entity.UnsplashPhoto;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/8.
 */

public interface OnUnsplashPhotoListener {
    void onDownloadSuccess();
    void onLoadPhotoSuccess(UnsplashPhoto photo);
    void onLoadDataError(String error);
    void onLoadSizeSuccess(int size,int pos);
}
