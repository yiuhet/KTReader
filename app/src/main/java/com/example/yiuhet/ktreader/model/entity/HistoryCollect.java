package com.example.yiuhet.ktreader.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yiuhet on 2017/6/12.
 */

public class HistoryCollect {

    private String title;
    private String url;
    private String time;

    public HistoryCollect(String title, String url, String time) {
        this.title = title;
        this.url = url;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getTime() {
        return time;
    }
}
