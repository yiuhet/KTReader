package com.example.yiuhet.ktreader.ui.fragment.douban;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.yiuhet.ktreader.BaseFragment;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.adapter.DoubanMovieAdapter;
import com.example.yiuhet.ktreader.presenter.imp1.DoubanMoviePresenterImp1;
import com.example.yiuhet.ktreader.view.DoubanMovieView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoubanMovieFragment extends BaseFragment<DoubanMovieView, DoubanMoviePresenterImp1> implements DoubanMovieView {

    @BindView(R.id.recycleview_douban)
    RecyclerView mRecycleviewDouban;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;

    Unbinder unbinder;
    private DoubanMovieAdapter mDoubanMovieAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_douban_movie;
    }

    @Override
    protected DoubanMoviePresenterImp1 createPresenter() {
        return new DoubanMoviePresenterImp1(this);
    }

    @Override
    public void onStartGetData() {
        mPrograss.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetInTheatersSuccess() {
        Log.d("testTime", "数据下载完成 返回到V层接口 时间： " + String.valueOf(System.currentTimeMillis()));
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        mDoubanMovieAdapter.setInTheatersData(mPresenter.getInTheatersData());
    }

    @Override
    public void onGetComingSoonSuccess() {
        mPrograss.setVisibility(View.GONE);
    }

    @Override
    public void onGgetTop250Success() {
        mPrograss.setVisibility(View.GONE);
    }

    @Override
    public void onGetWeeklySuccess() {
        mPrograss.setVisibility(View.GONE);
    }

    @Override
    public void onGetNewMoviesSuccess() {
        mPrograss.setVisibility(View.GONE);
    }

    @Override
    public void onGetDataFailed(String error) {
        mPrograss.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        mPresenter.getInTheaters();
        mRecycleviewDouban.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleviewDouban.setHasFixedSize(true);
        mRecycleviewDouban.setItemAnimator(new DefaultItemAnimator());
        mDoubanMovieAdapter = new DoubanMovieAdapter(getContext());
        mDoubanMovieAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecycleviewDouban.setAdapter(mDoubanMovieAdapter);


    }
    private DoubanMovieAdapter.OnItemClickListener mOnItemClickListener = new DoubanMovieAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(String id, int type) {
            //TODO : 跳转到详情页
            toast(id);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
