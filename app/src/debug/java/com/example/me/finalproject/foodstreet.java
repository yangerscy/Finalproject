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
        memberList.add(new cardlist(1, R.drawable.baishatunbeach1, "白沙屯海灘1"));
        memberList.add(new cardlist(2, R.drawable.baishatunbeach2, "白沙屯海灘2"));
        memberList.add(new cardlist(3, R.drawable.baishatunbeach3, "白沙屯海灘3"));
        memberList.add(new cardlist(4, R.drawable.baishatunbeach4, "白沙屯海灘4"));
        memberList.add(new cardlist(5, R.drawable.baishatunbeach5, "白沙屯海灘5"));
        memberList.add(new cardlist(6, R.drawable.baishatunspot3, "白沙屯3"));
        memberList.add(new cardlist(7, R.drawable.houlongthecape1, "後龍1"));
        memberList.add(new cardlist(8, R.drawable.houlongthecape2, "後龍2"));
        memberList.add(new cardlist(9, R.drawable.houlongthecape3, "後龍3"));
        memberList.add(new cardlist(10, R.drawable.longgangspot4, "龍港4"));
        memberList.add(new cardlist(11, R.drawable.longgangspot4_1, "龍港4.1"));
        memberList.add(new cardlist(12, R.drawable.longgangspot4_2, "龍港4.2"));
    }
}
