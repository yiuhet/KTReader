package com.example.yiuhet.ktreader.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yiuhet.ktreader.R;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/4.
 */

public class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.LabelViewHolder>{

    private String[] mLabelList;
    private OnItemClickListener mItemClickListener;

    public LabelAdapter (String[] data) {
        mLabelList = data;
    }
    @Override
    public LabelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LabelViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_label,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(LabelViewHolder holder, final int position) {
        holder.label.setText(mLabelList[position]);
        holder.label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position);
                }
            }
        });
    }

    public int getItemCount() {
        return mLabelList.length;
    }

    class LabelViewHolder extends RecyclerView.ViewHolder{
        Button label;
        public LabelViewHolder(View view) {
            super(view);
            label = (Button) view;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
