package com.example.yiuhet.ktreader.model.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yiuhet on 2017/6/9.
 */

public class UnsplashUrl {

    /**
     * url : https://images.unsplash.com/photo-1496687421442-315106b0da41?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=fdd136461f8d60677f01e99f347b1fbc
     */

    @SerializedName("url")
    public String url;

    public static UnsplashUrl objectFromData(String str) {

        return new Gson().fromJson(str, UnsplashUrl.class);
    }
}
