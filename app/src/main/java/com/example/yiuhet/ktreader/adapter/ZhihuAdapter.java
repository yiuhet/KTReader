package com.example.yiuhet.ktreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.yiuhet.ktreader.model.entity.ZhihuLatest;
import com.example.yiuhet.ktreader.widget.ZhihuItem;

import java.util.List;

/**
 * Created by yiuhet on 2017/5/23.
 */

public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.ZhihuViewHolder> {

    private Context mContext;
    List<ZhihuLatest.StoriesEntity> mZhihuLatestList;
    private OnItemClickListener mItemClickListener;

    public ZhihuAdapter(Context context, List<ZhihuLatest.StoriesEntity> zhihuLatestList) {
        mContext =context;
        mZhihuLatestList = zhihuLatestList;
    }

    @Override
    public ZhihuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ZhihuItem zhihuItem = new ZhihuItem(mContext);
        return new ZhihuViewHolder(zhihuItem);
    }

    @Override
    public void onBindViewHolder(ZhihuViewHolder holder, int position) {
        final ZhihuLatest.StoriesEntity zhihuLatest = mZhihuLatestList.get(position);
        holder.zhihuItem.bindView(zhihuLatest);
        holder.zhihuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(zhihuLatest.id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mZhihuLatestList.size();
    }


    public class ZhihuViewHolder extends RecyclerView.ViewHolder {
        public ZhihuItem zhihuItem;

        public ZhihuViewHolder(ZhihuItem itemView) {
            super(itemView);
            zhihuItem = itemView;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
