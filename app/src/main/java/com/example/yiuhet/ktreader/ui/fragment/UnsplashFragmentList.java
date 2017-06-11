package com.example.yiuhet.ktreader.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.yiuhet.ktreader.BaseFragment;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.adapter.UnsplashListAdapter;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotoSearch;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotosList;
import com.example.yiuhet.ktreader.presenter.imp1.UnsplashPhotoListPresenterImp1;
import com.example.yiuhet.ktreader.ui.activity.UnsplashPhotoActivity;
import com.example.yiuhet.ktreader.utils.CircularAnimUtil;
import com.example.yiuhet.ktreader.view.UnsplashPhotoListView;
import com.example.yiuhet.ktreader.widget.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yiuhet on 2017/6/6.
 */

public class UnsplashFragmentList extends BaseFragment<UnsplashPhotoListView, UnsplashPhotoListPresenterImp1> implements UnsplashPhotoListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.content_recycle)
    RecyclerView contentRecycle;
    Unbinder unbinder;
    @BindView(R.id.content_swipe)
    SwipeRefreshLayout mContentSwipe;
    @BindView(R.id.sv_photo)
    SearchView mSvPhoto;
    @BindView(R.id.spinner)
    Spinner mSpinner;

    private UnsplashListAdapter unsplashListAdapter;
    private int STATE = 0;//当前的状态
    //拥有的状态 ：latest", "oldest", "popular"，搜索状态。
    private final int STATE_LATEST = 0;
    private final int STATE_OLDEST = 1;
    private final int STATE_POPULAR = 2;
    private final int STATE_SEARCH = 3;
    //记录搜索状态下的搜索项
    private String Query;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        initRecycleView();
        //监听 SwipeRefreshLayout 事件 上拉就调用ReLoad方法。
        mContentSwipe.setOnRefreshListener(this);
        //监听 SearchView 事件
        mSvPhoto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Query = query;
                if (query != null){
                    STATE = STATE_SEARCH;
                } else {
                    STATE = STATE_LATEST;
                }
                ReLoad(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //初始化加载数据 Spinner 开始选择latest
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                STATE = position;
                ReLoad("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initRecycleView() {
        contentRecycle.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        contentRecycle.setHasFixedSize(true);
        contentRecycle.setItemAnimator(new DefaultItemAnimator());
        unsplashListAdapter = new UnsplashListAdapter(getContext());
        unsplashListAdapter.setOnItemClickListener(mOnItemClickListener);
        SpacesItemDecoration decoration = new SpacesItemDecoration(12);
        contentRecycle.addItemDecoration(decoration);
        contentRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isSlideToBottom(recyclerView)) {
                    if (STATE == STATE_SEARCH) {
                        mPresenter.getSearchPhotoList(Query);
                    } else {
                        mPresenter.getPhotoList(STATE);
                    }
                }
            }
        });
        contentRecycle.setAdapter(unsplashListAdapter);
    }

    //判断RecycleView是否到底部
    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange()){
            return true;
        }
        return false;
    }

    //RecycleView的item监听事件 点击item，跳转到相应的activity
    private UnsplashListAdapter.OnItemClickListener mOnItemClickListener = new UnsplashListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(String id,View view) {
            Intent intent = new Intent(getActivity(), UnsplashPhotoActivity.class);
            intent.putExtra("PHOTOID", id);
            CircularAnimUtil.startActivity(getActivity(), intent, view,
                    R.color.colorPrimary);
            toast("you touch me,my id:" + id);
        }
    };

    //重新加载数据 ，首先清空数据，然后根据当前状态获取数据
    public void ReLoad(String query) {
        unsplashListAdapter.clearData();
        if (STATE == STATE_SEARCH) {
            mPresenter.getSearchPhotoList(query);
        } else {
            mPresenter.getPhotoList(STATE);
        }
    }

    @Override
    public void onStartGetData() {
        if (mContentSwipe != null) {
            mContentSwipe.setRefreshing(true);
        }
    }

    @Override
    public void onGetPhotoSuccess(List<UnsplashPhotosList> photosList) {
        if (mContentSwipe != null && mContentSwipe.isRefreshing()) {
            mContentSwipe.setRefreshing(false);
        }
        unsplashListAdapter.addData(photosList);
    }

    @Override
    public void onGetSearchPhotoSuccess(UnsplashPhotoSearch photosList) {
        if (mContentSwipe != null && mContentSwipe.isRefreshing()) {
            mContentSwipe.setRefreshing(false);
        }
        unsplashListAdapter.addData(photosList.results);
    }

    @Override
    public void onGetDataFailed(String error) {
        if (mContentSwipe != null && mContentSwipe.isRefreshing()) {
            mContentSwipe.setRefreshing(false);
        }
        toast(error);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_unsplash;
    }

    @Override
    protected UnsplashPhotoListPresenterImp1 createPresenter() {
        return new UnsplashPhotoListPresenterImp1(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        ReLoad("");
    }
}
