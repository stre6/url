package com.kaka.ex;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements InputFilter {
    Chronometer chrono;
    Button btnStart, btnEnd;
    DatePicker calView;
    RadioButton rdoCal, rdoTime, rda, r1, r2, r3;
    TimePicker tPicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute, nae, name, sil, sayong,grade;
    EditText ede;
    RadioGroup rg;
    SQLiteDatabase dba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "YEY.db", null, 1);
        final DbHelpe dbHelpe = new DbHelpe(getApplicationContext(), "We.db", null, 1);
        final String result = dbHelper.getResult();
        setTitle("인평 세미나 예약");
        btnEnd = (Button) findViewById(R.id.btnEnd);
        rdoCal = (RadioButton) findViewById(R.id.rdoCal);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);
        tPicker = (TimePicker) findViewById(R.id.timePicker1);
        calView = (DatePicker) findViewById(R.id.dp1);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvMonth = (TextView) findViewById(R.id.tvMv);
        tvDay = (TextView) findViewById(R.id.tvM);
        tvHour = (TextView) findViewById(R.id.tvMp);
        tvMinute = (TextView) findViewById(R.id.tvMw);
        nae = (TextView) findViewById(R.id.jul);
        name = (TextView) findViewById(R.id.nam);
        rda = (RadioButton) findViewById(R.id.rda);
        tPicker.setVisibility(View.INVISIBLE);
        calView.setVisibility(View.INVISIBLE);
        r1 = (RadioButton) findViewById(R.id.rb1);
        r2 = (RadioButton) findViewById(R.id.rb2);
        r3 = (RadioButton) findViewById(R.id.rb3);
        r1.setVisibility(View.INVISIBLE);
        r2.setVisibility(View.INVISIBLE);
        r3.setVisibility(View.INVISIBLE);
        sil = (TextView) findViewById(R.id.sil);
        ede = (EditText) findViewById(R.id.ede);
        ede.setVisibility(View.INVISIBLE);
        ede.setFilters(new InputFilter[]{new MainActivity()});
        sayong = (TextView) findViewById(R.id.sayong);
        grade=(TextView)findViewById(R.id.grade);

        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE);
                ede.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.VISIBLE);
                r1.setVisibility(View.INVISIBLE);
                r2.setVisibility(View.INVISIBLE);
                r3.setVisibility(View.INVISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.VISIBLE);
                ede.setVisibility(View.VISIBLE);
                calView.setVisibility(View.INVISIBLE);
                r1.setVisibility(View.INVISIBLE);
                r2.setVisibility(View.INVISIBLE);
                r3.setVisibility(View.INVISIBLE);
            }
        });
        rda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.INVISIBLE);
                ede.setVisibility(View.INVISIBLE);
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
            }
        });

        calView.init(calView.getYear(), calView.getMonth(), calView.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvYear.setText(String.format("%d", year));
                        tvMonth.setText(String.format("%d", monthOfYear + 1));
                        tvDay.setText(String.format("%d", dayOfMonth));
                    }
                });
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sil.setText("세미나실");
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sil.setText("화상회의실");
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sil.setText("토론실");
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = "";
                String gette = String.valueOf(Integer.parseInt(ede.getText().toString()));
                if (gette.getBytes().length <= 0) {
                    Toast.makeText(MainActivity.this, "사용시간을 입력하세요", Toast.LENGTH_SHORT).show();
                    e = "";
                } else {
                    e = gette;
                }
                String year = String.format("%d", calView.getYear());
                String month = String.format("%d", calView.getMonth() + 1);
                String day = String.format("%d", calView.getDayOfMonth());
                tvYear.setText(Integer.toString(Integer.parseInt(year)));
                tvMonth.setText(Integer.toString(Integer.parseInt(month)));
                tvDay.setText(Integer.toString(Integer.parseInt(day)));
                tvHour.setText(Integer.toString(tPicker.getCurrentHour()));
                tvMinute.setText(Integer.toString(tPicker.getCurrentMinute()));

                sayong.setText(e);
                int ty = Integer.parseInt(tvYear.getText().toString());
                int tm = Integer.parseInt(tvMonth.getText().toString());
                int td = Integer.parseInt(tvDay.getText().toString());
                int th = Integer.parseInt(tvHour.getText().toString());
                int tM = Integer.parseInt(tvMinute.getText().toString());
                String si = sil.getText().toString();
                String na = null;
                dba = dbHelpe.getReadableDatabase();
                String me = null;
                String gr = String.valueOf("");
                Cursor c = dba.rawQuery("SELECT * FROM We", null);
                while (c.moveToNext()) {
                    me = c.getString(c.getColumnIndex("name"));
                    na = c.getString(c.getColumnIndex("b"));
                    gr = String.valueOf(Integer.parseInt(c.getString(c.getColumnIndex("c"))));
                }
                name.setText(me);
                nae.setText(na);
                grade.setText(gr);
                int sy = Integer.parseInt(ede.getText().toString());

                dbHelper.insert(me, na, Integer.parseInt(gr), si, sy, ty, tm, td, th, tM);
                Log.d("aa", dbHelper.getResult());

            }
        });
    }


    @Override
    public CharSequence filter(CharSequence cs, int start, int end, Spanned dest, int dstart, int dend) {
        Pattern ps = Pattern.compile("^[1-3]+$");
        if (cs.equals("") || ps.matcher(cs).matches()) {
            return cs;
        }
        return "";
    }
}