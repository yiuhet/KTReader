package com.example.yiuhet.ktreader.presenter.imp1;

import android.util.Log;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.entity.DoubanMovieDetail;
import com.example.yiuhet.ktreader.model.imp1.DoubanMovieModelImp1;
import com.example.yiuhet.ktreader.presenter.DoubanMoviePresenter;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanMovieListener;
import com.example.yiuhet.ktreader.view.DoubanMovieView;

/**
 * Created by yiuhet on 2017/5/31.
 */

public class DoubanMoviePresenterImp1 extends BasePresenter<DoubanMovieView> implements DoubanMoviePresenter,OnDoubanMovieListener{

    private DoubanMovieView mDoubanMovieView;
    private DoubanMovieModelImp1 mDoubanMovieModelImp1;
    private DoubanMovieDetail mDoubanMovieDetail;
    private DoubanMovieDetail mDoubanMovieTopDetail;

    public DoubanMoviePresenterImp1(DoubanMovieView doubanMovieView) {
        mDoubanMovieView = doubanMovieView;
        mDoubanMovieModelImp1 = new DoubanMovieModelImp1();

    }

    public DoubanMovieDetail getInTheatersData() {
        if (mDoubanMovieDetail == null) {
            mDoubanMovieDetail = new DoubanMovieDetail();
        }
        return mDoubanMovieDetail;
    }

    public DoubanMovieDetail getTopData() {
        if (mDoubanMovieTopDetail == null) {
            mDoubanMovieTopDetail = new DoubanMovieDetail();
        }
        return mDoubanMovieTopDetail;
    }
    @Override
    public void getSearch() {

    }

    @Override
    public void getInTheaters() {
        mDoubanMovieModelImp1.loadInTheaters(this);
    }

    @Override
    public void getComingSoon() {

    }

    @Override
    public void getTop250() {
        mDoubanMovieModelImp1.loadTop250(this);
    }

    @Override
    public void getWeekly() {

    }

    @Override
    public void getNewMovies() {

    }

    @Override
    public void onLoadSearchSuccess() {

    }

    @Override
    public void onLoadInTheatersSuccess(DoubanMovieDetail doubanMovieDetail) {
        mDoubanMovieDetail = doubanMovieDetail;
        Log.d("testTime", "数据下载完成 返回到p层接口 时间： " + String.valueOf(System.currentTimeMillis()));
        mDoubanMovieView.onGetInTheatersSuccess();
    }

    @Override
    public void onLoadComingSoonSuccess() {

    }

    @Override
    public void onLoadTop250Success(DoubanMovieDetail doubanMovieDetail) {
        mDoubanMovieTopDetail = doubanMovieDetail;
        mDoubanMovieView.onGgetTop250Success();
    }


    @Override
    public void onLoadWeeklySuccess() {

    }

    @Override
    public void onLoadNewMoviesSuccess() {

    }

    @Override
    public void onLoadDataError(String error) {
        Log.d("ppadpp",error);
    }
}
