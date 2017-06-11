package com.example.yiuhet.ktreader.presenter.imp1;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotoSearch;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotosList;
import com.example.yiuhet.ktreader.model.imp1.UnsplashPhotoListModelImp1;
import com.example.yiuhet.ktreader.presenter.UnsplashPhotoListPresenter;
import com.example.yiuhet.ktreader.presenter.listener.OnUnsplashPhotoListListener;
import com.example.yiuhet.ktreader.view.UnsplashPhotoListView;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/6.
 */

public class UnsplashPhotoListPresenterImp1 extends BasePresenter<UnsplashPhotoListView> implements UnsplashPhotoListPresenter,OnUnsplashPhotoListListener{

    private UnsplashPhotoListView mUnsplashPhotoListView;
    private UnsplashPhotoListModelImp1 mUnsplashPhotoListModelImp1;

    public UnsplashPhotoListPresenterImp1(UnsplashPhotoListView unsplashPhotoListView) {
        mUnsplashPhotoListView = unsplashPhotoListView;
        mUnsplashPhotoListModelImp1 = new UnsplashPhotoListModelImp1();
    }
    @Override
    public void getPhotoList(int state) {
        mUnsplashPhotoListView.onStartGetData();
        mUnsplashPhotoListModelImp1.setState(state);
        mUnsplashPhotoListModelImp1.loadPhotoList(this);
    }

    @Override
    public void getSearchPhotoList(String query) {
        mUnsplashPhotoListView.onStartGetData();
        mUnsplashPhotoListModelImp1.loadSearchPhotoList(this,query);
    }


    @Override
    public void onLoadPhotoListSuccess(List<UnsplashPhotosList> photosList) {
        mUnsplashPhotoListView.onGetPhotoSuccess(photosList);
    }

    @Override
    public void onLoadSearchPhotoListSuccess(UnsplashPhotoSearch photosList) {
        mUnsplashPhotoListView.onGetSearchPhotoSuccess(photosList);
    }

    @Override
    public void onLoadDataError(String error) {
        mUnsplashPhotoListView.onGetDataFailed(error);
    }
}
