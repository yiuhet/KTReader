package com.example.yiuhet.ktreader.api;

import com.example.yiuhet.ktreader.model.entity.Request;
import com.example.yiuhet.ktreader.model.entity.Response;
import com.example.yiuhet.ktreader.model.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yiuhet on 2018/6/12.
 */

public interface LoginApi {

    @GET("/login")
    Observable<Response<User>> login(@Query("userId")  String id, @Query("password") String passwprd); //登陆


    @POST("/insert")
    Observable<Response<Void>> insert(@Body Request<User> request);
}
