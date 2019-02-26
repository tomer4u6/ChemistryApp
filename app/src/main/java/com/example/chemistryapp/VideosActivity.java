package com.example.chemistryapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class VideosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

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

    public void openVideo1(View view) {
        Uri uri = Uri.parse("https://www.youtube.com/watch?v=gmlmg3XU6A0");
        Intent t = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(t);
    }

    public void openVideo2(View view) {
        Uri uri = Uri.parse("https://www.youtube.com/watch?v=Lk3Q2RZ2hRM");
        Intent t = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(t);
    }

    public void openVideo3(View view) {
        Uri uri = Uri.parse("https://www.youtube.com/watch?v=1TDwfM2Gdgg");
        Intent t = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(t);
    }

    public void openVideo4(View view) {
        Uri uri = Uri.parse("https://www.youtube.com/watch?v=SiN1KA1e8r8");
        Intent t = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(t);
    }

    public void openVideo5(View view) {
        Uri uri = Uri.parse("https://www.youtube.com/watch?v=rz4Dd1I_fX0");
        Intent t = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(t);
    }

    public void openVideo6(View view) {
        Uri uri = Uri.parse("https://www.youtube.com/watch?v=o1Ov3xk1F7g");
        Intent t = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(t);
    }
}
