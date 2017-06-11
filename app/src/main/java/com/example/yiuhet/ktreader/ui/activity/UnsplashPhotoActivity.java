package com.example.yiuhet.ktreader.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.yiuhet.ktreader.MVPBaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.app.MyApplication;
import com.example.yiuhet.ktreader.model.entity.UnsplashPhoto;
import com.example.yiuhet.ktreader.presenter.imp1.UnsplashPhotoPresenterImp1;
import com.example.yiuhet.ktreader.view.UnsplashPhotoView;
import com.jude.swipbackhelper.SwipeBackHelper;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yiuhet on 2017/6/8.
 */

public class UnsplashPhotoActivity extends MVPBaseActivity<UnsplashPhotoView, UnsplashPhotoPresenterImp1> implements UnsplashPhotoView {

    @BindView(R.id.button_collect)
    TextView mCollect;
    @BindView(R.id.button_detail)
    TextView mDetail;
    @BindView(R.id.button_download)
    TextView mDownload;
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;

    private String PhotoUrl;
    private UnsplashPhoto mUnsplashPhoto;
    private String[] itemsSize = { "raw (大小正在计算中)","full (大小正在计算中)","regularl (大小正在计算中)","small (大小正在计算中)" };
    private String[] items = { "raw","full","regular","small" };
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        PhotoUrl = getIntent().getStringExtra("PHOTOID");
        mPresenter.getPhoto(PhotoUrl);
    }

    @Override
    public void onStartGetData() {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetPhotoSuccess(UnsplashPhoto photo) {
        mUnsplashPhoto = photo;
        Glide.with(this).load(photo.urls.regular)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (mPrograss != null) {
                            mPrograss.setVisibility(View.GONE);
                        }
                        return false;
                    }
                })
                .into(mIvPhoto);
    }

    @Override
    public void onGetDataFailed(String error) {
        if (mPrograss != null) {
            mPrograss.setVisibility(View.GONE);
        }
        toast(error);
    }

    @Override
    public void onGetSizeSuccess(int size,int pos) {
        itemsSize[pos] = String.format(items[pos] + "       (图片大约" + size/1024 + "K)");;
        adapter.notifyDataSetChanged();
    }

    @Override
    protected UnsplashPhotoPresenterImp1 createPresenter() {
        return new UnsplashPhotoPresenterImp1(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_unsplashphoto;
    }

    @OnClick({R.id.button_collect, R.id.button_detail, R.id.button_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_collect:
                //Todo 收藏功能
                Snackbar.make(view, "添加进收藏夹（待做功能）", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.button_detail:
                if (mUnsplashPhoto != null) {
                    showPhotoDetail();
                }
                break;
            case R.id.button_download:
                mPresenter.getPhotoSize(mUnsplashPhoto.urls.raw,0);
                mPresenter.getPhotoSize(mUnsplashPhoto.urls.full,1);
                mPresenter.getPhotoSize(mUnsplashPhoto.urls.regular,2);
                mPresenter.getPhotoSize(mUnsplashPhoto.urls.small,3);
                showListDialog();
                break;
        }
    }

    private void showPhotoDetail() {
        String[] detailItems = {
//                "标题:  " + mUnsplashPhoto.location.title,
//                "作者:  " + mUnsplashPhoto.location.name,
                "拍摄时间:  " + mUnsplashPhoto.createdAt,
                "拍摄设备:  " + mUnsplashPhoto.exif.make,
                "宽度:  " + mUnsplashPhoto.width,
                "高度:  " + mUnsplashPhoto.height,
                "保存路径:  " + MyApplication.getAppCacheDir()+ File.separator + mUnsplashPhoto.id + ".jpg"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Photo 详情")
                .setItems(detailItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    private void showListDialog() {
        final Context dialogContext = new ContextThemeWrapper(this,
                android.R.style.Theme_Light);
        final LayoutInflater dialogInflater = (LayoutInflater) dialogContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        adapter = new ArrayAdapter<String>(this,
                R.layout.item_dialog_list, itemsSize) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = dialogInflater.inflate(
                            R.layout.item_dialog_list, parent, false);
                }

                final TextView text1 = (TextView) convertView
                        .findViewById(R.id.tv);
                final String display = this.getItem(position);
                text1.setText(display);

                return convertView;
            }
        };
        //mHandler.sendEmptyMessage(0);
        //View view = View.inflate(getApplicationContext(), R.layout.item_dialog_list, null);
        AlertDialog.Builder listDialog = new AlertDialog.Builder(this)
                .setTitle("选择要下载的图片质量：")
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toast("已添加进下载任务，图片质量" + itemsSize[which] );
                        switch (which) {
                            case 0:
                                mPresenter.downLoadPhoto(mUnsplashPhoto.urls.raw,
                                        mUnsplashPhoto.id + "(" + items[which] + ")");
                                break;
                            case 1:
                                mPresenter.downLoadPhoto(mUnsplashPhoto.urls.full,
                                        mUnsplashPhoto.id + "(" + items[which] + ")");
                                break;
                            case 2:
                                mPresenter.downLoadPhoto(mUnsplashPhoto.urls.regular,
                                        mUnsplashPhoto.id + "(" + items[which] + ")");
                                break;
                            case 3:
                                mPresenter.downLoadPhoto(mUnsplashPhoto.urls.small,
                                        mUnsplashPhoto.id + "(" + items[which] + ")");
                                break;
                            default:
                                break;
                        }

                    }
                })
                .setPositiveButton("取消下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        listDialog.show();
    }

//    private Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            // TODO Auto-generated method stub
//            int what = msg.what;
//            if (what == 0) {    //update
////                TextView tv = (TextView) listDialog.findViewById(R.id.raw);
//               ///if (tv != null) {
//                   itemsSize[1] = DateFormat.format("yyyy-MM-dd hh:mm:ss", System
//                            .currentTimeMillis()).toString();
//               //}
//                if(listDialog.isShowing()){
//                    mHandler.sendEmptyMessageDelayed(0,1000);
//                }
//            }else {
//                listDialog.cancel();
//            }
//        }
//    };


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }
}
