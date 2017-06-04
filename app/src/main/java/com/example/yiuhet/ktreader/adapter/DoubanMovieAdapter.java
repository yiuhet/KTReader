package com.example.yiuhet.ktreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.DoubanMovieDetail;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiuhet on 2017/5/31.
 */

public class DoubanMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private static final int TYPE_InTheaters = 0;
    private static final int TYPE_Top250 = 1;
    private static final int TYPE_THREE = 2;
    private DoubanMovieDetail mDoubanMovieDetail;
    private DoubanMovieDetail mDoubanMovieTopDetail;

    private OnItemClickListener mItemClickListener;

    public DoubanMovieAdapter(Context context) {
        mContext = context;
    }

    public void setInTheatersData(DoubanMovieDetail data) {
        mDoubanMovieDetail = data;
        notifyItemChanged(TYPE_InTheaters);
    }

    public void setTopData(DoubanMovieDetail data) {
        mDoubanMovieTopDetail = data;
        notifyItemChanged(TYPE_Top250);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_InTheaters:
                return new InTheatersViewHolder(
                        LayoutInflater.from(mContext).inflate(
                                R.layout.douban_movie_intheaters,parent,false)
                );
            case TYPE_Top250:
                return new Top250ViewHolder(
                        LayoutInflater.from(mContext).inflate(
                            R.layout.douban_movie_intheaters,parent,false)
                );
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == DoubanMovieAdapter.TYPE_InTheaters) {
            return DoubanMovieAdapter.TYPE_InTheaters;
        }else if (position == DoubanMovieAdapter.TYPE_Top250) {
            return DoubanMovieAdapter.TYPE_Top250;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case TYPE_InTheaters:
                ((InTheatersViewHolder) holder).bind(mDoubanMovieDetail);
                break;
            case TYPE_Top250:
                ((Top250ViewHolder) holder).bind(mDoubanMovieTopDetail);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class InTheatersViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout movieScrollView;
        public TextView hint;
        public TextView more;
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

        public InTheatersViewHolder(View itemView) {
            super(itemView);
            hint = (TextView) itemView.findViewById(R.id.hint);
            more = (TextView) itemView.findViewById(R.id.tv_more_intheaters);
            hint.setText("正在热映");
            movieScrollView = (LinearLayout) itemView.findViewById(R.id.sv_add);
        }

        protected void bind(DoubanMovieDetail doubanMovieDetail) {
            int size = mDoubanMovieDetail == null ? 0 : mDoubanMovieDetail.subjects.size();
            for (int i = 0; i < size; i++) {
                View view = View.inflate(mContext, R.layout.item_douban_movie_intheater, null);
                ButterKnife.bind(this, view);
                try {
                    Glide.with(mContext)
                            .load(doubanMovieDetail.subjects.get(i).images.large)
                            .into(ivMovie);
                    tvMovieName.setText(doubanMovieDetail.subjects.get(i).title);
                    tvDirectors.setText(String.format("导演：%s", doubanMovieDetail.subjects.get(i).directors.get(0).name));
                    tvCasts.setText(String.format("主演：%s", doubanMovieDetail.subjects.get(i).casts.get(0).name));
                    tvRating.setText(String.format("评分：%s", doubanMovieDetail.subjects.get(i).rating.average));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final int finalI = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener != null) {
                            mItemClickListener.onItemClick(mDoubanMovieDetail.subjects.get(finalI).id, TYPE_InTheaters);
                        }
                    }
                });
                movieScrollView.addView(view);
            }
        }
    }

    class Top250ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout mAddView;
        public TextView hint;
        public TextView more;

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
        public Top250ViewHolder(View itemView) {
            super(itemView);
            hint = (TextView) itemView.findViewById(R.id.hint);
            more = (TextView) itemView.findViewById(R.id.tv_more_intheaters);
            hint.setText("豆瓣Top250");
            mAddView = (LinearLayout) itemView.findViewById(R.id.sv_add);
        }

        protected void bind(DoubanMovieDetail doubanMovieDetail) {
            int size = mDoubanMovieTopDetail == null ? 0 : mDoubanMovieTopDetail.subjects.size();
           for (int i = 0; i < size; i++) {
                View view = View.inflate(mContext, R.layout.item_douban_movie_intheater, null);
                ButterKnife.bind(this, view);
                try {
                    Glide.with(mContext)
                            .load(doubanMovieDetail.subjects.get(i).images.large)
                            .into(ivMovie);
                    tvMovieName.setText(doubanMovieDetail.subjects.get(i).title);
                    tvDirectors.setText(String.format("导演：%s", doubanMovieDetail.subjects.get(i).directors.get(0).name));
                    tvCasts.setText(String.format("主演：%s", doubanMovieDetail.subjects.get(i).casts.get(0).name));
                    tvRating.setText(String.format("评分：%s", doubanMovieDetail.subjects.get(i).rating.average));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                final int finalI = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener != null) {
                            mItemClickListener.onItemClick(mDoubanMovieTopDetail.subjects.get(finalI).id, TYPE_Top250);
                        }
                    }
                });
               mAddView.addView(view);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String id, int Type);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
