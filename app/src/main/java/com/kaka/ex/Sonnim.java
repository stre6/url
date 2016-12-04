package com.kaka.ex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by stre6 on 2016-12-04.
 */

public class Sonnim extends AppCompatActivity{
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonnim);
        tx=(TextView)findViewById(R.id.tx);
        setTitle("회원 확인");
        final DbHelpe dbHelpe=new DbHelpe(getApplicationContext(),"We.db",null,1);
        tx.setText(dbHelpe.getresul());
    }
}
