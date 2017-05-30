package com.example.yiuhet.ktreader.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiuhet.ktreader.MVPBaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.ZhihuDetail;
import com.example.yiuhet.ktreader.presenter.imp1.ZhihuDetailPresenterImp1;
import com.example.yiuhet.ktreader.utils.WebUtil;
import com.example.yiuhet.ktreader.view.ZhihuDetailView;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhihuDetailActivity extends MVPBaseActivity<ZhihuDetailView, ZhihuDetailPresenterImp1> implements ZhihuDetailView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;
    @BindView(R.id.wv_zhihu)
    WebView mWvZhihu;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.iv_title)
    ImageView mIvTitle;
    private String mZhihuId;

    @Override
    protected ZhihuDetailPresenterImp1 createPresenter() {
        return new ZhihuDetailPresenterImp1(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initView() {
        mZhihuId = getIntent().getStringExtra("ZHIHUID");
        mPresenter.getDetail(mZhihuId);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "已添加进收藏夹（待做功能）", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mWvZhihu.setVerticalScrollBarEnabled(true);
        mWvZhihu.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);

        WebSettings settings = mWvZhihu.getSettings();
        //设置应用缓存路径，这个路径必须是可以让app写入文件的。该方法应该只被调用一次，重复调用会被无视~
        settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true); //启用应用缓存。
        settings.setDatabaseEnabled(true); //启用数据库缓存。
        settings.setDomStorageEnabled(true); //开启DOM缓存
        //用来设置WebView的缓存模式(这里使用的是 只要缓存可用就加载缓存)
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setJavaScriptEnabled(true); //设置WebView可以运行JavaScript。
        settings.setBuiltInZoomControls(true);//显示或不显示缩放按钮（wap网页不支持）。
        //指定WebView的页面布局显示形式，调用该方法会引起页面重绘
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWvZhihu.setWebChromeClient(new WebChromeClient());

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    public void onStartGetData() {
        mPrograss.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetDetailSuccess(ZhihuDetail zhihuDetail) {
        mPrograss.setVisibility(View.GONE);
        mToolbarLayout.setTitle(zhihuDetail.title);
        //在较为特殊的情况下，知乎日报可能将某个主题日报的站外文章推送至知乎日报首页。
        if (zhihuDetail.body == null) {
            mWvZhihu.loadUrl(zhihuDetail.shareUrl);
        } else {
            Glide.with(this).load(zhihuDetail.image).into(mIvTitle);
            String data = WebUtil.buildHtmlWithCss(zhihuDetail.body, zhihuDetail.css);
            mWvZhihu.loadDataWithBaseURL(WebUtil.BASE_URL, data, WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.FAIL_URL);
        }
    }

    @Override
    public void onGetDetailFailed(String error) {
        mPrograss.setVisibility(View.GONE);
        toast(error);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        if (mWvZhihu != null) {
            //webview内存泄露
            ((ViewGroup) mWvZhihu.getParent()).removeView(mWvZhihu);
            mWvZhihu.destroy();
            mWvZhihu = null;
        }
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }
}
