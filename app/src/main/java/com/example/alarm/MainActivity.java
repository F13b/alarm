package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button setTimer;
    Calendar dateTime = Calendar.getInstance();
    int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTimer = findViewById(R.id.setBtn);

        setTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(MainActivity.this,t,
                        dateTime.get(Calendar.HOUR_OF_DAY),
                        dateTime.get(Calendar.MINUTE), true).show();


            }
        });
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            int curtime = dateTime.get(Calendar.HOUR_OF_DAY) * 3600 + dateTime.get(Calendar.MINUTE) * 60;
            dateTime.set(Calendar.HOUR_OF_DAY,i);
            dateTime.set(Calendar.MINUTE,i1);

            seconds = i * 3600 + i1 * 60;
            int timer = seconds - curtime;
            Intent intent = new Intent(MainActivity.this, Alarm.class);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + timer * 1000L, PendingIntent.getBroadcast(getApplicationContext(),0,intent,0));
        }
    };
}