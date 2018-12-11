package com.example.me.finalproject;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();
    private final  int SPLASH_DISPLAY_LENGY = 1000;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView backet =findViewById(R.id.backet);
        backet.setScaleType(ImageView.ScaleType.CENTER);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainintent = new Intent(MainActivity.this,Main2Activity.class);
                MainActivity.this.startActivity(mainintent);
                MainActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGY);
    }

  }

