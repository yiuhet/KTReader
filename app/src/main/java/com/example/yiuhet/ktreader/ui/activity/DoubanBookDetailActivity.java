package com.example.yiuhet.ktreader.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiuhet.ktreader.MVPBaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.DoubanBookDetail;
import com.example.yiuhet.ktreader.presenter.imp1.DoubanBookDetailPresenterImp1;
import com.example.yiuhet.ktreader.view.DoubanBookDetailView;
import com.example.yiuhet.ktreader.widget.MoreTextView;
import com.example.yiuhet.ktreader.widget.MyTextView;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiuhet on 2017/6/1.
 */

public class DoubanBookDetailActivity extends MVPBaseActivity<DoubanBookDetailView, DoubanBookDetailPresenterImp1> implements DoubanBookDetailView {

    @BindView(R.id.iv_title)
    ImageView mIvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;
    @BindView(R.id.book_name)
    TextView mBookName;
    @BindView(R.id.book_subtitle)
    TextView mBookSubtitle;
    @BindView(R.id.book_author)
    TextView mBookAuthor;
    @BindView(R.id.book_publisher)
    TextView mBookPublisher;
    @BindView(R.id.book_pubdate)
    TextView mBookPubdate;
    @BindView(R.id.book_rating)
    TextView mBookRating;
    @BindView(R.id.book_numRaters)
    TextView mBookNumRaters;
    @BindView(R.id.ratingbar)
    RatingBar mRatingbar;
    @BindView(R.id.linear_left)
    LinearLayout linearLeft;
    @BindView(R.id.description_view)
    TextView mBookSummary;
    @BindView(R.id.expand_view)
    ImageView mSummaryExpandView;
    @BindView(R.id.expandable_layout)
    LinearLayout mSummaryExpandableLayout;

    MoreTextView mAuthorSummary;
    @BindView(R.id.content_linear)
    LinearLayout mContentLinear;
    @BindView(R.id.MoreTextView)
    MoreTextView mMoreTextView;
    @BindView(R.id.hint)
    TextView mSummaryHint;
    @BindView(R.id.summary_hint1)
    TextView mSummaryHint1;
    private int maxDescripLine = 3; //TextView默认最大展示行数
    private String mBookId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    private void initView() {
        mBookId = getIntent().getStringExtra("DOUBANBOOKID");
        mPresenter.getDetail(mBookId);

        mBookSummary.setMaxHeight(mBookSummary.getLineHeight() * maxDescripLine);
        //方法1
        mSummaryExpandableLayout.setOnClickListener(new View.OnClickListener() {
            boolean isExpand;//是否已展开的状态

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                mBookSummary.clearAnimation();//消除动画效果
                final int deltaValue;//默认高度，即前边由maxLine确定的高度
                final int startValue = mBookSummary.getHeight();//起始高度
                int durationMillis = 200;//动画持续时间
                if (isExpand) {
                    /**
                     * 折叠动画
                     * 从实际高度缩回起始高度
                     */
                    deltaValue = mBookSummary.getLineHeight() * mBookSummary.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    mSummaryExpandView.startAnimation(animation);
                } else {
                    /**
                     * 展开动画
                     * 从起始高度增长至实际高度
                     */
                    deltaValue = mBookSummary.getLineHeight() * maxDescripLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    mSummaryExpandView.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                        mBookSummary.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }
                };
                animation.setDuration(durationMillis);
                mBookSummary.startAnimation(animation);
            }
        });

        mAuthorSummary = new MoreTextView(this, null);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
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

    @Override
    public void onStartGetData() {
        mPrograss.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetSearchSuccess(DoubanBookDetail doubanBookDetail) {
        mPrograss.setVisibility(View.GONE);
        bindView(doubanBookDetail);
    }

    private void bindView(DoubanBookDetail doubanBookDetail) {
        mToolbarLayout.setTitle(String.format("售价：%s",doubanBookDetail.price));
        Glide.with(this).load(doubanBookDetail.images.large).into(mIvTitle);
        mBookName.setText(doubanBookDetail.title);
        mBookSubtitle.setText(doubanBookDetail.subtitle);
        mBookAuthor.setText(String.format("作者：%s", doubanBookDetail.author));
        mBookPublisher.setText(String.format("出版社：%s", doubanBookDetail.publisher));
        mBookPubdate.setText(String.format("出版时间：%s", doubanBookDetail.pubdate));
        mBookRating.setText(doubanBookDetail.rating.average);
        mBookNumRaters.setText(String.format("%s人", doubanBookDetail.rating.numRaters));
        /*
         *  在OnCreate方法中定义设置的textView不会马上渲染并显示
         *  所以textview的getLineCount()获取到的值一般都为零
         *  因此使用post会在其绘制完成后来对ImageView进行显示控制
         *  而此处是在返回数据后设置。
         */
        mBookSummary.setText(doubanBookDetail.summary);
        mSummaryHint.setText("图书简介");
        mSummaryExpandView.setVisibility(mBookSummary.getLineCount()
                > maxDescripLine ? View.VISIBLE : View.GONE);
        /*
         *方法2 通过自定义View组合封装
         * 不使用xml来定义layout，直接定义一个继承LinearLayout的MoreTextView类
         * 这个类里边添加TextView和ImageView。
         */
        mSummaryHint1.setText("作者简介");
        mMoreTextView.setText(doubanBookDetail.authorIntro);
        /*
         *方法3 通过自定义View组合封装
         * 使用xml来定义layout
         */
        MyTextView myTextView = new MyTextView(DoubanBookDetailActivity.this);
        myTextView.setTextTags("标签", doubanBookDetail.tags);
        mContentLinear.addView(myTextView);

        mRatingbar.setRating(Float.parseFloat(doubanBookDetail.rating.average) / 2f);
    }

    @Override
    public void onGetDataFailed(String error) {
        mPrograss.setVisibility(View.GONE);
        toast(error);
    }

    @Override
    protected DoubanBookDetailPresenterImp1 createPresenter() {
        return new DoubanBookDetailPresenterImp1(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_douban_book_detail;
    }
}
