package com.kaka.ex;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by stre6 on 2016-12-01.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "/mnt/sdcard/db/" + "YEY.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE YEY (a TEXT, b TEXT, c INTEGER,k TEXT,u INTEGER,d INTEGER,e INTEGER,f INTEGER,h INTEGER,j INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String insert(String a, String b, int c, String k, int u, int d, int e, int f, int h, int j) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO YEY VALUES('" + a + "','" + b + "'," + c + ",'" + k + "'," + u + "," + d + "," + e + "," + f + "," + h + "," + j + ");");

        db.close();
        return null;
    }

    public void delete() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM YEY");
        db.close();
    }

    public String getResult() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("SELECT * FROM YEY", null);
        while (cursor.moveToNext()) {
            result += "이름:" + cursor.getString(0)
                    + " 성별:" +
                    cursor.getString(1)
                    + " 학년:" +
                    cursor.getInt(2)
                    + " 대여실:" + cursor.getString(3)
                    + " 사용시간:" + cursor.getInt(4) + "시간"
                    + "\n" +
                    cursor.getInt(5)
                    + "년 " +
                    cursor.getInt(6)
                    + "월 " +
                    cursor.getInt(7)
                    + "일 " +
                    cursor.getInt(8)
                    + "시 " +
                    cursor.getInt(9)
                    + "분 " +
                    "예약됨" + "\n";
        }
        Log.d("aa", result);
        return result;
    }
}