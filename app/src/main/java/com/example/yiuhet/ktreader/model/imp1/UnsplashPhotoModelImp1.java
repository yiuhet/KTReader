package com.example.yiuhet.ktreader.model.imp1;

import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.annotation.UiThread;
import android.util.Log;

import com.example.yiuhet.ktreader.api.TupianApi;
import com.example.yiuhet.ktreader.app.MyApplication;
import com.example.yiuhet.ktreader.model.UnsplashPhotoModel;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhoto;
import com.example.yiuhet.ktreader.model.entity.UnsplashUrl;
import com.example.yiuhet.ktreader.presenter.listener.OnUnsplashPhotoListener;
import com.example.yiuhet.ktreader.utils.RetrofitManager;
import com.example.yiuhet.ktreader.utils.ThreadUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by yiuhet on 2017/6/8.
 */

public class UnsplashPhotoModelImp1 implements UnsplashPhotoModel{
    private static final String UNSPLASH_APPLICATION_ID = "c38ecf4661326d08903149c16235862997f1afc00ed5e294a550f4305e59dcdf";
    private static final String UNSPLASH_BASE_URL = "https://api.unsplash.com/";


    private TupianApi mTupianApi;

    public UnsplashPhotoModelImp1() {
        mTupianApi = RetrofitManager
                .getInstence()
                .getTupianService(UNSPLASH_BASE_URL);
    }

    @Override
    public void loadPhoto(final OnUnsplashPhotoListener listener, String id) {
        if (mTupianApi != null) {
            mTupianApi.getUnsplashPhoto(id, UNSPLASH_APPLICATION_ID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UnsplashPhoto>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull UnsplashPhoto unsplashPhoto) {

                            Log.d("cppmb","onNext");
                            listener.onLoadPhotoSuccess(unsplashPhoto);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d("cppmb","onError");
                            listener.onLoadDataError(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
    @Override
    public void loadRandomPhoto(final OnUnsplashPhotoListener listener) {
        if (mTupianApi != null) {
            mTupianApi.getUnsplashRandomPhoto(UNSPLASH_APPLICATION_ID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UnsplashPhoto>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull UnsplashPhoto unsplashPhoto) {
                            listener.onLoadPhotoSuccess(unsplashPhoto);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            listener.onLoadDataError(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
    @Override
    public void downloadPhoto(final OnUnsplashPhotoListener listener, String id, final String photoName) {
       if (mTupianApi != null) {
           mTupianApi.getPhotoDownload(id)
                   .subscribeOn(Schedulers.io())
                   .map(new Function<ResponseBody, Boolean>() {
                       @Override
                       public Boolean apply(@NonNull ResponseBody responseBody) throws Exception {
                           return DownloadImage(responseBody,listener,photoName);
                       }
                   })
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Observer<Boolean>() {
                       @Override
                       public void onSubscribe(@NonNull Disposable d) {

                       }

                       @Override
                       public void onNext(@NonNull Boolean aBoolean) {
                            if (aBoolean) {
                                listener.onDownloadSuccess();
                            }
                       }

                       @Override
                       public void onError(@NonNull Throwable e) {
                           listener.onLoadDataError(e.toString());
                       }

                       @Override
                       public void onComplete() {

                       }
                   });
        }
    }

    @Override
    public void getPhotoSize(final OnUnsplashPhotoListener listener, final String urlString, final int pos) {
        Flowable.just(urlString)
                .subscribeOn(Schedulers.io())
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(@NonNull String s) throws Exception {
                        int size = -1;
                        try {
                            URL url = new URL(urlString);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            size = connection.getContentLength();
                            return size;
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        return size;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer s) throws Exception {
                        listener.onLoadSizeSuccess(s, pos);
                    }
                });

    }

    private boolean DownloadImage(ResponseBody body,OnUnsplashPhotoListener listener,String photoName) {
        try {
            InputStream in;
            in = null;
            FileOutputStream out = null;

            try {
                in = body.byteStream();
                //String dir = Environment.getExternalStorageDirectory() + "/KTReader/";
                out = new FileOutputStream(MyApplication.getAppCacheDir()+ File.separator + photoName + ".jpg");
                byte[] buf = new byte[1024];

                while ((in.read(buf)) != -1) {
                    out.write(buf);
                }

            }
            catch (IOException e) {
                return false;
            }
            finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            return true;

        } catch (IOException e) {
            listener.onLoadDataError(e.toString());
            return false;
        }
    }

}
