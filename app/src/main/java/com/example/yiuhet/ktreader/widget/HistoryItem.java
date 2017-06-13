package com.example.yiuhet.ktreader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.HistoryCollect;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiuhet on 2017/6/12.
 */

public class HistoryItem extends RelativeLayout {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.time)
    TextView mTime;
    private Context mContext;

    public HistoryItem(Context context) {
        this(context, null);
    }

    public HistoryItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_history_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(HistoryCollect historyCollect) {
        try {
            mTitle.setText(historyCollect.getTitle());
            mTime.setText(historyCollect.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
