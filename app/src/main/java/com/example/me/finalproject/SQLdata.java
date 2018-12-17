package com.example.me.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLdata extends SQLiteOpenHelper {
    private final static String DB="storelist.db";
    private final static String TB="storelisttb.tb";
    private final  static  int vs = 2;

    public SQLdata(Context context,String name,SQLiteDatabase.CursorFactory factory,int version) {
        //super(context, name, factory, version);
        super(context, DB, null, vs);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL="CREATE TABLE IF NOT EXISTS " +TB+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,_title VARCHAR(50))";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL = "DROP TABLE"+TB;
        db.execSQL(SQL);
    }
}
