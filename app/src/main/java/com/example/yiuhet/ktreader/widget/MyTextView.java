package com.example.yiuhet.ktreader.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.DoubanBookDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiuhet on 2017/6/3.
 */

public class MyTextView extends LinearLayout {
    @BindView(R.id.hint)
    TextView hint;
    @BindView(R.id.description_view)
    TextView contentView;
    @BindView(R.id.expand_view)
    ImageView expandView;
    @BindView(R.id.expandable_layout)
    LinearLayout expandableLayout;

    private int maxLine = 3;

    private Context mContext;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.textview_expandable, this);
        ButterKnife.bind(this, this);
        expandableLayout.setOnClickListener(new View.OnClickListener() {
            boolean isExpand;
            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                contentView.clearAnimation();
                final int deltaValue;
                final int startValue = contentView.getHeight();
                int durationMillis = 350;
                if (isExpand) {
                    deltaValue = contentView.getLineHeight() * contentView.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                } else {
                    deltaValue = contentView.getLineHeight() * maxLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        contentView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }

                };
                animation.setDuration(durationMillis);
                contentView.startAnimation(animation);
            }
        });
    }

    public void setText(String title,List<DoubanBookDetail.TagsEntity> tagsEntityList) {
        hint.setText(title);
        for (int i = 0;i < tagsEntityList.size();i++) {
            contentView.setText(contentView.getText() + " " + String.format("%s(%s)",tagsEntityList.get(i).name,tagsEntityList.get(i).count));
        }

        expandView.post(new Runnable() {

            @Override
            public void run() {
                expandView.setVisibility(contentView.getLineCount() > maxLine ? View.VISIBLE : View.GONE);
            }
        });
        contentView.setHeight(contentView.getLineHeight() * maxLine);
    }
}
