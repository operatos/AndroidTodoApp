package com.example.test2;

/**
 * Created by 쑤헤이 on 2018-01-02.
 */

public class ScheduleData {
    private String strTitle;
    private String strDetail;

    private boolean isChecked;
    private int year;
    private int month;
    private int day;

    public ScheduleData(String title,String detail,int year,int month, int day,boolean isChecked) {
        strTitle = title;
        strDetail = detail;
        this.year = year;
        this.month = month;
        this.day = day;
        this.isChecked = isChecked;
    }

    public String getTitle() { return strTitle; }
    public String getDetail() { return strDetail; }
    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }
    public boolean getCheckStatus() { return isChecked;}

    public String getDate()
    {
        return String.format("%04d-%02d-%02d",year,month,day);
    }
}
