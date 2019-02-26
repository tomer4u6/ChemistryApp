//Version 1004
package com.example.chemistryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onClick_tableActivity(View view) {
        Intent t = new Intent(this,TableActivity.class);
        startActivity(t);

    }

    public void onClick_videosActivity(View view) {
        Intent t = new Intent(this,VideosActivity.class);
        startActivity(t);
    }

    public void onClick_mattersActivity(View view) {
    }

    public void onClick_alarmActivity(View view) {
    }
}
