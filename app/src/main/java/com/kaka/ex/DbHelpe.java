package com.kaka.ex;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stre6 on 2016-12-04.
 */

public class DbHelpe extends SQLiteOpenHelper {
    public DbHelpe(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE We(id TEXT,pa TEXT,name TEXT,b TEXT,c INTEGER,i TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public String insert1(String id, String pa, String name, String qa, int ik, String i) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO We VALUES('" + id + "','" + pa + "','" + name + "','" + qa + "'," + ik + ",'" + i + "')");
        db.close();
        return null;
    }

    public void delete() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM We");
        sqLiteDatabase.close();
    }

    public String getresul() {
        SQLiteDatabase db = getWritableDatabase();
        String resul = "";
        Cursor cursor1 = db.rawQuery("SELECT * FROM We", null);
        while (cursor1.moveToNext()) {
            resul += "ID:" + cursor1.getString(0) + " PASSWORD:" + cursor1.getString(1) + " 이름:" + cursor1.getString(2)
                    + " 성별:" + cursor1.getString(3)
                    + " 학년:" + cursor1.getInt(4)
                    + " 전화번호:" + cursor1.getString(5) + "\n";
        }
        return resul;
    }
}
