package com.kaka.ex;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by stre6 on 2016-12-04.
 */

public class Yayak extends AppCompatActivity {
    TextView te;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yayak);
        te=(TextView)findViewById(R.id.te);
        setTitle("예약 확인");
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "YEY.db", null, 1);
        te.setText(dbHelper.getResult());
        te.setTypeface(Typeface.createFromAsset(getAssets(), "font/hanjokar.ttf"));
    }
}
