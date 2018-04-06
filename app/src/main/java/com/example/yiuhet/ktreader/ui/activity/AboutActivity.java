package com.example.yiuhet.ktreader.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.yiuhet.ktreader.BaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.button_github)
    Button mButtonGithub;
    @BindView(R.id.button_jianshu)
    Button mButtonJianshu;
    @BindView(R.id.button_share)
    Button mButtonShare;
    @BindView(R.id.button_tucao)
    Button mButtonTucao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mToolbar.setTitle("关于");
        setSupportActionBar(mToolbar);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_about;
    }

    @OnClick({R.id.button_github, R.id.button_jianshu, R.id.button_share, R.id.button_tucao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_github:
                goToHtml("https://github.com/yiuhet/KTReader");
                break;
            case R.id.button_jianshu:
                goToHtml("http://www.jianshu.com/u/8857dea54ec2");
                break;
            case R.id.button_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "分享app");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_txt));
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_app)));
                break;
            case R.id.button_tucao:
                ClipboardManager manager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("msg", "965846580");
                manager.setPrimaryClip(clipData);
                Snackbar.make(view,"我的号qq已经复制到粘贴板啦( •̀ .̫ •́ )✧",Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    private void goToHtml(String url) {
        Uri uri = Uri.parse(url);   //指定网址
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);           //指定Action
        intent.setData(uri);                            //设置Uri
        startActivity(intent);        //启动Activity
    }

}
