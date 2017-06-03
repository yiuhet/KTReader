package com.example.yiuhet.ktreader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.model.entity.DoubanBook;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiuhet on 2017/6/1.
 */

public class DoubanBookItem extends RelativeLayout {
    @BindView(R.id.iv_douban_book)
    ImageView mIvDoubanBook;
    @BindView(R.id.book_title)
    TextView mBookTitle;
    @BindView(R.id.book_author)
    TextView mBookAuthor;
    @BindView(R.id.book_time)
    TextView mBookTime;
    @BindView(R.id.book_sorce)
    TextView mBookSorce;
    private Context mContext;

    public DoubanBookItem(Context context) {
        this(context, null);
    }

    public DoubanBookItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_douban_book_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(DoubanBook.BooksEntity doubanBook) {
        try {
            Glide.with(mContext)
                    .load(doubanBook.images.large)
                    .placeholder(R.drawable.loading) //占位图片
                    .error(R.drawable.error) //错误图片
                    .into(mIvDoubanBook);
            mBookTitle.setText(doubanBook.title);
            mBookAuthor.setText(String.format("作者:%s",doubanBook.author.get(0)));
            mBookTime.setText(String.format("出版时间:%s",doubanBook.pubdate));
            mBookSorce.setText(String.format("豆瓣评分:%s(%s人)",doubanBook.rating.average ,doubanBook.rating.numRaters));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
