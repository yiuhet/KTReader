package com.example.yiuhet.ktreader.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.DoubanMovieSubject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiuhet on 2017/6/4.
 */

public class MyScrollView extends LinearLayout {

    private TextView mHint;
    private TextView mMoreHint;
    private LinearLayout mAddLayout;


    @BindView(R.id.iv_movie)
    ImageView ivMovie;
    @BindView(R.id.tv_movie_name)
    TextView tvMovieName;
    @BindView(R.id.tv_directors)
    TextView tvDirectors;
    @BindView(R.id.tv_casts)
    TextView tvCasts;
    @BindView(R.id.tv_Rating)
    TextView tvRating;

    private Context mContext;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.douban_movie_intheaters, this);
        mHint = (TextView) view.findViewById(R.id.hint);
        mMoreHint= (TextView) view.findViewById(R.id.tv_more_intheaters);
        mAddLayout = (LinearLayout) view.findViewById(R.id.sv_add);
    }

    public void bindData(String hint, List<DoubanMovieSubject.CastsEntity> content) {
        mHint.setText(hint);

        for (DoubanMovieSubject.CastsEntity cast:content){
            View view = View.inflate(mContext, R.layout.item_douban_movie_intheater, null);
            ButterKnife.bind(this, view);
            Glide.with(mContext)
                    .load(cast.avatars.large)
                    .into(ivMovie);
            tvMovieName.setText(cast.name);
            tvDirectors.setVisibility(View.GONE);
            tvCasts.setVisibility(View.GONE);
            tvRating.setVisibility(View.GONE);
            mAddLayout.addView(view);
        }

    }
}
