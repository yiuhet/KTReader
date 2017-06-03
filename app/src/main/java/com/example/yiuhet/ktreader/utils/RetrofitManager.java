package com.example.yiuhet.ktreader.utils;

import com.example.yiuhet.ktreader.api.DoubanApi;
import com.example.yiuhet.ktreader.api.ZhihuApi;
import com.example.yiuhet.ktreader.app.MyApplication;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.InternalCache;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yiuhet on 2017/5/23.
 */

public class RetrofitManager {

    private static RetrofitManager retrofitManager;

    private RetrofitManager() {
    }

    // 无论有无网络都读取缓存。(有时间限制) 把拦截器设置到addNetworkOnterceptor
    private static Interceptor netInterceptor1 = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            int maxAge = 60;  //60s为缓存的有效时间，60s内获取的是缓存数据，超过60S我们就去网络重新请求数据
            return response
                    .newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public,max-age=" + maxAge)
                    .build();
        }
    };
    //有网络读取网络的数据，没有网络读取缓存。
    private static class netInterceptor2 implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //没有网络时强制使用缓存数据
            if (!NetWorkUtil.isNetWorkAvailable(MyApplication.getContext())) {
                request = request.newBuilder()
                        //强制使用缓存数据
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (true) {
                return originalResponse .newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public,max-age=" + 0) //0为不进行缓存
                        .build();
            } else {
                int maxAge =  4 * 24 * 60 * 60; //缓存保存时间
                return originalResponse .newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-age=" + maxAge)
                        .build();
            }
        }
    };
    //缓存位置
    private static File cacheFile = new File(MyApplication.getAppCacheDir(), "caheData_zhihu");
    //设置缓存大小
    private static int DEFAULT_DIR_CACHE = 10 * 1024 * 1024;
    private static Cache cache = new Cache(cacheFile, DEFAULT_DIR_CACHE);

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new netInterceptor2())
            .addNetworkInterceptor(new netInterceptor2())
            .cache(cache)
            .build();

    public static RetrofitManager getInstence() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    private ZhihuApi zhihuApi;
    private DoubanApi doubanApi;

    public ZhihuApi getZhihuService(String url) {
        if (zhihuApi == null) {
            zhihuApi = new Retrofit.Builder()
                    .baseUrl(url) //必须以‘/’结尾
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用RxJava2作为CallAdapter
                    .client(client)//如果没有添加,那么retrofit2会自动给我们添加了一个。
                    .addConverterFactory(GsonConverterFactory.create())//Retrofit2可以帮我们自动解析返回数据，
                    .build().create(ZhihuApi.class);
        }
        return zhihuApi;
    }

    public DoubanApi getDoubanService(String url) {
        if (doubanApi == null) {
            doubanApi = new Retrofit.Builder()
                    .baseUrl(url) //必须以‘/’结尾
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用RxJava2作为CallAdapter
                    .client(client)//如果没有添加,那么retrofit2会自动给我们添加了一个。
                    .addConverterFactory(GsonConverterFactory.create())//Retrofit2可以帮我们自动解析返回数据，
                    .build().create(DoubanApi.class);
        }
        return doubanApi;
    }

}
