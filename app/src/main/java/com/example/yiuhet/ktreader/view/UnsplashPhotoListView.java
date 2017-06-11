package com.example.yiuhet.ktreader.view;

import com.example.yiuhet.ktreader.model.entity.UnsplashPhotoSearch;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotosList;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/6.
 */

public interface UnsplashPhotoListView {
    void onStartGetData();

    void onGetPhotoSuccess(List<UnsplashPhotosList> photosList);

    void onGetSearchPhotoSuccess(UnsplashPhotoSearch photosList);

    void onGetDataFailed(String error);
}
