package com.example.yiuhet.ktreader.factory;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.yiuhet.ktreader.BaseFragment;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.ui.fragment.DoubanFragment;
import com.example.yiuhet.ktreader.ui.fragment.ZhiHuFragment;

/**
 * Created by yiuhet on 2017/5/31.
 */

public class FragmentFactory {
    private static FragmentFactory sFragmentFactory;

    private BaseFragment mZHihuFragment;
    private Fragment mDoubanFragment;
    private BaseFragment mQiwenFragment;
    private BaseFragment mTupianFragment;

    public static FragmentFactory getInstance() {
        if (sFragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                if (sFragmentFactory == null) {
                    sFragmentFactory = new FragmentFactory();
                }
            }
        }
        return sFragmentFactory;
    }

    public Fragment getFragment(int id) {
        switch (id) {
            case R.id.nav_zhihu:
                return getZHihuFragment();
            case R.id.nav_douban:
                return getDoubanFragment();
            case R.id.nav_qiwen:
                return getQiwenFragment();
            case R.id.nav_tupian:
                return getTupianFragment();
        }
        return null;
    }
    private BaseFragment getZHihuFragment() {
        if (mZHihuFragment == null) {
            mZHihuFragment = new ZhiHuFragment();
        }
        return mZHihuFragment;
    }
    private Fragment getDoubanFragment() {
        if (mDoubanFragment == null) {
            mDoubanFragment = new DoubanFragment();
            Log.d("ppapp","new DoubanFragment()");
        }
        Log.d("ppapp","getDoubanFragment");
        return mDoubanFragment;
    }
    private BaseFragment getQiwenFragment() {
        if (mQiwenFragment == null) {
            mQiwenFragment = new ZhiHuFragment();
        }
        return mQiwenFragment;
    }
    private BaseFragment getTupianFragment() {
        if (mTupianFragment == null) {
            mTupianFragment = new ZhiHuFragment();
        }
        return mTupianFragment;
    }
}
