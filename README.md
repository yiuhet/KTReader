# Android练手小项目（KTReader）基于mvp架构（一）
## 前言
这是一个练手的小项目，权当作为Android学习的总结，项目里会有相同功能的不同实现（从基本的代码到热门的开源项目实现，比如网络模块会分别使用最基本的HttpURLConnection和retrofit2+OKhttp3实现），如果你在阅读中发现了错误或是可以改进之处，欢迎指正，谢谢。

GIthub地址: [https://github.com/yiuhet/KTReader](https://github.com/yiuhet/KTReader)

## 目录结构：
￼![包结构](https://github.com/yiuhet/KTReader/blob/master/img/package.PNG)

## 创建基类
- BaseActivity
- BasePresenter
- MVPBaseActivity
- BaseFragment

__添加依赖 (之后会逐步添加)__
基类创建会用到[__ButterKnife__](http://blog.csdn.net/nealkafuly/article/details/52526254)
```
compile 'com.jakewharton:butterknife:8.6.0'
annotationProcessor 'com.jakewharton:butterknife-compiler:8.6.0
```


### 1. BaseActivity
Activity基类里定义了以下方法（方法的作用如名所示）:
- getLayoutRes()（抽象） 
- showProgress(String msg)
- hideProgress()
- startActivity(Class activity)
- toast(String msg)

```
public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        //android 5.0 以上设置直接状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }
    protected void showProgress(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
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
    protected void startActivity(Class activity) {
        startActivity(activity, true);
    }
    protected void startActivity(Class activity, boolean finish) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        if (finish) {
            finish();
        }
    }
    protected abstract int getLayoutRes();
    protected void toast(String msg) {
        CommonUtils.ShowTips(this, msg); //方法类里的一个方法，封装了Toast,后文会提到.
    }
}
```
### 2. BasePresenter
BasePresenter有四个方法：
- attachView(T view) —— 建立关联
- getView() —— 获取View
- isViewAttached() —— 判断是否与View建立了关联
- detachView() —— 解除关联

Presenter对通过泛型传进来的VIew持有弱引用，防止内存泄漏。
```
public abstract class BasePresenter<T> {

    protected Reference<T> mViewRef; //View 接口类型的弱引用

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);  //建立关联
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
```
### 3. MVPBaseActivity
MVPBaseActivity继承BaseActivity，含有两个泛型参数：
- 第一个是View接口类型 V
- 第二个是Presenter的具体类型。T
```
public abstract class MVPBaseActivity<V, T extends BasePresenter<V>> extends BaseActivity {
    protected T mPresenter;
    
    protected abstract T createPresenter();
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
```
### 4. BaseFragment
泛型参数与MVPBaseActivity一样
- 第一个是View接口类型 V
- 第二个是Presenter的具体类型。T
```
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    protected T mPresenter;
    private ProgressDialog mProgressDialog;
    
    protected abstract T createPresenter();
    
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
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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
}
```

## 创建一个漂亮的启动界面（Splash Screen）
这个项目的启动界面有以下特点:
-  启动App时不会出现白屏。
-  背景图有放大的动画效果。
-  从网上拉取一句励志语录并加载出来。

### 1. 不会出现白屏
[__白屏出现的原因：__](https://www.zhihu.com/question/35336192)
>Android是先渲染window再渲染activity，而业务逻辑(比如初始化用户信息等)是在activity里，这就会导致渲染出activity的布局变慢,如果不做任何操作，这时候在activity的页面渲染出来前就会有个黑色或者白色的状态。

所以严格来说，我并不是消除了白屏，而是设置window的background替换了那个阶段的显示，实际上没有加速，但从用户角度确实提高了感知。

#### 下面附上代码：
styles.xml:
```
    <style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>
    <style name="SplashTheme" parent="AppTheme.NoActionBar">
        <item name="android:windowBackground">@drawable/splash_layers</item>
    </style>
```
AndroidManifest.xml:
```
<activity android:name=".ui.activity.SplashActivity"
            android:theme="@style/SplashTheme">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
```
splash_layers.xml:
```
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item>
        <bitmap android:gravity="fill" android:src="@drawable/bg_splash_default" />
    </item>
    <item>
        <shape>
            <gradient
                android:angle="90"
                android:startColor="@android:color/black"
                android:endColor="@android:color/transparent"
                />
        </shape>
    </item>
</layer-list>
```
activity_splash.xml:
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/iv_show_pic"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/splash_layers"
        android:scaleType="fitXY"/>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_marginBottom="100dp"
        android:gravity="center"
        android:text="KTReader"
        android:textColor="@android:color/white"
        android:textSize="23sp"/>

    <TextView
        android:id="@+id/tv_show_saying"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_marginBottom="60dp"
        android:layout_centerInParent="true"
        android:textColor="@android:color/white" />
</RelativeLayout>
```
### 2. 放大的动画效果
SplashActivity里定义一个startAnim(Class act)方法：
```
private void startAnim(final Class act) {
        //传入一个ImageView对象,围绕X,Y进行2D缩放,由原始的大小方法到原来的1.15倍
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIvShowPic, "scaleX", 1f, 1.15f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIvShowPic, "scaleY", 1f, 1.15f);
        //多个动画的协同工作
        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000).play(animatorX).with(animatorY);
        set.start();
        //对动画的监听,动画结束后立马跳转到主页面上
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(act); //基类里的方法
            }
        });
    }
```

### 3.  从网上拉取一句励志语录并加载出来:

__启动界面里的数据通信是用基本的HttpURLConnection类实现的，数据解析也是用JSONObject类实现的。__

励志语录是易源数据由提供的,返回数据格式如下:
>
```
{
    "showapi_res_error": "",
    "showapi_res_code": 0,
    "showapi_res_body": {
        "ret_code": 0,
        "ret_message": "Success",
        "data": [
            {
                "english": "Let the right one in. Let the old dreams die. Let the wrong ones go.",
                "chinese": "让适合的人走进你的生活吧，让旧梦逝去吧，让不合适的那个离开吧。"
            }
        ]
    }
}
```
>

__上代码:__
View层 —— SplashView :
```
public interface SplashView {

    void onGetSayingSuccess(String string);

    void onGetSayingFailed();
}
```

Model层 —— SplashPresenter:
```
public interface SplashPresenter {
    void loadSaying();
}
```
加载数据的方法 —— ShowApiUtils：

```
public class ShowApiUtils {

    private final static String SHOWAPI_APPID = "38473";
    private final static String SHOWAPI_SECRT= "cb7ffb4054924ba2b2933a6834069bb1";
    public final static String BING_PIC = "1377-1";
    public final static String SAYING = "1211-1";  //励志语录的api
    // 解析url网址
    public static String getApiRequest(String address) {
        String url = Uri.parse("http://route.showapi.com/" + address)
                    .buildUpon()
                    .appendQueryParameter("showapi_appid", SHOWAPI_APPID)
                    .appendQueryParameter("showapi_sign", SHOWAPI_SECRT)
                    .build().toString();
        return url;
    }
    //获取数据，使用HttpURLConnection实现
    public static String getData(String httpUrl) {
        String jsonResult ;
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(getApiRequest(httpUrl));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonResult =sbf.toString();
        return jsonResult;
    }
    //从返回的Json数据解析出结果
    public static String parseJsonFromSaying(String jsonResult) {
        String resultEnglish = null;
        String resultChinese = null;
        try {
            JSONObject jsonBody = new JSONObject(jsonResult);
            JSONObject resBody = jsonBody.getJSONObject("showapi_res_body");
            JSONArray resDataArray = resBody.getJSONArray("data");
            JSONObject result = resDataArray.getJSONObject(0);
            resultEnglish = result.getString("english");
            resultChinese = result.getString("chinese");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultChinese;
    }
}
```

Presenter层 —— SplashPresenterImp1:
```
public class SplashPresenterImp1 extends BasePresenter<SplashView> implements SplashPresenter{

    private SplashView mSplashView;

    public SplashPresenterImp1(SplashView splashView) {
        mSplashView = splashView;
    }

    @Override
    public void loadSaying() {
        new ShowAsyncTask().execute(ShowApiUtils.SAYING);
    }

    //使用基本的AsyncTask处理网络请求
    class ShowAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return ShowApiUtils.parseJsonFromSaying(ShowApiUtils.getData(params[0]));
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                mSplashView.onGetSayingSuccess(s);
            } else {
                mSplashView.onGetSayingFailed();
            }
        }
    }
}
```
V层 —— SplashActivity：
```
public class SplashActivity extends MVPBaseActivity<SplashView, SplashPresenterImp1> implements SplashView {

    @BindView(R.id.iv_show_pic)
    ImageView mIvShowPic;
    @BindView(R.id.tv_show_saying)
    TextView mTvShowSaying;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected SplashPresenterImp1 createPresenter() {
        return new SplashPresenterImp1(this);
    }
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //保持全屏窗口
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mPresenter.loadSaying();
        startAnim(MainActivity.class);
    }

    private void startAnim(final Class act) {
        //传入一个ImageView对象,围绕X,Y进行2D缩放,由原始的大小方法到原来的1.15倍
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIvShowPic, "scaleX", 1f, 1.15f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIvShowPic, "scaleY", 1f, 1.15f);
        //多个动画的协同工作
        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000).play(animatorX).with(animatorY);
        set.start();
        //对动画的监听,动画结束后立马跳转到主页面上
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(act);
            }
        });
    }
    
    @Override
    public void onGetSayingSuccess(String string) {
        mTvShowSaying.setText(string);
    }

    @Override
    public void onGetSayingFailed() {
        mTvShowSaying.setText(getString(R.string.default_saying));
    }
}
```
