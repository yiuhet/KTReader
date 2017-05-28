package com.example.yiuhet.ktreader.model.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yiuhet on 2017/5/22.
 */

public class ZhihuLatest {

    /**
     * date : 20170522
     * stories : [{"images":["https://pic3.zhimg.com/v2-c6b3231ce57f40520cf97c4714b661de.jpg"],"type":0,"id":9435259,"ga_prefix":"052222","title":"小事 · 可是，你拿他的鞋干嘛"},{"title":"没什么人看，但我推荐你一定要看","ga_prefix":"052221","images":["https://pic4.zhimg.com/v2-7b6ada623d9d63e485a2762a013ec16b.jpg"],"multipic":true,"type":0,"id":9432469},{"title":"为了更愉快地逛街，成都太古里的每个细节都精心设计","ga_prefix":"052220","images":["https://pic3.zhimg.com/v2-e4918a32a221c9ec847729c80c3db7ee.jpg"],"multipic":true,"type":0,"id":9434844},{"images":["https://pic2.zhimg.com/v2-323f15bf0f3c4bd08e0de715a8109b71.jpg"],"type":0,"id":9434773,"ga_prefix":"052220","title":"面对更强的 AlphaGo，柯洁能带来奇迹吗？"},{"images":["https://pic3.zhimg.com/v2-f0c1bc4dabf231602a55e7a2935fbc9a.jpg"],"type":0,"id":9434662,"ga_prefix":"052219","title":"十五分钟 · 人工智能不是泡沫，它只是必然会发生的事"},{"images":["https://pic4.zhimg.com/v2-1944b61b37bf16213b4dfea2ee26d3a3.jpg"],"type":0,"id":9433985,"ga_prefix":"052218","title":"鱼会数数吗？"},{"images":["https://pic1.zhimg.com/v2-e7c66bbe24f284af502ac2705eb849f8.jpg"],"type":0,"id":9434263,"ga_prefix":"052218","title":"它被怀疑是外星人建造的「戴森球」，最近又变暗了\u2026\u2026"},{"images":["https://pic4.zhimg.com/v2-38d536b7434f4e23918d3735244b430f.jpg"],"type":0,"id":9434400,"ga_prefix":"052216","title":"电竞早就是体育项目，但不少人觉得它和「体育」不挨边"},{"images":["https://pic2.zhimg.com/v2-99ff1e529fb7debd0867a93da757fd05.jpg"],"type":0,"id":9434326,"ga_prefix":"052216","title":"贾跃亭辞任总经理：闹了大半年，乐视这次能否度过危机？"},{"images":["https://pic2.zhimg.com/v2-b2a8a66b8a369f6c7026713656b62de1.jpg"],"type":0,"id":9434365,"ga_prefix":"052215","title":"搞清五险一金是怎么回事，才不会糊里糊涂地交钱"},{"images":["https://pic4.zhimg.com/v2-bfc513bb0c551f2006dc6d6b0e13738b.jpg"],"type":0,"id":9432849,"ga_prefix":"052213","title":"护肤品里的火山泥真的能深度清洁皮肤吗？"},{"images":["https://pic2.zhimg.com/v2-77c7d8459d83f6cceaad15d44c81284d.jpg"],"type":0,"id":9432604,"ga_prefix":"052212","title":"大误 · 做科研使人成熟"},{"images":["https://pic2.zhimg.com/v2-3270087a91c384370b626fb582b19ca5.jpg"],"type":0,"id":9431747,"ga_prefix":"052211","title":"如何建立自己的知识体系？"},{"images":["https://pic3.zhimg.com/v2-53d00b0441e74e9ec902fb283cf61586.jpg"],"type":0,"id":9433324,"ga_prefix":"052210","title":"介绍一种不需要懂会计，就能看出假账的方法"},{"images":["https://pic1.zhimg.com/v2-2b40ba07be4dcc8fa39c3c3734917e20.jpg"],"type":0,"id":9433269,"ga_prefix":"052209","title":"谷歌发布了比 GPS 更厉害的 VPS，室内室外厘米级定位"},{"images":["https://pic4.zhimg.com/v2-208953fd6c543f6f743db51215cfe56f.jpg"],"type":0,"id":9432908,"ga_prefix":"052208","title":"经济不景气的时候，建个足球场试试"},{"title":"行李系统是机场航站楼中最复杂的系统，没有之一","ga_prefix":"052207","images":["https://pic3.zhimg.com/v2-55e5a5934e7ca14acf73e06d5772fdca.jpg"],"multipic":true,"type":0,"id":9432895},{"images":["https://pic3.zhimg.com/v2-63e71456fd5756541153105e578883da.jpg"],"type":0,"id":9433189,"ga_prefix":"052207","title":"「除了自我伤害，也没有更好的办法，让人正视我的绝望」"},{"images":["https://pic2.zhimg.com/v2-7b15474c3fb1b0aa580604a51782236d.jpg"],"type":0,"id":9433229,"ga_prefix":"052207","title":"日本为什么会自愿接受看起来对本国不利的「广场协议」？"},{"images":["https://pic1.zhimg.com/v2-7b68f871e16f3612571fbe7cf2062ec0.jpg"],"type":0,"id":9430250,"ga_prefix":"052206","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-5ec11e9f2672d93b69b5fbf15910df6d.jpg","type":0,"id":9434773,"ga_prefix":"052220","title":"面对更强的 AlphaGo，柯洁能带来奇迹吗？"},{"image":"https://pic3.zhimg.com/v2-4ac454e049dc68499bf36cc049326e46.jpg","type":0,"id":9434326,"ga_prefix":"052216","title":"贾跃亭辞任总经理：闹了大半年，乐视这次能否度过危机？"},{"image":"https://pic4.zhimg.com/v2-6039136be716e1254cdf187921216fc3.jpg","type":0,"id":9432895,"ga_prefix":"052207","title":"行李系统是机场航站楼中最复杂的系统，没有之一"},{"image":"https://pic2.zhimg.com/v2-e8eafadcf3db62f107ccd9ec8e5b737d.jpg","type":0,"id":9433189,"ga_prefix":"052207","title":"「除了自我伤害，也没有更好的办法，让人正视我的绝望」"},{"image":"https://pic3.zhimg.com/v2-3a591d2d81708ce9c0d7ecb4e2753e6e.jpg","type":0,"id":9433229,"ga_prefix":"052207","title":"日本为什么会自愿接受看起来对本国不利的「广场协议」？"}]
     */

    @SerializedName("date")
    public String date;
    @SerializedName("stories")
    public List<StoriesEntity> stories;
    @SerializedName("top_stories")
    public List<TopStoriesEntity> topStories;

    public static ZhihuLatest objectFromData(String str) {

        return new Gson().fromJson(str, ZhihuLatest.class);
    }

    public static class StoriesEntity {
        /**
         * images : ["https://pic3.zhimg.com/v2-c6b3231ce57f40520cf97c4714b661de.jpg"]
         * type : 0
         * id : 9435259
         * ga_prefix : 052222
         * title : 小事 · 可是，你拿他的鞋干嘛
         * multipic : true
         */

        @SerializedName("type")
        public int type;
        @SerializedName("id")
        public int id;
        @SerializedName("ga_prefix")
        public String gaPrefix;
        @SerializedName("title")
        public String title;
        @SerializedName("multipic")
        public boolean multipic;
        @SerializedName("images")
        public List<String> images;

        public static StoriesEntity objectFromData(String str) {

            return new Gson().fromJson(str, StoriesEntity.class);
        }
    }

    public static class TopStoriesEntity {
        /**
         * image : https://pic2.zhimg.com/v2-5ec11e9f2672d93b69b5fbf15910df6d.jpg
         * type : 0
         * id : 9434773
         * ga_prefix : 052220
         * title : 面对更强的 AlphaGo，柯洁能带来奇迹吗？
         */

        @SerializedName("image")
        public String image;
        @SerializedName("type")
        public int type;
        @SerializedName("id")
        public int id;
        @SerializedName("ga_prefix")
        public String gaPrefix;
        @SerializedName("title")
        public String title;

        public static TopStoriesEntity objectFromData(String str) {
            return new Gson().fromJson(str, TopStoriesEntity.class);
        }
    }
}
