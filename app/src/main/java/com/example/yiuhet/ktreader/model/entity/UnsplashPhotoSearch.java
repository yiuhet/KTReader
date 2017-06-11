package com.example.yiuhet.ktreader.model.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yiuhet on 2017/6/7.
 */

public class UnsplashPhotoSearch {

    /**
     * total : 133
     * total_pages : 7
     * results : [{"id":"eOLpJytrbsQ","created_at":"2014-11-18T14:35:36-05:00","width":4000,"height":3000,"color":"#A7A2A1","likes":286,"liked_by_user":false,"user":{"id":"Ul0QVz12Goo","username":"ugmonk","name":"Jeff Sheldon","portfolio_url":"http://ugmonk.com/","profile_image":{"small":"https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=7cfe3b93750cb0c93e2f7caec08b5a41","medium":"https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=5a9dc749c43ce5bd60870b129a40902f","large":"https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=32085a077889586df88bfbe406692202"},"links":{"self":"https://api.unsplash.com/users/ugmonk","html":"http://unsplash.com/@ugmonk","photos":"https://api.unsplash.com/users/ugmonk/photos","likes":"https://api.unsplash.com/users/ugmonk/likes"}},"current_user_collections":[],"urls":{"raw":"https://images.unsplash.com/photo-1416339306562-f3d12fefd36f","full":"https://hd.unsplash.com/photo-1416339306562-f3d12fefd36f","regular":"https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=92f3e02f63678acc8416d044e189f515","small":"https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=263af33585f9d32af39d165b000845eb","thumb":"https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=8aae34cf35df31a592f0bef16e6342ef"},"links":{"self":"https://api.unsplash.com/photos/eOLpJytrbsQ","html":"http://unsplash.com/photos/eOLpJytrbsQ","download":"http://unsplash.com/photos/eOLpJytrbsQ/download"}}]
     */

    @SerializedName("total")
    public int total;
    @SerializedName("total_pages")
    public int totalPages;
    @SerializedName("results")
    public List<UnsplashPhotosList> results;

    public static UnsplashPhotoSearch objectFromData(String str) {

        return new Gson().fromJson(str, UnsplashPhotoSearch.class);
    }

//    public static class ResultsEntity {
//        /**
//         * id : eOLpJytrbsQ
//         * created_at : 2014-11-18T14:35:36-05:00
//         * width : 4000
//         * height : 3000
//         * color : #A7A2A1
//         * likes : 286
//         * liked_by_user : false
//         * user : {"id":"Ul0QVz12Goo","username":"ugmonk","name":"Jeff Sheldon","portfolio_url":"http://ugmonk.com/","profile_image":{"small":"https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=7cfe3b93750cb0c93e2f7caec08b5a41","medium":"https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=5a9dc749c43ce5bd60870b129a40902f","large":"https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=32085a077889586df88bfbe406692202"},"links":{"self":"https://api.unsplash.com/users/ugmonk","html":"http://unsplash.com/@ugmonk","photos":"https://api.unsplash.com/users/ugmonk/photos","likes":"https://api.unsplash.com/users/ugmonk/likes"}}
//         * current_user_collections : []
//         * urls : {"raw":"https://images.unsplash.com/photo-1416339306562-f3d12fefd36f","full":"https://hd.unsplash.com/photo-1416339306562-f3d12fefd36f","regular":"https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=92f3e02f63678acc8416d044e189f515","small":"https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=263af33585f9d32af39d165b000845eb","thumb":"https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=8aae34cf35df31a592f0bef16e6342ef"}
//         * links : {"self":"https://api.unsplash.com/photos/eOLpJytrbsQ","html":"http://unsplash.com/photos/eOLpJytrbsQ","download":"http://unsplash.com/photos/eOLpJytrbsQ/download"}
//         */
//
//        @SerializedName("id")
//        public String id;
//        @SerializedName("created_at")
//        public String createdAt;
//        @SerializedName("width")
//        public int width;
//        @SerializedName("height")
//        public int height;
//        @SerializedName("color")
//        public String color;
//        @SerializedName("likes")
//        public int likes;
//        @SerializedName("liked_by_user")
//        public boolean likedByUser;
//        @SerializedName("user")
//        public UserEntity user;
//        @SerializedName("urls")
//        public UrlsEntity urls;
//        @SerializedName("links")
//        public LinksEntityX links;
//        @SerializedName("current_user_collections")
//        public List<?> currentUserCollections;
//
//        public static ResultsEntity objectFromData(String str) {
//
//            return new Gson().fromJson(str, ResultsEntity.class);
//        }
//
//        public static class UserEntity {
//            /**
//             * id : Ul0QVz12Goo
//             * username : ugmonk
//             * name : Jeff Sheldon
//             * portfolio_url : http://ugmonk.com/
//             * profile_image : {"small":"https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=7cfe3b93750cb0c93e2f7caec08b5a41","medium":"https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=5a9dc749c43ce5bd60870b129a40902f","large":"https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=32085a077889586df88bfbe406692202"}
//             * links : {"self":"https://api.unsplash.com/users/ugmonk","html":"http://unsplash.com/@ugmonk","photos":"https://api.unsplash.com/users/ugmonk/photos","likes":"https://api.unsplash.com/users/ugmonk/likes"}
//             */
//
//            @SerializedName("id")
//            public String id;
//            @SerializedName("username")
//            public String username;
//            @SerializedName("name")
//            public String name;
//            @SerializedName("portfolio_url")
//            public String portfolioUrl;
//            @SerializedName("profile_image")
//            public ProfileImageEntity profileImage;
//            @SerializedName("links")
//            public LinksEntity links;
//
//            public static UserEntity objectFromData(String str) {
//
//                return new Gson().fromJson(str, UserEntity.class);
//            }
//
//            public static class ProfileImageEntity {
//                /**
//                 * small : https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=7cfe3b93750cb0c93e2f7caec08b5a41
//                 * medium : https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=5a9dc749c43ce5bd60870b129a40902f
//                 * large : https://images.unsplash.com/profile-1441298803695-accd94000cac?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=32085a077889586df88bfbe406692202
//                 */
//
//                @SerializedName("small")
//                public String small;
//                @SerializedName("medium")
//                public String medium;
//                @SerializedName("large")
//                public String large;
//
//                public static ProfileImageEntity objectFromData(String str) {
//
//                    return new Gson().fromJson(str, ProfileImageEntity.class);
//                }
//            }
//
//            public static class LinksEntity {
//                /**
//                 * self : https://api.unsplash.com/users/ugmonk
//                 * html : http://unsplash.com/@ugmonk
//                 * photos : https://api.unsplash.com/users/ugmonk/photos
//                 * likes : https://api.unsplash.com/users/ugmonk/likes
//                 */
//
//                @SerializedName("self")
//                public String self;
//                @SerializedName("html")
//                public String html;
//                @SerializedName("photos")
//                public String photos;
//                @SerializedName("likes")
//                public String likes;
//
//                public static LinksEntity objectFromData(String str) {
//
//                    return new Gson().fromJson(str, LinksEntity.class);
//                }
//            }
//        }
//
//        public static class UrlsEntity {
//            /**
//             * raw : https://images.unsplash.com/photo-1416339306562-f3d12fefd36f
//             * full : https://hd.unsplash.com/photo-1416339306562-f3d12fefd36f
//             * regular : https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=92f3e02f63678acc8416d044e189f515
//             * small : https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=263af33585f9d32af39d165b000845eb
//             * thumb : https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=8aae34cf35df31a592f0bef16e6342ef
//             */
//
//            @SerializedName("raw")
//            public String raw;
//            @SerializedName("full")
//            public String full;
//            @SerializedName("regular")
//            public String regular;
//            @SerializedName("small")
//            public String small;
//            @SerializedName("thumb")
//            public String thumb;
//
//            public static UrlsEntity objectFromData(String str) {
//
//                return new Gson().fromJson(str, UrlsEntity.class);
//            }
//        }
//
//        public static class LinksEntityX {
//            /**
//             * self : https://api.unsplash.com/photos/eOLpJytrbsQ
//             * html : http://unsplash.com/photos/eOLpJytrbsQ
//             * download : http://unsplash.com/photos/eOLpJytrbsQ/download
//             */
//
//            @SerializedName("self")
//            public String self;
//            @SerializedName("html")
//            public String html;
//            @SerializedName("download")
//            public String download;
//
//            public static LinksEntityX objectFromData(String str) {
//
//                return new Gson().fromJson(str, LinksEntityX.class);
//            }
//        }
//    }
}
