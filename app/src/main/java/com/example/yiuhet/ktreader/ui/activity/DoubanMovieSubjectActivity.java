package com.example.yiuhet.ktreader.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiuhet.ktreader.MVPBaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.DoubanMovieSubject;
import com.example.yiuhet.ktreader.presenter.imp1.DoubanMovieSubjectPresenterImp1;
import com.example.yiuhet.ktreader.view.DoubanMovieSubjectView;
import com.example.yiuhet.ktreader.widget.MyScrollView;
import com.example.yiuhet.ktreader.widget.MyTextView;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoubanMovieSubjectActivity extends MVPBaseActivity<DoubanMovieSubjectView, DoubanMovieSubjectPresenterImp1> implements DoubanMovieSubjectView {

    @BindView(R.id.iv_title)
    ImageView mIvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.content_linear)
    LinearLayout mContentLinear;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;
    @BindView(R.id.book_name)
    TextView mName;
    @BindView(R.id.book_subtitle)
    TextView mSubtitle;
    @BindView(R.id.book_author)
    TextView mAuthor;
    @BindView(R.id.book_publisher)
    TextView mPublisher;
    @BindView(R.id.book_pubdate)
    TextView mPubdate;
    @BindView(R.id.book_rating)
    TextView mRating;
    @BindView(R.id.ratingbar)
    RatingBar mRatingbar;
    @BindView(R.id.book_numRaters)
    TextView mNumRaters;

    private String mMovieId;

    @Override
    protected DoubanMovieSubjectPresenterImp1 createPresenter() {
        return new DoubanMovieSubjectPresenterImp1(this);
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
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initView() {
        mMovieId = getIntent().getStringExtra("DOUBANMOVIEID");
        mPresenter.getMovieSubject(mMovieId);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_douban_movie_subject;
    }

    @Override
    public void onStartGetData() {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetSearchSuccess(DoubanMovieSubject doubanMovieSubject) {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        bindView(doubanMovieSubject);
    }

    private void bindView(DoubanMovieSubject doubanMovieSubject) {
        mToolbarLayout.setTitle(doubanMovieSubject.title);
        Glide.with(this).load(doubanMovieSubject.images.large).into(mIvTitle);
        mName.setText(doubanMovieSubject.title);
        mSubtitle.setText(doubanMovieSubject.genres.toString());
        mAuthor.setText(String.format("导演：%s",doubanMovieSubject.directors.toString()));
        mPublisher.setText(String.format("主演：%s",doubanMovieSubject.casts.toString()));
        mPubdate.setText(String.format("上映年份：%s",doubanMovieSubject.year));

        mRating.setText(String.valueOf(doubanMovieSubject.rating.average));
        mRatingbar.setRating((float) (doubanMovieSubject.rating.average / 2));
        mNumRaters.setText(String.format("%s人评分", doubanMovieSubject.ratingsCount));
        //直接使用 自定义的控件 贼方便
        MyTextView myTextView = new MyTextView(DoubanMovieSubjectActivity.this);
        myTextView.setText("影片介绍", doubanMovieSubject.summary);
        mContentLinear.addView(myTextView);

        MyScrollView myScrollView = new MyScrollView(DoubanMovieSubjectActivity.this);
        myScrollView.bindData("演员阵容", doubanMovieSubject.casts);
        mContentLinear.addView(myScrollView);
    }

    @Override
    public void onGetDataFailed(String error) {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        toast(error);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }
}
