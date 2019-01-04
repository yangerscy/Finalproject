package com.example.me.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class randomshow extends AppCompatActivity {
    Button again, eat;
    SQLiteDatabase db;
    Integer count;
    Cursor c;
    TextView foodstradon2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomshow);
        foodstradon2 =findViewById(R.id.foodstradon2);
        db=openOrCreateDatabase("storelistDB",Context.MODE_PRIVATE,null);

        try {
            c=db.rawQuery("SELECT * FROM storelist1 ",null);
            count=Integer.parseInt(String.valueOf(c.getCount()));
            foodstradon2.setText(count.toString()+"筆資料");
            Random x = new Random();
            c.moveToPosition(x.nextInt(count));
            foodstradon2.setText(String.valueOf(c.getString(1)));
        }
        catch (Exception ex){
            foodstradon2.setText(ex.toString());
            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_SHORT).show();
            //for debug

        }
    }

    public  void onemore(View view){
        Random x = new Random();
        c.moveToPosition(x.nextInt(count));
        foodstradon2.setText(String.valueOf(c.getString(1)));
    }
    public void eatclick(View view) {
        try {
            String createTable = "CREATE TABLE eattimes(_id INTEGER PRIMARY KEY, store TEXT,times INTEGER)";
            db.execSQL(createTable);
            Toast.makeText(getApplicationContext(),"資料表完成",Toast.LENGTH_SHORT).show();
        }catch (Exception ex){

        }
        Cursor cu=db.rawQuery("SELECT * FROM eattimes ",null);
    finish();

    }

}
