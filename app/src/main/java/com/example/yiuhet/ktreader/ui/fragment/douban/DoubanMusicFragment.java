package com.example.yiuhet.ktreader.ui.fragment.douban;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.yiuhet.ktreader.BaseFragment;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.adapter.DoubanMusicAdapter;
import com.example.yiuhet.ktreader.adapter.LabelAdapter;
import com.example.yiuhet.ktreader.model.entity.DoubanMusic;
import com.example.yiuhet.ktreader.presenter.imp1.DoubanMusicPresenterImp1;
import com.example.yiuhet.ktreader.view.DoubanMusicView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoubanMusicFragment extends BaseFragment<DoubanMusicView, DoubanMusicPresenterImp1> implements DoubanMusicView {


    @BindView(R.id.label_recycle)
    RecyclerView mLabelRecycle;
    @BindView(R.id.content_linear)
    LinearLayout mContentLinear;
    Unbinder unbinder;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;
    @BindView(R.id.content_recycle)
    RecyclerView mContentRecycle;
    private LabelAdapter labelAdapter;
    private DoubanMusicAdapter doubanMusicAdapter;
    private final String[] mLabelList = new String[]{
            "流行", "摇滚", "民谣", "电子",
            "舞曲", "说唱", "爵士", "乡村"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_douban_music;
    }

    @Override
    protected DoubanMusicPresenterImp1 createPresenter() {
        return new DoubanMusicPresenterImp1(this);
    }

    private void init() {
        //设置可滑动的标签视图
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLabelRecycle.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mLabelRecycle.setHasFixedSize(true);
        mLabelRecycle.setItemAnimator(new DefaultItemAnimator());
        labelAdapter = new LabelAdapter(mLabelList);
        labelAdapter.setOnItemClickListener(mOnItemClickListener);
        mLabelRecycle.setAdapter(labelAdapter);

        mContentRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        mContentRecycle.setHasFixedSize(true);
        mContentRecycle.setItemAnimator(new DefaultItemAnimator());
        doubanMusicAdapter = new DoubanMusicAdapter(getContext());
        labelAdapter.setOnItemClickListener(mOnItemClickListener);
        mContentRecycle.setAdapter(doubanMusicAdapter);
    }

    private LabelAdapter.OnItemClickListener mOnItemClickListener = new LabelAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            mPresenter.getSearch(mLabelList[position]);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStartGetData() {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetSearchSuccess(DoubanMusic doubanMusic) {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        doubanMusicAdapter.addData(doubanMusic);
    }

    @Override
    public void onGetDataFailed(String error) {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        toast(error);
    }
}
