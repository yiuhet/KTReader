package com.example.yiuhet.ktreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.yiuhet.ktreader.model.entity.HistoryCollect;
import com.example.yiuhet.ktreader.widget.HistoryItem;
import com.example.yiuhet.ktreader.widget.ZhihuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiuhet on 2017/6/12.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{


    private OnItemClickListener mItemClickListener;
    private List<HistoryCollect> mHistoryCollects = new ArrayList<>();
    private Context mContext;

    public HistoryAdapter(Context context, List<HistoryCollect> historyCollects) {
        mHistoryCollects = historyCollects;
        mContext = context;
    }


    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HistoryItem historyItem = new HistoryItem(mContext);
        return new HistoryViewHolder(historyItem);
    }

    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, int position) {
        final HistoryCollect historyCollect = mHistoryCollects.get(position);
        holder.historyItem.bindView(historyCollect);
        holder.historyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(historyCollect.getUrl(),historyCollect.getTitle());
                }
            }
        });
    }


    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        public HistoryItem historyItem;

        public HistoryViewHolder(HistoryItem itemView) {
            super(itemView);
            historyItem = itemView;
        }
    }

    @Override
    public int getItemCount() {
        return mHistoryCollects.size();
    }


    public interface OnItemClickListener {
        void onItemClick(String id, String title);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
