package com.example.yiuhet.ktreader.utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

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
 * Created by yiuhet on 2017/5/19.
 */

public class ShowApiUtils {

    private final static String SHOWAPI_APPID = "38473";
    private final static String SHOWAPI_SECRT= "cb7ffb4054924ba2b2933a6834069bb1";
    public final static String BING_PIC = "1377-1";
    public final static String SAYING = "1211-1";

    public static String getApiRequest(String address) {
        String url = Uri.parse("http://route.showapi.com/" + address)
                    .buildUpon()
                    .appendQueryParameter("showapi_appid", SHOWAPI_APPID)
                    .appendQueryParameter("showapi_sign", SHOWAPI_SECRT)
                    .build().toString();
        return url;
    }

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
