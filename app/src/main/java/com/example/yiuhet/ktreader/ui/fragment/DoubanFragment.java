package com.example.yiuhet.ktreader.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.adapter.DoubanAdapter;
import com.example.yiuhet.ktreader.ui.fragment.douban.DoubanBookFragment;
import com.example.yiuhet.ktreader.ui.fragment.douban.DoubanMovieFragment;
import com.example.yiuhet.ktreader.ui.fragment.douban.DoubanMusicFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class DoubanFragment extends Fragment {

    @BindView(R.id.tablay_douban)
    TabLayout mTablayDouban;
    @BindView(R.id.vp_douban)
    ViewPager mVpDouban;
    Unbinder unbinder;

    DoubanAdapter doubanAdapter ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 坑 fragment+tablayout+viewpager+多个fragment内容不显示
     * http://www.cnblogs.com/mengdd/p/5552721.html
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_douban, container, false);
        unbinder = ButterKnife.bind(this, view);
        doubanAdapter = new DoubanAdapter(getActivity().getSupportFragmentManager());
        mVpDouban.setAdapter(doubanAdapter);
        mTablayDouban.setupWithViewPager(mVpDouban);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("pppppa","onDestroyView");
        removeChildFragment();
        unbinder.unbind();
    }

    public void removeChildFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        List<Fragment> fragmentList = fragmentManager.getFragments();
        for (int i =0;i<fragmentList.size(); i++){
            if (fragmentList.get(i) instanceof DoubanBookFragment
                    ||fragmentList.get(i) instanceof DoubanMovieFragment
                    ||fragmentList.get(i) instanceof DoubanMusicFragment){
                fragmentManager.beginTransaction()
                        .remove(fragmentList.get(i))
                        .commit();
            }
        }
    }
}
