package com.example.yiuhet.ktreader.model.imp1;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.yiuhet.ktreader.app.Constant;
import com.example.yiuhet.ktreader.model.SplashModel;
import com.example.yiuhet.ktreader.presenter.listener.NetCallback;
import com.example.yiuhet.ktreader.utils.LogUtil;
import com.example.yiuhet.ktreader.utils.ShowApiUtils;
import com.example.yiuhet.ktreader.utils.Tuple;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yiuhet on 2017/5/28
 * <p>
 * 获取易源api励志语句的Model实现
 */

public class SplashModelImp1 implements SplashModel {

    private NetCallback callback; //网络请求回调接口

    @Override
    public void loadData(NetCallback callback) {
        this.callback = callback;
        new ShowAsyncTask().execute(ShowApiUtils.SAYING);
    }

    //使用基本的AsyncTask处理网络请求
    class ShowAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String url = Uri.parse(Constant.SHOWAPI_BASEURL + params[0])
                    .buildUpon()
                    .appendQueryParameter("showapi_appid", Constant.SHOWAPI_APPID)
                    .appendQueryParameter("showapi_sign", Constant.SHOWAPI_SECRT)
                    .build().toString();
            LogUtil.d("yiuhet", "Request URL : " + url);
            String jsonResult = getData(url);
            return parseJson(jsonResult).a;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                callback.onSuccess(s); //网络请求数据不为空时 回调成功方法。
            } else {
                callback.onError(); //回调失败方法。
            }
        }
    }

    /**
     * 从网络获取数据
     *
     * @param URL
     * @return
     */
    private String getData(String URL) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(URL);
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
        return sbf.toString();
    }

    /**
     * 解析json为string
     *
     * @param jsonResult
     * @return 由于返回英汉两组数据，这里返回二元组
     */
    private Tuple<String,String> parseJson(String jsonResult) {
        String resultEnglish = null;
        String resultChinese = null;
        try {
            JSONObject jsonBody = new JSONObject(jsonResult);
            JSONObject resBody = jsonBody.getJSONObject("showapi_res_body");
            JSONArray resDataArray = resBody.getJSONArray("data");
            JSONObject result = resDataArray.getJSONObject(0);
            resultEnglish = result.getString("english");
            resultChinese = result.getString("chinese");
            LogUtil.d("yiuhet", "Receice Data : " + resultChinese + "\n " + resultEnglish);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Tuple<>(resultChinese, resultEnglish);
    }
}
