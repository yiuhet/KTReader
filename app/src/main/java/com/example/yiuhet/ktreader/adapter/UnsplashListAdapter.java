package com.example.yiuhet.ktreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotoSearch;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotosList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiuhet on 2017/6/6.
 */

public class UnsplashListAdapter extends RecyclerView.Adapter<UnsplashListAdapter.PhotoViewHolder>{

    private List<UnsplashPhotosList> mPhotoList;
    private Context mContext;
    private OnItemClickListener mItemClickListener;
    //用于每个item的布局
    private LayoutInflater mInflater;
    //瀑布流控制高度
    private List<Integer> mHeights;
    private int currentPos = 0;

    public UnsplashListAdapter(Context context) {
        mContext = context;
        mPhotoList = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        mHeights = new ArrayList<Integer>();
    }


    public void addData(List<UnsplashPhotosList> unsplashPhotosList) {
        currentPos += mPhotoList.size();
        for (UnsplashPhotosList photosList:unsplashPhotosList){
            mPhotoList.add(photosList);
        }
        for (int i = 0;i<unsplashPhotosList.size();i++){
            mHeights.add((int) (300+Math.random()*300));
        }
        //应该局部 刷新 ，有个bug TODO: 2017/6/11  bug
        //notifyItemRangeChanged(currentPos,unsplashPhotosList.size());
        notifyDataSetChanged();
    }

    //清空数据
    public void clearData() {
        currentPos = 0;
        mPhotoList.clear();
        notifyDataSetChanged();
    }


    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_photo, parent, false);
        PhotoViewHolder photoViewHolder = new PhotoViewHolder(view);
        return photoViewHolder;
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {

        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        Glide.with(mContext)
                .load(mPhotoList.get(position).urls.small)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(holder.mImage);
        holder.mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(mPhotoList.get(position).id,holder.itemView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPhotoList == null ? 0:mPhotoList.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_photo)
        ImageView mImage;
        @BindView(R.id.tv_title)
        TextView mTitle;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String id,View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }


}
