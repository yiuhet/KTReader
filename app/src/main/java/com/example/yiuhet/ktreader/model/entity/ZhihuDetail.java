package com.example.yiuhet.ktreader.model.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yiuhet on 2017/5/29.
 */

public class ZhihuDetail {
    @Override
    public String toString() {
        return "ZhihuDetail{" +
                "body='" + body + '\'' +
                ", imageSource='" + imageSource + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", id=" + id +
                ", css=" + css +
                '}';
    }

    @SerializedName("body")
    public String body;
    @SerializedName("image_source")
    public String imageSource;
    @SerializedName("title")
    public String title;
    @SerializedName("image")
    public String image;
    @SerializedName("share_url")
    public String shareUrl;
    @SerializedName("id")
    public int id;
    @SerializedName("css")
    public List<String> css;

    public static ZhihuDetail objectFromData(String str) {

        return new Gson().fromJson(str, ZhihuDetail.class);
    }
}
