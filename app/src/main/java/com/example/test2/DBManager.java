package com.example.test2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

/**
 * Created by 쑤헤이 on 2018-01-02.
 */

public class DBManager extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}


    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCHEDULEDATA (title TEXT, detail TEXT, scheduleDate DATE,isChecked BIT, PRIMARY KEY (scheduleDate,title) );");
        //db.execSQL("CREATE TABLE SCHEDULEDATA (title TEXT, detail TEXT, year INTEGER, month INTEGER , day INTEGER, PRIMARY KEY (year,month,day,title) );");
    }

    public void insert(String title,String detail,int year,int month ,int day) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        String strDate = String.format("%04d-%02d-%02d", year,month,day);
        db.execSQL("INSERT INTO SCHEDULEDATA VALUES('" +title + "', '" + detail + "', '" + strDate + "' ,'0');");
        db.close();
    }

    public void update(String key_title,String key_date,String title,String date,String detail) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE SCHEDULEDATA SET detail='" + detail + "', title='" + title + "' WHERE scheduleDate='" + key_date + "' and title='" + key_title + "' ;");
        db.close();
    }
    public void update(String key_title,String key_date,boolean isChecked) {
        SQLiteDatabase db = getWritableDatabase();
        String strIsChecked;
        if(isChecked)
            strIsChecked = "1";
        else
            strIsChecked = "0";
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE SCHEDULEDATA SET isChecked='"+ strIsChecked + "' WHERE scheduleDate='" + key_date + "' and title='" + key_title + "' ;");
        db.close();
    }
    public void delete(String title,String date) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제

        db.execSQL("DELETE FROM SCHEDULEDATA WHERE scheduleDate='" + date + "' and title='" + title + "';");
        db.close();
    }

    public void getScheduleData(Vector<ScheduleData> vData, int _year,int _month , int _day) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String strDate = String.format("%04d-%02d-%02d", _year,_month,_day);

        vData.removeAllElements();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM SCHEDULEDATA WHERE scheduleDate ='" + strDate + "';", null);
        while (cursor.moveToNext()) {
            String title =  cursor.getString(0);
            String detail =  cursor.getString(1);
            String[] date =  cursor.getString(2).split("-");
            int isChecked = cursor.getInt(3);

            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);

            boolean bIsChecked;
            if(isChecked != 0)
                bIsChecked = true;
            else
                bIsChecked = false;

            ScheduleData newData = new ScheduleData(title,detail,year,month,day,bIsChecked);
            vData.add(newData);
        }

    }
}
