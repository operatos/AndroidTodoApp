package com.example.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityDetailViewer extends AppCompatActivity implements View.OnClickListener{

    private EditText etTitle;
    private EditText etCurDate;
    private EditText etDetail;

    private Button btnDelete;
    private Button btnSave;
    private  Button btnModify;

    private String keyTitle;
    private String keyDate;
    private String strDetail;

    private boolean mode_saveBtn;
    private final boolean BUTTON_MODE_SAVE = false;
    private final boolean BUTTON_MODE_OK = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_viewer);

        etTitle = findViewById(R.id.etTitle_dv);
        etCurDate = findViewById(R.id.etCurDate_dv);
        etDetail = findViewById(R.id.etDetail_dv);
        btnSave = findViewById(R.id.btnSave_dv);
        btnModify = findViewById(R.id.btnModify_dv);
        btnDelete = findViewById(R.id.btnRemove_dv);

        mode_saveBtn = BUTTON_MODE_OK;

        btnModify.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        etCurDate.setOnClickListener(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String detail = intent.getStringExtra("detail");
        String date = intent.getStringExtra("date");

        etTitle.setText(title);
        etDetail.setText(detail);
        etCurDate.setText(date);

        keyDate = date;
        keyTitle = title;
        strDetail = detail;


        etCurDate.setEnabled(false);
        setViewEnableMode(false);
    }


    @Override
    public void onClick(View view) {
        if(view == btnModify) {
            if(mode_saveBtn == BUTTON_MODE_OK) {
                setViewEnableMode(true);
                mode_saveBtn = BUTTON_MODE_SAVE;

                btnSave.setText("저장");
                btnModify.setText("취소");
            }
            else {
                etTitle.setText(keyTitle);
                etDetail.setText(strDetail);

                mode_saveBtn = BUTTON_MODE_OK;
                setViewEnableMode(false);
            }
        }
        else if(view == btnSave) {
            if (mode_saveBtn == BUTTON_MODE_SAVE) {
                String strTitle = etTitle.getText().toString();
                String strDetail = etDetail.getText().toString();
                String strDate = etCurDate.getText().toString();

                MainActivity.dbManager.update(keyTitle,keyDate,strTitle,strDate,strDetail);
                btnSave.setText("확인");
                btnModify.setText("수정");

                mode_saveBtn = BUTTON_MODE_OK;
                setViewEnableMode(false);
            }
            else {
                finish();
            }
        }
        else if(view == btnDelete) {
            MainActivity.dbManager.delete(etTitle.getText().toString(),etCurDate.getText().toString());
            finish();
        }
        else if(view ==etCurDate){
            Log.e("e-----e" , " test");
        }
    }

    public void setViewEnableMode(boolean enable) {
        etTitle.setEnabled(enable);
        etDetail.setEnabled(enable);
    }
}
