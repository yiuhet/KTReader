package com.example.yiuhet.ktreader.presenter.imp1;

import android.util.Log;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhoto;
import com.example.yiuhet.ktreader.model.imp1.UnsplashPhotoModelImp1;
import com.example.yiuhet.ktreader.presenter.UnsplashPhotoPresenter;
import com.example.yiuhet.ktreader.presenter.listener.OnUnsplashPhotoListener;
import com.example.yiuhet.ktreader.view.UnsplashPhotoView;

/**
 * Created by yiuhet on 2017/6/8.
 */

public class UnsplashPhotoPresenterImp1 extends BasePresenter<UnsplashPhotoView> implements UnsplashPhotoPresenter,OnUnsplashPhotoListener {
    private UnsplashPhotoView mUnsplashPhotoView;
    private UnsplashPhotoModelImp1 mUnsplashPhotoModelImp1;

    public UnsplashPhotoPresenterImp1(UnsplashPhotoView unsplashPhotoView) {
        mUnsplashPhotoView = unsplashPhotoView;
        mUnsplashPhotoModelImp1 = new UnsplashPhotoModelImp1();
    }


    @Override
    public void getPhoto(String id) {
        mUnsplashPhotoView.onStartGetData();
        mUnsplashPhotoModelImp1.loadPhoto(this,id);
    }

    @Override
    public void getRandomPhoto() {
        mUnsplashPhotoView.onStartGetData();
        mUnsplashPhotoModelImp1.loadRandomPhoto(this);
    }
    @Override
    public void downLoadPhoto(String id,String photoName) {
        mUnsplashPhotoView.onStartGetData();
        mUnsplashPhotoModelImp1.downloadPhoto(this,id,photoName);
    }

    @Override
    public void getPhotoSize(String urlString,int pos) {
        mUnsplashPhotoModelImp1.getPhotoSize(this, urlString, pos);
    }

    @Override
    public void onDownloadSuccess() {
        mUnsplashPhotoView.onGetDataFailed("下载成功");
    }

    @Override
    public void onLoadPhotoSuccess(UnsplashPhoto photo) {
        mUnsplashPhotoView.onGetPhotoSuccess(photo);
    }

    @Override
    public void onLoadDataError(String error) {
        mUnsplashPhotoView.onGetDataFailed(error);
    }

    @Override
    public void onLoadSizeSuccess(int size,int pos) {
        mUnsplashPhotoView.onGetSizeSuccess(size, pos);
    }
}
