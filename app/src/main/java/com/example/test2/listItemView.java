package com.example.test2;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class listItemView extends LinearLayout implements CompoundButton.OnCheckedChangeListener,View.OnClickListener{
    private CheckBox cbStatus;
    private TextView tvTitle;
    private  ScheduleData data;
    private  Context parentContext;

    public listItemView(Context context,ScheduleData _data) {
        super(context);

        parentContext = context;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listitem, this, true);

        cbStatus = findViewById(R.id.cbStatus);
        tvTitle = findViewById(R.id.tvTitle);

        cbStatus.setChecked(_data.getCheckStatus());
        cbStatus.setOnCheckedChangeListener(this);
        tvTitle.setOnClickListener(this);

        data = _data;
        tvTitle.setText(_data.getTitle());
        Log.e("boolean : " , String.valueOf(_data.getCheckStatus()));
    }


    @Override
    public void onClick(View view) {
        //세부사항 보여주는 코드 삽입
        Intent intent = new Intent( parentContext, ActivityDetailViewer.class );

        intent.putExtra("title", data.getTitle());
        intent.putExtra("detail",data.getDetail());
        intent.putExtra("date", data.getDate());

        parentContext.startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Log.e("TTTTTTTTTTT","TTTTTTTTTTTTTTt");
        String strKeyDate = this.data.getDate();
        String strKeyTitle = this.data.getTitle();

        MainActivity.dbManager.update(strKeyTitle,strKeyDate,b);
    }
}