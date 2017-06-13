package com.example.yiuhet.ktreader.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yiuhet on 2017/6/12.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public static final String HISTORY = "History";
    public static final String COLLECT = "Collect";
    public static final String UNSPLASH = "Unsplash";


    public static final String CREATE_TABLE = "create table %s ("
            + "id integer primary key autoincrement, "
            + "title text, "
            + "url text, "
            + "time text)";

    private Context mContext;

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format(CREATE_TABLE,HISTORY));
        db.execSQL(String.format(CREATE_TABLE,COLLECT));
        db.execSQL(String.format(CREATE_TABLE,UNSPLASH));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
