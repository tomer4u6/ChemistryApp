//Version 1000
package com.example.chemistryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_tableActivity,btn_videosActivity,btn_mattersActivity,btn_alarmActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_tableActivity = (Button)findViewById(R.id.btn_tableActivity);
        btn_videosActivity = (Button)findViewById(R.id.btn_videosActivity);
        btn_mattersActivity = (Button)findViewById(R.id.btn_mattersActivity);
        btn_alarmActivity = (Button)findViewById(R.id.btn_alarmActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
