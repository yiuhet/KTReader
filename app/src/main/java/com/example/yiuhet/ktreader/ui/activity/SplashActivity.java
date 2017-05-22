package com.example.yiuhet.ktreader.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiuhet.ktreader.BaseActivity;
import com.example.yiuhet.ktreader.MVPBaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.presenter.imp1.SplashPresenterImp1;
import com.example.yiuhet.ktreader.utils.ShowApiUtils;
import com.example.yiuhet.ktreader.utils.ThreadUtils;
import com.example.yiuhet.ktreader.view.SplashView;

public class SplashActivity extends MVPBaseActivity<SplashView,SplashPresenterImp1> implements SplashView{

    private ImageView mIvShowPic;
    private TextView mTvShowSaying;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        mIvShowPic = (ImageView) findViewById(R.id.iv_show_pic);
        mTvShowSaying = (TextView) findViewById(R.id.tx_show_saying);
        mPresenter.loadSaying();
        startAnim(MainActivity.class);
        //mTvShowSaying.setText(ShowApiUtils.getApiRequest(ShowApiUtils.BING_PIC));
//        String url = "http://www.bing.com/az/hprichbg/rb/SpermophilusArmatus_ZH-CN11634149121_1920x1080.jpg";
//        Glide.with(this).load(url).into(mIvShowPic);
    }

    private void startAnim(final Class act) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIvShowPic, "scaleX", 1f, 1.15f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIvShowPic, "scaleY", 1f, 1.15f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000).play(animatorX).with(animatorY);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(act);
            }
        });

    }


    @Override
    public void onGetSayingSuccess(String string) {
        mTvShowSaying.setText(string);
    }

    @Override
    public void onGetSayingFailed() {
        mTvShowSaying.setText(getString(R.string.default_saying));
    }


    @Override
    protected SplashPresenterImp1 createPresenter() {
        return new SplashPresenterImp1(this);
    }
}
