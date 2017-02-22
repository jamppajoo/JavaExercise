package com.jumuvirma.javaexercise;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;
import java.util.StringTokenizer;

public class DateActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
    }
    protected void saveClick(View view)
    {
        Intent intent = new Intent();

        EditText editText = (EditText) findViewById(R.id.notificationText);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        String message = editText.getText().toString();
        intent.putExtra("message",message);
        intent.putExtra("day",day);
        intent.putExtra("month",month);
        intent.putExtra("year",year);

        setResult(MainActivity.RESULT_OK,intent);
        finish();

    }
}
