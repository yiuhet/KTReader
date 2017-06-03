package com.example.yiuhet.ktreader.model.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yiuhet on 2017/5/31.
 */

public class DoubanMovieDetail {

    @SerializedName("count")
    public int count;
    @SerializedName("start")
    public int start;
    @SerializedName("total")
    public int total;
    @SerializedName("title")
    public String title;
    @SerializedName("subjects")
    public List<SubjectsEntity> subjects;

    public static DoubanMovieDetail objectFromData(String str) {

        return new Gson().fromJson(str, DoubanMovieDetail.class);
    }

    public static class SubjectsEntity {

        @SerializedName("rating")
        public RatingEntity rating;
        @SerializedName("title")
        public String title;
        @SerializedName("collect_count")
        public int collectCount;
        @SerializedName("original_title")
        public String originalTitle;
        @SerializedName("subtype")
        public String subtype;
        @SerializedName("year")
        public String year;
        @SerializedName("images")
        public ImagesEntity images;
        @SerializedName("alt")
        public String alt;
        @SerializedName("id")
        public String id;
        @SerializedName("genres")
        public List<String> genres;
        @SerializedName("casts")
        public List<CastsEntity> casts;
        @SerializedName("directors")
        public List<DirectorsEntity> directors;

        public static SubjectsEntity objectFromData(String str) {

            return new Gson().fromJson(str, SubjectsEntity.class);
        }

        public static class RatingEntity {
            /**
             * max : 10
             * average : 7.4
             * stars : 40
             * min : 0
             */

            @SerializedName("max")
            public int max;
            @SerializedName("average")
            public double average;
            @SerializedName("stars")
            public String stars;
            @SerializedName("min")
            public int min;

            public static RatingEntity objectFromData(String str) {

                return new Gson().fromJson(str, RatingEntity.class);
            }
        }

        public static class ImagesEntity {
            /**
             * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2459723975.webp
             * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2459723975.webp
             * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2459723975.webp
             */

            @SerializedName("small")
            public String small;
            @SerializedName("large")
            public String large;
            @SerializedName("medium")
            public String medium;

            public static ImagesEntity objectFromData(String str) {

                return new Gson().fromJson(str, ImagesEntity.class);
            }
        }

        public static class CastsEntity {
            /**
             * alt : https://movie.douban.com/celebrity/1054456/
             * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/562.jpg","large":"https://img3.doubanio.com/img/celebrity/large/562.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/562.jpg"}
             * name : 约翰尼·德普
             * id : 1054456
             */

            @SerializedName("alt")
            public String alt;
            @SerializedName("avatars")
            public ImagesEntity avatars;
            @SerializedName("name")
            public String name;
            @SerializedName("id")
            public String id;

            public static CastsEntity objectFromData(String str) {

                return new Gson().fromJson(str, CastsEntity.class);
            }
        }

        public static class DirectorsEntity {
            /**
             * alt : https://movie.douban.com/celebrity/1019391/
             * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/58032.jpg","large":"https://img3.doubanio.com/img/celebrity/large/58032.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/58032.jpg"}
             * name : 艾斯彭·山德伯格
             * id : 1019391
             */

            @SerializedName("alt")
            public String alt;
            @SerializedName("avatars")
            public ImagesEntity avatars;
            @SerializedName("name")
            public String name;
            @SerializedName("id")
            public String id;

            public static DirectorsEntity objectFromData(String str) {

                return new Gson().fromJson(str, DirectorsEntity.class);
            }
        }
    }
}
