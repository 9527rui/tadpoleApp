package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sql_utils extends SQLiteOpenHelper {
    public sql_utils(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table name_pay(_id int primary key autoincrement,name varchar,data varchar,pay int)";
        db.execSQL(sql);
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
