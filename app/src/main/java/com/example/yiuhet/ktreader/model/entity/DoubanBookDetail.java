package com.example.yiuhet.ktreader.model.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/2.
 */

public class DoubanBookDetail {

    /**
     * rating : {"max":10,"numRaters":56214,"average":"8.4","min":0}
     * subtitle : 修订版
     * author : ["今何在"]
     * pubdate : 2001-4
     * tags : [{"count":9738,"name":"悟空传","title":"悟空传"},{"count":9667,"name":"今何在","title":"今何在"},{"count":5991,"name":"小说","title":"小说"},{"count":4161,"name":"网络小说","title":"网络小说"},{"count":2937,"name":"网络文学","title":"网络文学"},{"count":1945,"name":"西游记","title":"西游记"},{"count":1819,"name":"中国","title":"中国"},{"count":1471,"name":"文学","title":"文学"}]
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s9026255.jpg
     * binding : 平装
     * translator : []
     * catalog : 网友说
     悟空传
     百年孤独
     花果山
     评论

     * pages : 300
     * images : {"small":"https://img3.doubanio.com/spic/s9026255.jpg","large":"https://img3.doubanio.com/lpic/s9026255.jpg","medium":"https://img3.doubanio.com/mpic/s9026255.jpg"}
     * alt : https://book.douban.com/subject/1003000/
     * id : 1003000
     * publisher : 光明日报出版社
     * isbn10 : 7801453646
     * isbn13 : 9787801453648
     * title : 悟空传
     * url : https://api.douban.com/v2/book/1003000
     * alt_title :
     * author_intro : 今何在，网络文学作家，1977年生人。《悟空传》、《诺星汉天空》作者。做过网站管理，游戏策划，影视编剧。
     * summary : 猪八戒和孙悟空虽然都神通广大，但在命运面前终究是软弱无力的小人物。顶天立地的美猴王实际上仍然是那个充满惊恐的小猴子，而决心与命运抗争的天逢若非紧急也终究不肯以猪的面目见阿月。神仙尚且如此，何况吾辈生来渺小的小人物呢？随着年纪的增长，少年时素怀的“愿乘长风破万里浪”的豪情壮志早已经烟消云散，只剩下一个行尸走肉的家伙还在苟延残喘。自己何尝不是一只因为知道自己身份而在午夜对月痛哭的猪啊！但他们最终战胜了命运，而我呢？
     * price : 14.80元
     */

    @SerializedName("rating")
    public RatingEntity rating;
    @SerializedName("subtitle")
    public String subtitle;
    @SerializedName("pubdate")
    public String pubdate;
    @SerializedName("origin_title")
    public String originTitle;
    @SerializedName("image")
    public String image;
    @SerializedName("binding")
    public String binding;
    @SerializedName("catalog")
    public String catalog;
    @SerializedName("pages")
    public String pages;
    @SerializedName("images")
    public ImagesEntity images;
    @SerializedName("alt")
    public String alt;
    @SerializedName("id")
    public String id;
    @SerializedName("publisher")
    public String publisher;
    @SerializedName("isbn10")
    public String isbn10;
    @SerializedName("isbn13")
    public String isbn13;
    @SerializedName("title")
    public String title;
    @SerializedName("url")
    public String url;
    @SerializedName("alt_title")
    public String altTitle;
    @SerializedName("author_intro")
    public String authorIntro;
    @SerializedName("summary")
    public String summary;
    @SerializedName("price")
    public String price;
    @SerializedName("author")
    public List<String> author;
    @SerializedName("tags")
    public List<TagsEntity> tags;
    @SerializedName("translator")
    public List<?> translator;

    public static DoubanBookDetail objectFromData(String str) {

        return new Gson().fromJson(str, DoubanBookDetail.class);
    }

    public static class RatingEntity {
        /**
         * max : 10
         * numRaters : 56214
         * average : 8.4
         * min : 0
         */

        @SerializedName("max")
        public int max;
        @SerializedName("numRaters")
        public int numRaters;
        @SerializedName("average")
        public String average;
        @SerializedName("min")
        public int min;

        public static RatingEntity objectFromData(String str) {

            return new Gson().fromJson(str, RatingEntity.class);
        }
    }

    public static class ImagesEntity {
        /**
         * small : https://img3.doubanio.com/spic/s9026255.jpg
         * large : https://img3.doubanio.com/lpic/s9026255.jpg
         * medium : https://img3.doubanio.com/mpic/s9026255.jpg
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

    public static class TagsEntity {
        /**
         * count : 9738
         * name : 悟空传
         * title : 悟空传
         */

        @SerializedName("count")
        public int count;
        @SerializedName("name")
        public String name;
        @SerializedName("title")
        public String title;

        public static TagsEntity objectFromData(String str) {

            return new Gson().fromJson(str, TagsEntity.class);
        }

        @Override
        public String toString() {

            return String.format("%s(%s)",name.toString(),count);
        }
    }
}
