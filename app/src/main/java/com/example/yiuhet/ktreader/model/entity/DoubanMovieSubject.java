package com.example.yiuhet.ktreader.model.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/3.
 */

public class DoubanMovieSubject {

    /**
     * rating : {"max":10,"average":9.6,"stars":"50","min":0}
     * reviews_count : 4883
     * wish_count : 77997
     * douban_site :
     * year : 1994
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.webp","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.webp","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p480747492.webp"}
     * alt : https://movie.douban.com/subject/1292052/
     * id : 1292052
     * mobile_url : https://movie.douban.com/subject/1292052/mobile
     * title : 肖申克的救赎
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/1292052
     * seasons_count : null
     * schedule_url :
     * episodes_count : null
     * countries : ["美国"]
     * genres : ["犯罪","剧情"]
     * collect_count : 1068873
     * casts : [{"alt":"https://movie.douban.com/celebrity/1054521/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"},"name":"蒂姆·罗宾斯","id":"1054521"},{"alt":"https://movie.douban.com/celebrity/1054534/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/34642.jpg","large":"https://img3.doubanio.com/img/celebrity/large/34642.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/34642.jpg"},"name":"摩根·弗里曼","id":"1054534"},{"alt":"https://movie.douban.com/celebrity/1041179/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/5837.jpg","large":"https://img1.doubanio.com/img/celebrity/large/5837.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/5837.jpg"},"name":"鲍勃·冈顿","id":"1041179"},{"alt":"https://movie.douban.com/celebrity/1000095/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/7827.jpg","large":"https://img1.doubanio.com/img/celebrity/large/7827.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/7827.jpg"},"name":"威廉姆·赛德勒","id":"1000095"}]
     * current_season : null
     * original_title : The Shawshank Redemption
     * summary : 20世纪40年代末，小有成就的青年银行家安迪（蒂姆·罗宾斯 Tim Robbins 饰）因涉嫌杀害妻子及她的情人而锒铛入狱。在这座名为肖申克的监狱内，希望似乎虚无缥缈，终身监禁的惩罚无疑注定了安迪接下来灰暗绝望的人生。未过多久，安迪尝试接近囚犯中颇有声望的瑞德（摩根·弗里曼 Morgan Freeman 饰），请求对方帮自己搞来小锤子。以此为契机，二人逐渐熟稔，安迪也仿佛在鱼龙混杂、罪恶横生、黑白混淆的牢狱中找到属于自己的求生之道。他利用自身的专业知识，帮助监狱管理层逃税、洗黑钱，同时凭借与瑞德的交往在犯人中间也渐渐受到礼遇。表面看来，他已如瑞德那样对那堵高墙从憎恨转变为处之泰然，但是对自由的渴望仍促使他朝着心中的希望和目标前进。而关于其罪行的真相，似乎更使这一切朝前推进了一步……
     本片根据著名作家斯蒂芬·金（Stephen Edwin King）的原著改编。©豆瓣
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1047973/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"},"name":"弗兰克·德拉邦特","id":"1047973"}]
     * comments_count : 193435
     * ratings_count : 828038
     * aka : ["月黑高飞(港)","刺激1995(台)","地狱诺言","铁窗岁月","消香克的救赎"]
     */

    @SerializedName("rating")
    public RatingEntity rating;
    @SerializedName("reviews_count")
    public int reviewsCount;
    @SerializedName("wish_count")
    public int wishCount;
    @SerializedName("douban_site")
    public String doubanSite;
    @SerializedName("year")
    public String year;
    @SerializedName("images")
    public ImagesEntity images;
    @SerializedName("alt")
    public String alt;
    @SerializedName("id")
    public String id;
    @SerializedName("mobile_url")
    public String mobileUrl;
    @SerializedName("title")
    public String title;
    @SerializedName("do_count")
    public Object doCount;
    @SerializedName("share_url")
    public String shareUrl;
    @SerializedName("seasons_count")
    public Object seasonsCount;
    @SerializedName("schedule_url")
    public String scheduleUrl;
    @SerializedName("episodes_count")
    public Object episodesCount;
    @SerializedName("collect_count")
    public int collectCount;
    @SerializedName("current_season")
    public Object currentSeason;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("summary")
    public String summary;
    @SerializedName("subtype")
    public String subtype;
    @SerializedName("comments_count")
    public int commentsCount;
    @SerializedName("ratings_count")
    public int ratingsCount;
    @SerializedName("countries")
    public List<String> countries;
    @SerializedName("genres")
    public List<String> genres;
    @SerializedName("casts")
    public List<CastsEntity> casts;
    @SerializedName("directors")
    public List<DirectorsEntity> directors;
    @SerializedName("aka")
    public List<String> aka;

    public static DoubanMovieSubject objectFromData(String str) {

        return new Gson().fromJson(str, DoubanMovieSubject.class);
    }

    public static class RatingEntity {
        /**
         * max : 10
         * average : 9.6
         * stars : 50
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
         * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.webp
         * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.webp
         * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p480747492.webp
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
         * alt : https://movie.douban.com/celebrity/1054521/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"}
         * name : 蒂姆·罗宾斯
         * id : 1054521
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

        @Override
        public String toString() {
            return name;
        }
    }

    public static class DirectorsEntity {
        /**
         * alt : https://movie.douban.com/celebrity/1047973/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"}
         * name : 弗兰克·德拉邦特
         * id : 1047973
         */

        @SerializedName("alt")
        public String alt;
        @SerializedName("avatars")
        public ImagesEntity avatars;
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public String id;
        @Override
        public String toString() {
            return name;
        }
        public static DirectorsEntity objectFromData(String str) {

            return new Gson().fromJson(str, DirectorsEntity.class);
        }
    }
}
