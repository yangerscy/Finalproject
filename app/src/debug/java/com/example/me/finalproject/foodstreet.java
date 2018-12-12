package com.example.me.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class foodstreet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodstreet);
        List<cardlist> memberList = new ArrayList<>();
        memberList.add(new cardlist(1, R.drawable.lauchbox, "白沙屯海灘1"));

    }
}
