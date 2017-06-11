package com.example.yiuhet.ktreader.api;

import com.example.yiuhet.ktreader.model.entity.UnsplashPhoto;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotoSearch;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhotosList;
import com.example.yiuhet.ktreader.model.entity.UnsplashUrl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by yiuhet on 2017/6/6.
 */

public interface TupianApi {
    /*
     *Get a single page from the list of all photos.
     * 参数分别为appId,页码，每页个数，排序规则 (Valid values: latest, oldest, popular; default: latest)
     */
    @GET("photos")
    Observable<List<UnsplashPhotosList>> getUnsplashPhotosList
        (@Query("client_id") String clientId,@Query("page") int page,@Query("per_page") int perPage,
         @Query("order_by") String orderBy);

    @GET("/search/photos")
    Observable<UnsplashPhotoSearch> getSearchUnsplashPhotosList
            (@Query("client_id") String clientId,@Query("query") String query,@Query("page") int page,
             @Query("per_page") int perPage);

    @GET("photos/random")
    Observable<UnsplashPhoto> getUnsplashRandomPhoto(@Query("client_id") String clientId);

    @GET("photos/{id}")
    Observable<UnsplashPhoto> getUnsplashPhoto
        (@Path("id")  String id, @Query("client_id") String clientId);

    @GET("photos/{id}/download")
    Observable<UnsplashUrl> getPhotoDownloadUrl(
            @Path("id")  String id, @Query("client_id") String clientId);

    @GET
    Observable<ResponseBody> getPhotoDownload(@Url String url);

}
