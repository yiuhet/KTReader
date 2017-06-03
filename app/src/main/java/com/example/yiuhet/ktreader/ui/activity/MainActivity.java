package com.example.yiuhet.ktreader.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.yiuhet.ktreader.BaseActivity;
import com.example.yiuhet.ktreader.R;
import com.example.yiuhet.ktreader.factory.FragmentFactory;
import com.example.yiuhet.ktreader.ui.fragment.ZhiHuFragment;
import com.example.yiuhet.ktreader.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiuhet on 17/5/18.
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fragment_main)
    FrameLayout fragmentMain;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.title_zhihu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        mNavView.setCheckedItem(R.id.nav_zhihu);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main, new ZhiHuFragment()).commit();

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
            case R.id.nav_qiwen :
                mToolbar.setTitle(R.string.title_qiwen);
                break;
            case R.id.nav_tupian :
                mToolbar.setTitle(R.string.title_tupian);
                break;
            case R.id.nav_history :
                break;
            case R.id.nav_save :
                break;
            case R.id.nav_setting :
                break;
            case R.id.nav_about :
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
