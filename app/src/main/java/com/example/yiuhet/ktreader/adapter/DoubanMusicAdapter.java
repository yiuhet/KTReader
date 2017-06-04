package com.example.yiuhet.ktreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.yiuhet.ktreader.model.entity.DoubanMusic;
import com.example.yiuhet.ktreader.widget.DoubanMusicItem;

/**
 * Created by yiuhet on 2017/6/4.
 */

public class DoubanMusicAdapter extends RecyclerView.Adapter <DoubanMusicAdapter.DoubanMusicViewHolder>  {

    private Context mContext;
    private DoubanMusic mDoubanMusic;
    private OnItemClickListener mItemClickListener;

    public DoubanMusicAdapter(Context context) {
        mContext = context;
    }

    public void addData(DoubanMusic doubanMusic) {
        mDoubanMusic = doubanMusic;
        notifyDataSetChanged();
    }
    @Override
    public DoubanMusicAdapter.DoubanMusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DoubanMusicItem doubanBookItem = new DoubanMusicItem(mContext);
        return new DoubanMusicViewHolder(doubanBookItem);
    }

    @Override
    public void onBindViewHolder(DoubanMusicViewHolder holder, int position) {
        final DoubanMusic.MusicsEntity doubanMusicList = mDoubanMusic.musics.get(position);
        holder.doubanMusicItem.bindView(doubanMusicList);
        holder.doubanMusicItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(doubanMusicList.id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDoubanMusic == null ? 0 : mDoubanMusic.count;
    }

    public class DoubanMusicViewHolder extends RecyclerView.ViewHolder {

        public DoubanMusicItem doubanMusicItem;
        public DoubanMusicViewHolder(DoubanMusicItem itemView) {
            super(itemView);
            doubanMusicItem = itemView;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String id);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
