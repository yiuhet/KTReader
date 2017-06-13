package com.example.yiuhet.ktreader.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.yiuhet.ktreader.MVPBaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.adapter.HistoryAdapter;
import com.example.yiuhet.ktreader.model.entity.HistoryCollect;
import com.example.yiuhet.ktreader.presenter.imp1.HistoryCollectPresenterImp1;
import com.example.yiuhet.ktreader.utils.MyDataBaseHelper;
import com.example.yiuhet.ktreader.view.HistoryCollectView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.yiuhet.ktreader.app.MyApplication.getContext;

public class HistoryActivity extends MVPBaseActivity<HistoryCollectView, HistoryCollectPresenterImp1> implements HistoryCollectView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycle_history)
    RecyclerView mRecycleHistory;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;

    List<HistoryCollect> mHistoryCollects = new ArrayList<>();
    HistoryAdapter mHistoryAdapter;

    @Override
    protected HistoryCollectPresenterImp1 createPresenter() {
        return new HistoryCollectPresenterImp1(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initToolbar();
        mPresenter.getData(MyDataBaseHelper.HISTORY);
        mRecycleHistory.setLayoutManager(new LinearLayoutManager(this));
        mRecycleHistory.setHasFixedSize(true);
        mRecycleHistory.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecycleHistory.setItemAnimator(new DefaultItemAnimator());
        mHistoryAdapter = new HistoryAdapter(this, mHistoryCollects);
        mHistoryAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecycleHistory.setAdapter(mHistoryAdapter);
    }

    HistoryAdapter.OnItemClickListener mOnItemClickListener = new HistoryAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(String id, String title) {
            Intent intent = new Intent(getContext(), ZhihuDetailActivity.class);
            intent.putExtra("ZHIHUID",id);
            intent.putExtra("ZHIHUTITLE",title);
            startActivity(intent);
        }
    };

    private void initToolbar() {
        mToolbar.setTitle("足迹");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_history;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clear) {
            showClearDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showClearDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("是否确定清空历史纪录")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.clearHistory();
                        toast("记录已清空");
                        mHistoryCollects.clear();
                        mHistoryAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    @Override
    public void onStartGetData() {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetHistorySuccess(List<HistoryCollect> historyCollects) {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        mHistoryCollects = historyCollects;
        if (mHistoryAdapter != null) {
            mHistoryAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetCollectSuccess(List<HistoryCollect> historyCollects) {

    }

    @Override
    public void onGetUnsplashSuccess(List<HistoryCollect> historyCollects) {

    }


    @Override
    public void onGetDataFailed(String error) {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        toast(error);
    }
}
