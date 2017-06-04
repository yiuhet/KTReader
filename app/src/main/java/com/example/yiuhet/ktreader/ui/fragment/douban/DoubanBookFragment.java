package com.example.yiuhet.ktreader.ui.fragment.douban;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yiuhet.ktreader.BaseFragment;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.adapter.DoubanBookAdapter;
import com.example.yiuhet.ktreader.model.entity.DoubanBook;
import com.example.yiuhet.ktreader.presenter.imp1.DoubanBookPresenterImp1;
import com.example.yiuhet.ktreader.ui.activity.DoubanBookDetailActivity;
import com.example.yiuhet.ktreader.view.DoubanBookView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoubanBookFragment extends BaseFragment<DoubanBookView, DoubanBookPresenterImp1> implements DoubanBookView {

    Unbinder unbinder;
    @BindView(R.id.searchView)
    SearchView mSearchView;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.recycleview_douban)
    RecyclerView mRecycleviewDouban;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;

    private DoubanBookAdapter mDoubanBookAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setQueryHint("查找图书");
        mSearchView.setOnQueryTextListener(searchViewListener);
        mRecycleviewDouban.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleviewDouban.setHasFixedSize(true);
        mRecycleviewDouban.setItemAnimator(new DefaultItemAnimator());
        mDoubanBookAdapter = new DoubanBookAdapter(getContext());
        mDoubanBookAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecycleviewDouban.setAdapter(mDoubanBookAdapter);
    }

    private DoubanBookAdapter.OnItemClickListener mOnItemClickListener = new DoubanBookAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(String id) {
            Intent intent = new Intent(getContext(), DoubanBookDetailActivity.class);
            intent.putExtra("DOUBANBOOKID",String.valueOf(id));
            startActivity(intent);
        }
    };
    private SearchView.OnQueryTextListener searchViewListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            mPresenter.getSearch(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_douban_book;
    }

    @Override
    protected DoubanBookPresenterImp1 createPresenter() {
        return new DoubanBookPresenterImp1(this);
    }

    @Override
    public void onStartGetData() {
        mPrograss.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetSearchSuccess(DoubanBook doubanBook) {
        mPrograss.setVisibility(View.GONE);
        mTvCount.setText(String.format("找到%s个相关结果",doubanBook.total));
        mDoubanBookAdapter.addData(doubanBook);
    }

    @Override
    public void onGetDataFailed(String error) {
        mPrograss.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
