package com.example.test2;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.Calendar;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Button btnAddSchedule;
    private  ListView lvSchedule;
    private  ListViewAdapter listAdapter;
    private  TextView tvToday;

    private  int year;
    private  int month;
    private  int day;

    private  Vector<ScheduleData> vCurDateData;
    static DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCurrentDate();
        dbManager =  new DBManager(getApplicationContext(), "ScheduleDB.db", null, 1);

        listAdapter = new ListViewAdapter();

        btnAddSchedule = findViewById(R.id.btnAddSchedule);
        lvSchedule = findViewById(R.id.lvSchedule);
        tvToday = findViewById(R.id.tvToday);


        tvToday.setOnClickListener(this);
        btnAddSchedule.setOnClickListener(this);

        vCurDateData = new Vector<ScheduleData>();
        loadData();

        lvSchedule.setAdapter(listAdapter);

        Log.e(" - test : " , " -- OnCreate() called" );
    }

    @Override
    public void onResume(){
        super.onResume();
        reloadView();
    }
    @Override
    public void onClick(View view) {
        if(view == btnAddSchedule) {
            Intent intent=new Intent(MainActivity.this, ActivityAddSchdule.class);
            intent.putExtra("year",year);
            intent.putExtra("month",month);
            intent.putExtra("day",day);
            startActivity(intent);
        }
        else if(view == tvToday) {
            DatePickerDialog dialog = new DatePickerDialog(this, this,year,month-1,day);
            dialog.show();
        }
    }
    void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH) + 1;
    }
    void reloadView() {
        loadData();

        String strCurDate = String.valueOf(year) + "년 " + String.valueOf(month) + "월 " +  String.valueOf(day) + " 일 일정" ;
        tvToday.setText(strCurDate);
    }
    boolean loadData() {
        //load data from local
        dbManager.getScheduleData(vCurDateData,year,month,day);

        listAdapter.setItems(vCurDateData);
        lvSchedule.setAdapter(listAdapter);

        Log.e("-- DBLoad test : ",String.valueOf(vCurDateData.size()));

        return true;
    }

    @Override
    public void onDateSet(DatePicker datePickerView, int _year, int _month, int _day) {
        year = _year;
        month = _month+1;
        day = _day;

        reloadView();

        Log.e("test : "," - OnDateSet() Called");
        //reload current date's data
    }
}
