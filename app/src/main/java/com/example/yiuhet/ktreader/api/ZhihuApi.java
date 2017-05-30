package com.example.yiuhet.ktreader.api;

import com.example.yiuhet.ktreader.model.entity.ZhihuDetail;
import com.example.yiuhet.ktreader.model.entity.ZhihuLatest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yiuhet on 2017/5/22.
 */

public interface ZhihuApi {

    @GET("latest")
    Observable<ZhihuLatest> getZhihuLatest();

    @GET("before/{date}")
    Observable<ZhihuLatest> getBefore(@Path("date") String date);

    @GET("{id}")
    Observable<ZhihuDetail> getDetail(@Path("id") String id);

}
