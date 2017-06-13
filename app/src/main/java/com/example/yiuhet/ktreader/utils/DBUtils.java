package com.example.yiuhet.ktreader.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yiuhet.ktreader.model.entity.HistoryCollect;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by yiuhet on 2017/6/12.
 */

public class DBUtils {
    private static DBUtils sDBUtis;
    private SQLiteDatabase mSQLiteDatabase;

    private DBUtils(Context context) {
        mSQLiteDatabase = new MyDataBaseHelper(context, "KTReader.db", null, 1).getWritableDatabase();
    }

    public static DBUtils getInstence(Context context) {
        if (sDBUtis == null) {
            synchronized (DBUtils.class) {
                if (sDBUtis == null) {
                    sDBUtis = new DBUtils(context);
                }
            }
        }
        return sDBUtis;
    }

    public void insertData(String table, String title, String url) {

        //每个表储存数量上限为20条，超过就删除最旧的记录
        Cursor cursor = mSQLiteDatabase.query(table, null, null, null, null, null, "id asc");
        if (cursor.getCount() > 20 && cursor.moveToNext()) {
            mSQLiteDatabase.delete(table, "id=?", new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex("id")))});
        }
        cursor.close();
        //插入数据
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("url", url);
        Calendar c = Calendar.getInstance();
        String time = String.format("%d月%d日",c.get(Calendar.MONTH)+1,c.get(Calendar.DATE));
        contentValues.put("time", time);

        mSQLiteDatabase.insertWithOnConflict(table, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public boolean isExist(String table, String url) {
        boolean isRead = false;
        Cursor cursor = mSQLiteDatabase.query(table, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex("url")).equals(url)) {
                isRead = true;
                break;
            }
        }
        cursor.close();
        return isRead;
    }

    public void deleteDataCollect(String table, String id) {
        mSQLiteDatabase.delete(table,"url=?", new String[] {id});
    }

    public List<HistoryCollect> getData(String table) {

        List<HistoryCollect> historyCollectList = new ArrayList<>();

        Cursor cursor = mSQLiteDatabase.query(table, null, null, null, null, null, "id desc");
        while (cursor.moveToNext()) {
            String title;
            String url;
            String time;
            title = cursor.getString(cursor.getColumnIndex("title"));
            url = cursor.getString(cursor.getColumnIndex("url"));
            time = cursor.getString(cursor.getColumnIndex("time"));
            HistoryCollect historyCollect = new HistoryCollect(title, url, time);
            historyCollectList.add(historyCollect);
        }
        cursor.close();
        return historyCollectList;
    }

    public void clearHistory() {
        mSQLiteDatabase.delete(MyDataBaseHelper.HISTORY, null, null);
    }
}
