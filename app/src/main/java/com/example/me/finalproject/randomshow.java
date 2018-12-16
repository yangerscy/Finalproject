package com.example.me.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class randomshow extends AppCompatActivity {
    Button again, eat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomshow);
    }

    public void eatclick(View view) {
    finish();

    }

}
