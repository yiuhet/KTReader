package com.example.yiuhet.ktreader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import com.example.yiuhet.ktreader.MVPBaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.adapter.CollectAdapter;
import com.example.yiuhet.ktreader.model.entity.HistoryCollect;
import com.example.yiuhet.ktreader.presenter.imp1.HistoryCollectPresenterImp1;
import com.example.yiuhet.ktreader.utils.CircularAnimUtil;
import com.example.yiuhet.ktreader.utils.MyDataBaseHelper;
import com.example.yiuhet.ktreader.view.HistoryCollectView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.yiuhet.ktreader.app.MyApplication.getContext;

public class CollectActivity extends MVPBaseActivity<HistoryCollectView, HistoryCollectPresenterImp1> implements HistoryCollectView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;
    @BindView(R.id.content_listview)
    ExpandableListView mContentListview;

    private ArrayList<String> gData = null;
    private ArrayList<ArrayList<HistoryCollect>> iData = null;
    private ArrayList<HistoryCollect> lData = null;

    CollectAdapter mCollectAdapter;

    @Override
    protected HistoryCollectPresenterImp1 createPresenter() {
        return new HistoryCollectPresenterImp1(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        gData = new ArrayList<String>();
        iData = new ArrayList<ArrayList<HistoryCollect>>();
        gData.add("知乎日报");
        gData.add("图片精选");
        //知乎日报
        lData = new ArrayList<HistoryCollect>();
        iData.add(lData);
        //图片精选
        lData = new ArrayList<HistoryCollect>();
        iData.add(lData);
    }

    private void initView() {
        initToolbar();
        mCollectAdapter = new CollectAdapter(gData,iData,this);
        mContentListview.setAdapter(mCollectAdapter);
        mContentListview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition ==0) {
                    Intent intent = new Intent(CollectActivity.this, ZhihuDetailActivity.class);
                    intent.putExtra("ZHIHUID",iData.get(groupPosition).get(childPosition).getUrl());
                    intent.putExtra("ZHIHUTITLE",iData.get(groupPosition).get(childPosition).getTitle());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(CollectActivity.this, UnsplashPhotoActivity.class);
                    intent.putExtra("PHOTOID", iData.get(groupPosition).get(childPosition).getTitle());
                    CircularAnimUtil.startActivity(CollectActivity.this, intent, v,
                            R.color.colorPrimary);
                }
                return true;
            }
        });
        mPresenter.getData(MyDataBaseHelper.COLLECT);
        mPresenter.getData(MyDataBaseHelper.UNSPLASH);

    }

    private void initToolbar() {
        mToolbar.setTitle("收藏");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_collect;
    }

    @Override
    public void onStartGetData() {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetHistorySuccess(List<HistoryCollect> historyCollects) {

    }

    @Override
    public void onGetCollectSuccess(List<HistoryCollect> historyCollects) {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        iData.get(0).clear();
        iData.get(0).addAll(historyCollects);
        mCollectAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetUnsplashSuccess(List<HistoryCollect> historyCollects) {
        Log.d("dfegeav",historyCollects.get(0).getUrl());
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        iData.get(1).clear();
        iData.get(1).addAll(historyCollects);
        mCollectAdapter.notifyDataSetChanged();
    }


    @Override
    public void onGetDataFailed(String error) {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
    }
}
