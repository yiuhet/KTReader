package com.example.yiuhet.ktreader.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yiuhet.ktreader.BaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.app.MyApplication;
import com.example.yiuhet.ktreader.factory.FragmentFactory;
import com.example.yiuhet.ktreader.ui.fragment.ZhiHuFragment;
import com.example.yiuhet.ktreader.utils.CommonUtils;
import com.example.yiuhet.ktreader.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiuhet on 17/5/18.
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fragment_main)
    FrameLayout fragmentMain;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    TextView userId;
    TextView userName;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("testsafa", prefs.getString("settings_theme","indigo"));
        Log.d("testsafa", String.valueOf(SharedPreferenceUtil.getInstence().getSettingsSafe()));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.title_zhihu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        mNavView.setCheckedItem(R.id.nav_zhihu);
        View headerLayout = mNavView.inflateHeaderView(R.layout.nav_header_main);
        ImageView userHead = (ImageView) headerLayout.findViewById(R.id.imageView);
        userId = (TextView) headerLayout.findViewById(R.id.userId);
        userName = (TextView) headerLayout.findViewById(R.id.userName);
        userHead.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main, new ZhiHuFragment()).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("yiuhet", "登陆状态：" + MyApplication.isLogin);
        if (MyApplication.isLogin) {
            loadUserData();
        }
    }

    // TODO: 2018/6/11 查询登陆信息
    private void loadUserData() {
        userId.setText(MyApplication.getUser().getId());
        userName.setText(MyApplication.getUser().getName());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                CommonUtils.ShowTips(MainActivity.this, "再点一次，退出");
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();
        int groupId = item.getGroupId();
        if (groupId == R.id.nav_group_fragment) {
            fragmentTransaction.replace(R.id.fragment_main, FragmentFactory.getInstance().getFragment(id)).commit();
        }
        switch (id) {
            case R.id.nav_zhihu :
                mToolbar.setTitle(R.string.title_zhihu);
                break;
            case R.id.nav_douban :
                mToolbar.setTitle(R.string.title_douban);
                break;
//            case R.id.nav_qiwen :
//                mToolbar.setTitle(R.string.title_qiwen);
//                break;
            case R.id.nav_tupian :
                mToolbar.setTitle(R.string.title_tupian);
                break;
            case R.id.nav_history :
                startActivity(HistoryActivity.class, false);
                break;
            case R.id.nav_save :
                startActivity(CollectActivity.class, false);
                break;
            case R.id.nav_setting :
                startActivity(SettingsActivity.class, false);
                break;
            case R.id.nav_about :
                startActivity(AboutActivity.class, false);
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageView) {
//            startActivity(LoginActivity.class, false);
            if (!MyApplication.isLogin) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            } else {
                CommonUtils.ShowTips(MainActivity.this, "已经登陆，进入个人主页!");
            }
        }
    }
}
