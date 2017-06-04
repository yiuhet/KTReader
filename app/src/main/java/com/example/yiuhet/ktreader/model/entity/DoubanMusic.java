package com.example.yiuhet.ktreader.model.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/4.
 */

public class DoubanMusic {


    @SerializedName("count")
    public int count;
    @SerializedName("start")
    public int start;
    @SerializedName("total")
    public int total;
    @SerializedName("musics")
    public List<MusicsEntity> musics;

    public static DoubanMusic objectFromData(String str) {

        return new Gson().fromJson(str, DoubanMusic.class);
    }

    public static class MusicsEntity {
        /**
         * rating : {"max":10,"average":"9.2","numRaters":70198,"min":0}
         * author : [{"name":"周杰伦"}]
         * alt_title : Fantasy
         * image : https://img3.doubanio.com/spic/s3750422.jpg
         * tags : [{"count":17164,"name":"周杰伦"},{"count":6585,"name":"范特西"},{"count":4920,"name":"台湾"},{"count":4013,"name":"Jay"},{"count":3453,"name":"R&B"},{"count":2672,"name":"流行"},{"count":2366,"name":"华语"},{"count":1896,"name":"pop"}]
         * mobile_link : https://m.douban.com/music/subject/1403307/
         * attrs : {"publisher":["BMG"],"singer":["周杰伦"],"version":["专辑"],"pubdate":["2001-09-14"],"title":["范特西"],"media":["CD"],"tracks":["1. 爱在西元前\n2. 爸我回来了\n3. 简单爱\n4. 忍者\n5. 开不了口\n6. 上海一九四三\n7. 对不起\n8. 威廉古堡\n9. 双截棍\n10. 安静"],"discs":["1"]}
         * title : 范特西
         * alt : https://music.douban.com/subject/1403307/
         * id : 1403307
         */

        @SerializedName("rating")
        public RatingEntity rating;
        @SerializedName("alt_title")
        public String altTitle;
        @SerializedName("image")
        public String image;
        @SerializedName("mobile_link")
        public String mobileLink;
        @SerializedName("attrs")
        public AttrsEntity attrs;
        @SerializedName("title")
        public String title;
        @SerializedName("alt")
        public String alt;
        @SerializedName("id")
        public String id;
        @SerializedName("author")
        public List<AuthorEntity> author;
        @SerializedName("tags")
        public List<TagsEntity> tags;

        public static MusicsEntity objectFromData(String str) {

            return new Gson().fromJson(str, MusicsEntity.class);
        }

        public static class RatingEntity {
            /**
             * max : 10
             * average : 9.2
             * numRaters : 70198
             * min : 0
             */

            @SerializedName("max")
            public int max;
            @SerializedName("average")
            public String average;
            @SerializedName("numRaters")
            public int numRaters;
            @SerializedName("min")
            public int min;

            public static RatingEntity objectFromData(String str) {

                return new Gson().fromJson(str, RatingEntity.class);
            }
        }

        public static class AttrsEntity {
            @SerializedName("publisher")
            public List<String> publisher;
            @SerializedName("singer")
            public List<String> singer;
            @SerializedName("version")
            public List<String> version;
            @SerializedName("pubdate")
            public List<String> pubdate;
            @SerializedName("title")
            public List<String> title;
            @SerializedName("media")
            public List<String> media;
            @SerializedName("tracks")
            public List<String> tracks;
            @SerializedName("discs")
            public List<String> discs;

            public static AttrsEntity objectFromData(String str) {

                return new Gson().fromJson(str, AttrsEntity.class);
            }
        }

        public static class AuthorEntity {
            /**
             * name : 周杰伦
             */

            @SerializedName("name")
            public String name;

            public static AuthorEntity objectFromData(String str) {

                return new Gson().fromJson(str, AuthorEntity.class);
            }
        }

        public static class TagsEntity {
            /**
             * count : 17164
             * name : 周杰伦
             */

            @SerializedName("count")
            public int count;
            @SerializedName("name")
            public String name;

            public static TagsEntity objectFromData(String str) {

                return new Gson().fromJson(str, TagsEntity.class);
            }
        }
    }
}
