package com.example.yiuhet.ktreader.model.imp1;

import android.util.Log;

import com.example.yiuhet.ktreader.api.ZhihuApi;
import com.example.yiuhet.ktreader.app.Constant;
import com.example.yiuhet.ktreader.model.ZhihuDetailModel;
import com.example.yiuhet.ktreader.model.entity.ZhihuDetail;
import com.example.yiuhet.ktreader.presenter.listener.OnZhihuDetailListener;
import com.example.yiuhet.ktreader.utils.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yiuhet on 2017/5/29.
 */

public class ZhihuDetailModelImp1 implements ZhihuDetailModel {
   /*获取日报详情的Model实现*/

    private ZhihuApi mZhihuApiService; //请求服务
    private ZhihuDetail mZhihuDetail;

    public ZhihuDetailModelImp1() {
        mZhihuDetail = new ZhihuDetail();
        mZhihuApiService = RetrofitManager.getInstence()
                .getZhihuService(Constant.ZHIHU_BASE_URL); //创建请求服务
    }

    public ZhihuDetail getDetail() {
        return mZhihuDetail;
    }


    @Override
    public void loadDetail(String id, final OnZhihuDetailListener listener) {
        if (mZhihuApiService != null) {
            mZhihuApiService.getDetail(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ZhihuDetail>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull ZhihuDetail zhihuDetail) {
                            mZhihuDetail = zhihuDetail;
//                            Log.e("yiuhet", "--------------->" + zhihuDetail.toString());
                            listener.onLoadZhihuDetailSuccess(zhihuDetail);//加载成功时 回调接口方法。
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            listener.onLoadDataError(e.toString());//加载失败时 回调接口方法。
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
