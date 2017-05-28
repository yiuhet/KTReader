package com.example.yiuhet.ktreader.ui.fragment;

import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.yiuhet.ktreader.BaseFragment;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.adapter.ZhihuAdapter;
import com.example.yiuhet.ktreader.app.Constant;
import com.example.yiuhet.ktreader.presenter.imp1.ZhihuPresenterImp1;
import com.example.yiuhet.ktreader.view.ZhihuView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yiuhet on 2017/5/22.
 */

public class ZhiHuFragment extends BaseFragment<ZhihuView, ZhihuPresenterImp1> implements ZhihuView {

    @BindView(R.id.recycle_zhihu)
    RecyclerView mRecycleZhihu;
    Unbinder unbinder;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;

    private ZhihuAdapter mZhihuAdapter;

    @Override
    public void onStartGetData() {
        mPrograss.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetZhihuLatestSuccess() {
        mPrograss.setVisibility(View.GONE);
        mZhihuAdapter.notifyDataSetChanged();
    }


    @Override
    public void onGetMoreSuccess() {
        mPrograss.setVisibility(View.GONE);
        mZhihuAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetDataFailed(String error) {
        mPrograss.setVisibility(View.GONE);
        toast(error);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected ZhihuPresenterImp1 createPresenter() {
        return new ZhihuPresenterImp1(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        mPresenter.getLatest();
        return rootView;
    }

    private void init() {
        mRecycleZhihu.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleZhihu.setHasFixedSize(true);
        mRecycleZhihu.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecycleZhihu.setItemAnimator(new DefaultItemAnimator());
        mRecycleZhihu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isSlideToBottom(recyclerView)) {
                    mPresenter.getMore();
                }
            }
        });
        mZhihuAdapter = new ZhihuAdapter(getContext(), mPresenter.getmZhihuLatestList());
        mZhihuAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecycleZhihu.setAdapter(mZhihuAdapter);
    }

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    private ZhihuAdapter.OnItemClickListener mOnItemClickListener = new ZhihuAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int id) {
            toast(Constant.ZHIHU_BASE_URL + String.valueOf(id));
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
