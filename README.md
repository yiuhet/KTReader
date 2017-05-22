#Android练手小项目（KTReader）基于mvp架构（一）
#前言
这是一个练手的小项目，权当作为Android学习的总结，项目里会有相同功能的不同实现（从基本的代码到热门的开源项目实现，比如网络模块会分别使用最基本的HttpURLConnection和retrofit2+OKhttp3实现），如果你在阅读中发现了错误或是可以改进之处，欢迎指正，谢谢。
GIthub地址: [https://github.com/yiuhet/KTReader](https://github.com/yiuhet/KTReader)

#目录结构：
￼

#创建基类
- BaseActivity
- BaseFragment
- BasePresenter
- MVPBaseActivity

##1. BaseActivity
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
        CommonUtils.ShowTips(this, msg);
    }
}
```
##2. BaseFragment
