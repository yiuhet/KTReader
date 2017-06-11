package com.example.yiuhet.ktreader.presenter.listener;

import com.example.yiuhet.ktreader.model.entity.UnsplashPhotoSearch;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotosList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiuhet on 2017/6/6.
 */

public interface OnUnsplashPhotoListListener {
    void onLoadPhotoListSuccess(List<UnsplashPhotosList> photosList);
//    void onLoadMoreSuccess(List<UnsplashPhotosList> photosList);

    void onLoadSearchPhotoListSuccess(UnsplashPhotoSearch photosList);
    //void onLoadSearchMoreSuccess(List<UnsplashPhotosList> photosList);

    void onLoadDataError(String error);
}
