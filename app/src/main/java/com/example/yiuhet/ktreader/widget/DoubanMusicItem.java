package com.example.yiuhet.ktreader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.DoubanMusic;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiuhet on 2017/6/4.
 */

public class DoubanMusicItem extends RelativeLayout {

    @BindView(R.id.iv_douban_book)
    ImageView mIvDoubanMusic;
    @BindView(R.id.book_title)
    TextView mMusicTitle;
    @BindView(R.id.book_author)
    TextView mMusicAuthor;
    @BindView(R.id.book_time)
    TextView mMusicTime;
    @BindView(R.id.book_sorce)
    TextView mMusicSorce;
    private Context mContext;


    public DoubanMusicItem(Context context) {
        this(context,null);
    }

    public DoubanMusicItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_douban_book_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(DoubanMusic.MusicsEntity musicsEntity) {
        try {
            Glide.with(mContext)
                    .load(musicsEntity.image)
                    .placeholder(R.drawable.loading) //占位图片
                    .error(R.drawable.error) //错误图片
                    .into(mIvDoubanMusic);
            mMusicTitle.setText(musicsEntity.title);
            mMusicAuthor.setText(String.format("表演者:%s",musicsEntity.author.get(0).name));
            mMusicTime.setText(String.format("发现时间:%s",musicsEntity.attrs.pubdate.get(0)));
            mMusicSorce.setText(String.format("豆瓣评分:%s(%s人)",musicsEntity.rating.average ,musicsEntity.rating.numRaters));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
