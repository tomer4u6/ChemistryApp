//Version 1007
package com.example.chemistryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        Intent t = new Intent(this, VideosActivity.class);
        startActivity(t);
    }

    public void onClick_mattersActivity(View view) {
        Intent t = new Intent(this,mattersActivity.class);
        startActivity(t);
    }

    public void onClick_alarmActivity(View view) {
        Intent t = new Intent(this,notificationActivity.class);
        startActivity(t);
    }
}
