package com.example.yiuhet.ktreader.presenter.listener;


/**
 * Created by yiuhet on 2017/5/28.
 * 在Presenter层实现，给Model层回调，更改View层的状态，确保Model层不直接操作View层
 */

public interface OnZhihuLatestListener {
    /**
     * 成功时回调
     */
    void onLoadZhihuLatestSuccess();

    void onLoadMoreSuccess();

    /**
     * 失败时回调
     */
    void onLoadDataError(String error);
}
