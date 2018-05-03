package com.example.yiuhet.ktreader.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yiuhet.ktreader.MVPBaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.presenter.imp1.SplashPresenterImp1;
import com.example.yiuhet.ktreader.view.SplashView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends MVPBaseActivity<SplashView, SplashPresenterImp1> implements SplashView {

    @BindView(R.id.iv_show_pic)
    ImageView mIvShowPic;
    @BindView(R.id.tv_show_saying)
    TextView mTvShowSaying;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected SplashPresenterImp1 createPresenter() {
        return new SplashPresenterImp1(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //保持全屏窗口
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mPresenter.loadData();
        startAnim();
    }

    private void startAnim() {
        //传入一个ImageView对象,围绕X,Y进行2D缩放,由原始的大小方法到原来的1.15倍
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIvShowPic, "scaleX", 1f, 1.15f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIvShowPic, "scaleY", 1f, 1.15f);
        //多个动画的协同工作
        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000).play(animatorX).with(animatorY);
        set.start();
        //对动画的监听,动画结束后立马跳转到主页面上
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(MainActivity.class);
            }
        });
    }

    @Override
    public void onSuccess(String string) {
        mTvShowSaying.setText(string);
    }

    @Override
    public void onError() {
        mTvShowSaying.setText(getString(R.string.default_saying));
    }
}
