package com.kaka.ex;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by stre6 on 2016-12-02.
 */

public class Ga extends AppCompatActivity {
    EditText id, pa, name, cattle, gra, phone;
    Button btn1, btn2;
    TextView txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ga);
        setTitle("회원가입 페이지");
        txt2 = (TextView) findViewById(R.id.txt2);
        id = (EditText) findViewById(R.id.id);
        pa = (EditText) findViewById(R.id.pa);
        name = (EditText) findViewById(R.id.name);
        cattle = (EditText) findViewById(R.id.cattle);
        gra = (EditText) findViewById(R.id.gra);
        phone = (EditText) findViewById(R.id.phone);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "font/hanjokar.ttf"));

        final DbHelpe dbHelper = new DbHelpe(getApplicationContext(), "We.db", null, 1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i = id.getText().toString();
                String p = pa.getText().toString();
                String n = name.getText().toString();
                String c = cattle.getText().toString();
                int g = Integer.parseInt(gra.getText().toString());
                String ph = phone.getText().toString();
                dbHelper.insert1(i, p, n, c, g, ph);
                Log.d("aa", dbHelper.getresul());
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id.setText("");
                pa.setText("");
                name.setText("");
                cattle.setText("");
                gra.setText("");
                phone.setText("");
            }
        });
    }
}
