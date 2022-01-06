package com.example.today;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String db_name = "history.db";
    public final static String db_table = "History";
    public final static String title = "title";
    public final static String desc = "detail";

    public DatabaseHelper(Context context) {
        super(context, db_name, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+db_table+"(id TEXT primary key, "+title+" text, "+desc+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+db_table+"");
        onCreate(db);
    }

    public void tambah_data(String id, String title, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put(DatabaseHelper.title, title);
        contentValues.put(DatabaseHelper.desc, desc);

        db.insert(db_table, null, contentValues);
    }

    public Cursor baca_data() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from "+db_table+"", null);
        return cur;
    }

    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(db_table,null,new String[]{});
    }
}
