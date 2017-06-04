package com.example.yiuhet.ktreader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yiuhet.ktreader.ui.fragment.douban.DoubanBookFragment;
import com.example.yiuhet.ktreader.ui.fragment.douban.DoubanMovieFragment;
import com.example.yiuhet.ktreader.ui.fragment.douban.DoubanMusicFragment;

/**
 * Created by yiuhet on 2017/5/31.
 */

public class DoubanAdapter extends FragmentPagerAdapter {

    private final String[] mTitles = new String[]{
            "图书", "电影", "音乐"};

    public DoubanAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new DoubanMovieFragment();
        } else if (position == 2) {
            return new DoubanMusicFragment();
        }
        return new DoubanBookFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }


}
