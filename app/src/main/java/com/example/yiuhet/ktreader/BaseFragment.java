package com.example.yiuhet.ktreader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yiuhet.ktreader.utils.CommonUtils;

import butterknife.ButterKnife;

/**
 * Created by yiuhet on 2017/5/18.
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    protected T mPresenter;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutRes(), null);
        ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
    }

    protected abstract int getLayoutRes();

    protected void setTitle(String title) {
        getActivity().getActionBar().setTitle(title);
    }

    protected void showProgress(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    protected void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    protected void toast(String msg) {
        CommonUtils.ShowTips(getContext(), msg);
    }

    protected void startActivity(Class activity) {
        startActivity(activity, true);
    }

    protected void startActivity(Class activity, boolean finish) {
        Intent intent = new Intent(getContext(), activity);
        startActivity(intent);
        if (finish) {
            getActivity().finish();
        }
    }

    protected void startActivity(Class activity, String key, String extra) {
        Intent intent = new Intent(getContext(),activity);
        intent.putExtra(key, extra);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mProgressDialog = null;
    }

    protected abstract T createPresenter();

}
