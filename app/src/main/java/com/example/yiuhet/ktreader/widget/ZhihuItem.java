package com.example.yiuhet.ktreader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.ZhihuLatest;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiuhet on 2017/5/23.
 */

public class ZhihuItem extends RelativeLayout {

    private Context mContext;

    @BindView(R.id.zhihu_iv)
    ImageView mZhihuIv;
    @BindView(R.id.zhihu_title)
    TextView mZhihuTitle;

    public ZhihuItem(Context context) {
        this(context, null);
    }

    public ZhihuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_zhihu_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(ZhihuLatest.StoriesEntity zhihuLatest) {
        mZhihuTitle.setText(zhihuLatest.title);
        String url = zhihuLatest.images.get(0).toString();
        //Glide 获取图片
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.loading) //占位图片
                .error(R.drawable.error) //错误图片
                .into(mZhihuIv);
    }

}
