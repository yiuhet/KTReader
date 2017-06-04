package com.example.yiuhet.ktreader.presenter.imp1;

import com.example.yiuhet.ktreader.BasePresenter;
import com.example.yiuhet.ktreader.model.DoubanMovieSubjectModel;
import com.example.yiuhet.ktreader.model.entity.DoubanMovieSubject;
import com.example.yiuhet.ktreader.model.imp1.DoubanMovieSubjectModelImp1;
import com.example.yiuhet.ktreader.presenter.DoubanMovieSubjectPresenter;
import com.example.yiuhet.ktreader.presenter.listener.OnDoubanMovieSubjectListener;
import com.example.yiuhet.ktreader.view.DoubanMovieSubjectView;

/**
 * Created by yiuhet on 2017/6/3.
 */

public class DoubanMovieSubjectPresenterImp1 extends BasePresenter<DoubanMovieSubjectView> implements DoubanMovieSubjectPresenter,OnDoubanMovieSubjectListener{

    private DoubanMovieSubjectView mDoubanMovieSubjectView;
    private DoubanMovieSubjectModelImp1 mDoubanMovieSubjectModelImp1;

    private DoubanMovieSubject mDoubanMovieSubject;


    public DoubanMovieSubjectPresenterImp1(DoubanMovieSubjectView doubanMovieSubjectView) {
        mDoubanMovieSubjectView = doubanMovieSubjectView;
        mDoubanMovieSubjectModelImp1 = new DoubanMovieSubjectModelImp1();

    }

    public DoubanMovieSubject getMovieSubjectData() {
        if (mDoubanMovieSubject == null) {
            mDoubanMovieSubject = new DoubanMovieSubject();
        }
        return mDoubanMovieSubject;
    }
    @Override
    public void getMovieSubject(String id) {
        mDoubanMovieSubjectModelImp1.loadSearch(id,this);
    }

    @Override
    public void onLoadSearchSuccess(DoubanMovieSubject doubanMovieSubject) {
        mDoubanMovieSubjectView.onGetSearchSuccess(doubanMovieSubject);
    }

    @Override
    public void onLoadDataError(String error) {
        mDoubanMovieSubjectView.onGetDataFailed(error);
    }
}
