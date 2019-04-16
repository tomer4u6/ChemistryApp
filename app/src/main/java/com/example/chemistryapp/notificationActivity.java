package com.example.chemistryapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class notificationActivity extends AppCompatActivity {

    AlertDialog.Builder adb;
    AlertDialog ad;
    TimePicker timePicker;
    AlarmManager alarm;
    int hour = 14;
    int minute = 0;
    ToggleButton notifyTb;
    PendingIntent alarmIntent;
    Button timeBtn;
    String chosenRingtone;
    String hourString,minString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notifyTb = (ToggleButton)findViewById(R.id.notifyTb);
        timeBtn = (Button)findViewById(R.id.timeBtn);

        SharedPreferences settings = getSharedPreferences("com.example.chemistryapp",MODE_PRIVATE);

        boolean toggleState = settings.getBoolean("toggle_status",false);

        hour = settings.getInt("hour",14);
        minute = settings.getInt("minute",0);

        chosenRingtone = settings.getString("sound","");

        if(minute<10){
            minString = "0"+Integer.toString(minute);
        }
        else{
            minString = Integer.toString(minute);
        }

        if(hour<10){
            hourString = "0"+Integer.toString(hour);
        }
        else{
            hourString = Integer.toString(hour);

        }

        timeBtn.setText("קביעת שעות לחזרה - "+hourString+":"+minString);

        if(toggleState){
            notifyTb.setChecked(true);
        }
        else{
            notifyTb.setChecked(false);
        }

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void chooseTime(View view) {
        timePicker = new TimePicker(this);
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Set time for notifications");
        adb.setCancelable(false);
        adb.setView(timePicker);
        adb.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ad.cancel();
            }
        });
        adb.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
                SharedPreferences.Editor editor =
                        getSharedPreferences("com.example.chemistryapp",MODE_PRIVATE).edit();
                editor.putInt("hour",hour);
                editor.commit();
                editor.putInt("minute",minute);
                editor.commit();
                enableAlarm(null);
                ad.dismiss();
            }
        });
        ad = adb.create();
        ad.show();
    }


    public void enableAlarm(View view) {

        if(notifyTb.isChecked()) {

            SharedPreferences.Editor editor =
                    getSharedPreferences("com.example.chemistryapp",MODE_PRIVATE).edit();
            editor.putBoolean("toggle_status",true);
            editor.commit();

            Intent t = new Intent(notificationActivity.this, AlarmReceiver.class);
            t.putExtra("notificationId", 1);
            t.putExtra("appMessage", "הגיע הזמן ללמוד קצת כימיה");

            if(chosenRingtone==null){
                t.putExtra("soundUri","None");
            }
            else {
                t.putExtra("soundUri",chosenRingtone);
            }


            alarmIntent = PendingIntent.getBroadcast(notificationActivity.this, 0,
                    t, PendingIntent.FLAG_UPDATE_CURRENT);

            alarm = (AlarmManager) getSystemService(ALARM_SERVICE);


            Calendar startTime = Calendar.getInstance();
            startTime.set(Calendar.HOUR_OF_DAY, hour);
            startTime.set(Calendar.MINUTE, minute);
            startTime.set(Calendar.SECOND,10);

            alarm.setRepeating(AlarmManager.RTC_WAKEUP,
                    startTime.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY,
                    alarmIntent);
            if(minute<10){
                minString = "0"+Integer.toString(minute);
            }
            else{
                minString = Integer.toString(minute);
            }

            if(hour<10){
                hourString = "0"+Integer.toString(hour);
            }
            else{
                hourString = Integer.toString(hour);

            }
            timeBtn.setText("קביעת שעות לחזרה - "+hourString+":"+minString);

            Toast.makeText(this, "התראות מאופשרות ונקבעו לשעה "+hourString+":"+minString, Toast.LENGTH_SHORT).show();
        }
        else{

            SharedPreferences.Editor editor =
                    getSharedPreferences("com.example.chemistryapp",MODE_PRIVATE).edit();
            editor.putBoolean("toggle_status",false);
            editor.commit();

            if(alarm != null){
                alarm.cancel(alarmIntent);
            }

            if(minute<10){
                minString = "0"+Integer.toString(minute);
            }
            else{
                minString = Integer.toString(minute);
            }

            if(hour<10){
                hourString = "0"+Integer.toString(hour);
            }
            else{
                hourString = Integer.toString(hour);

            }

            timeBtn.setText("קביעת שעות לחזרה - "+hourString+":"+minString);
            Toast.makeText(this, "התראות כבויות", Toast.LENGTH_SHORT).show();
        }
    }

    public void chooseSound(View view) {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
        this.startActivityForResult(intent, 5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 5)
        {
            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

            if (uri != null)
            {
                this.chosenRingtone = uri.toString();
            }
            else
            {
                this.chosenRingtone = null;
            }
        }
        SharedPreferences.Editor editor =
                getSharedPreferences("com.example.chemistryapp",MODE_PRIVATE).edit();
        editor.putString("sound",chosenRingtone);
        editor.commit();
        enableAlarm(null);
    }

    public void setDefault(View view) {
        hour = 10;
        minute = 0;
        chosenRingtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION).toString();

        SharedPreferences.Editor editor =
                getSharedPreferences("com.example.chemistryapp",MODE_PRIVATE).edit();

        editor.putString("sound",chosenRingtone);
        editor.commit();

        editor.putInt("hour",hour);
        editor.commit();

        editor.putInt("minute",minute);
        editor.commit();


        enableAlarm(null);
    }
}
