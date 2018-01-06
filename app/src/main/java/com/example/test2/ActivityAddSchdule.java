package com.example.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ActivityAddSchdule extends AppCompatActivity implements View.OnClickListener {

    private EditText etTitle;
    private EditText etDetail;
    private Button btnAddSchedule;
    private EditText etCurDate;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schdule);

        Intent intent = getIntent();
        year = intent.getIntExtra("year",0);
        month = intent.getIntExtra("month",0);
        day = intent.getIntExtra("day",0);
        //생성자에 넣으면 오류남.


        etDetail = findViewById(R.id.etDetail);
        etTitle = findViewById(R.id.etTitle);
        etCurDate = findViewById(R.id.etCurDate);

        btnAddSchedule = findViewById(R.id.btnAdd);
        btnAddSchedule.setOnClickListener(this);

        String strCurDate = String.valueOf(year) + "년 " + String.valueOf(month) + "월 " +  String.valueOf(day) + " 일 일정" ;
        etCurDate.setText(strCurDate);
        etCurDate.setEnabled(false);

    }

    @Override
    public void onClick(View view) {
        if(view == btnAddSchedule){
            String strTitle = etTitle.getText().toString();
            String strDetail = etDetail.getText().toString();

            ScheduleData newData = new ScheduleData(strTitle,strDetail,year,month,day,false);
            MainActivity.dbManager.insert(newData.getTitle(),newData.getDetail(),newData.getYear(),newData.getMonth(),newData.getDay());

            finish();
        }
    }
}
