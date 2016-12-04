package com.kaka.ex;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by stre6 on 2016-12-02.
 */

public class Join extends AppCompatActivity {
    Button Ga, login, admin, btn7;
    EditText lo, pa;
    SQLiteDatabase db;
    DbHelpe helper;
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        startActivity(new Intent(this, Splash.class));
        setTitle("로그인 페이지");
        final DBHelper dbhe = new DBHelper(getApplicationContext(), "YAY.db", null, 1);
        txt1 = (TextView) findViewById(R.id.txt1);
        login = (Button) findViewById(R.id.lgbtn);
        lo = (EditText) findViewById(R.id.login);
        pa = (EditText) findViewById(R.id.password);
        Ga = (Button) findViewById(R.id.sigbtn);
        admin = (Button) findViewById(R.id.admin);
        btn7 = (Button) findViewById(R.id.btn7);
        txt1.setTypeface(Typeface.createFromAsset(getAssets(), "font/hanjokar.ttf"));
        Ga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Join.this, com.kaka.ex.Ga.class);
                startActivity(i);
            }
        });
        helper = new DbHelpe(Join.this, "We.db", null, 1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getReadableDatabase();
                String id = lo.getText().toString();
                String pw = pa.getText().toString();
                String db_id = null;
                String db_pa = null;
                Cursor c = db.rawQuery("SELECT * FROM We", null);
                while (c.moveToNext()) {
                    db_id = c.getString(c.getColumnIndex("id"));
                    db_pa = c.getString(c.getColumnIndex("pa"));
                }
                if (id.equals(db_id) && pw.equals(db_pa)) {
                    Toast.makeText(Join.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Join.this, MainActivity.class);
                    startActivity(i);
                } else if (TextUtils.isEmpty(id) || TextUtils.isEmpty(pw)) {
                    Toast.makeText(Join.this, "ID와 PW를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Join.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getReadableDatabase();
                String i = lo.getText().toString();
                String p = pa.getText().toString();
                String db_i = null;
                String db_p = null;
                Cursor e = db.rawQuery("SELECT * FROM We where id='fjscl13'", null);
                while (e.moveToNext()) {
                    db_i = e.getString(e.getColumnIndex("id"));
                    db_p = e.getString(e.getColumnIndex("pa"));
                }
                if (i.equals(db_i) && p.equals(db_p)) {
                    Toast.makeText(Join.this, "관리자 인증성공", Toast.LENGTH_SHORT).show();
                    Intent w = new Intent(Join.this, Admin.class);
                    startActivity(w);
                } else if (TextUtils.isEmpty(i) || TextUtils.isEmpty(p)) {
                    Toast.makeText(Join.this, "ID와 PW를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Join.this, "접근권한이 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent q = new Intent(Join.this, Yayak.class);
                startActivity(q);
            }
        });
    }
}
