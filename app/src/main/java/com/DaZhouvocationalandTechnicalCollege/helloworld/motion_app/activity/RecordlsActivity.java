package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.R;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Record.DatePickerTimeline;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Record.MonthView;

import java.util.Calendar;

public class RecordlsActivity extends AppCompatActivity {
    private TextView time001,time002;
    private int year01,month01,day01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsjl);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        time001=findViewById(R.id.time001);
        time002=findViewById(R.id.time002);

        DatePickerTimeline timeline = findViewById(R.id.timeline);
        timeline.setDateLabelAdapter(new MonthView.DateLabelAdapter() {
            @Override
            public CharSequence getLabel(Calendar calendar, int index) {
                return Integer.toString(calendar.get(Calendar.MONTH) + 1) + "/" + (calendar.get(Calendar.YEAR) % 2000);
            }
        });
        timeline.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int index) {
                month=month+1;
                time001.setText(year+"-"+month+"-"+day);
                time002.setText(year+"-"+month+"-"+day);

            }
        });
        timeline.setFirstVisibleDate(2017, Calendar.JULY, 19);
        timeline.setLastVisibleDate(2021, Calendar.JULY, 19);
        Calendar calendar = Calendar.getInstance();


        year01 = calendar.get(Calendar.YEAR);
        month01 = calendar.get(Calendar.MONTH);
        day01 = calendar.get(Calendar.DAY_OF_MONTH);
        timeline.setSelectedDate(year01, month01, day01);



    }
}
