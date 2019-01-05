package com.example.me.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
            c=db.rawQuery("SELECT _id, store FROM storelist1 ",null);
            count=Integer.parseInt(String.valueOf(c.getCount()));
            foodstradon2.setText(count.toString()+"筆資料");
            Random x = new Random();
            c.moveToPosition(x.nextInt(count));
            String storename  = c.getString(1);
            foodstradon2.setText(String.valueOf(storename));
            Cursor cursor=db.rawQuery("SELECT _id ,image FROM storelist1 WHERE store = '"+storename+"'",null);
            cursor.moveToFirst();
            byte bytes[] = Base64.decode(cursor.getString(1), Base64.DEFAULT);
            Bitmap pic;
            pic=BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            ImageView imv=findViewById(R.id.imageView2);
            imv.setImageBitmap(pic);
        }
        catch (Exception ex){
            /*
            foodstradon2.setText(ex.toString());
            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_SHORT).show();*/
            //for debug

        }
    }

    public  void onemore(View view){
        Random x = new Random();
        c.moveToPosition(x.nextInt(count));
        String storename  = c.getString(1);
        foodstradon2.setText(String.valueOf(storename));
        Cursor cursor=db.rawQuery("SELECT _id ,image FROM storelist1 WHERE store = '"+storename+"'",null);
        cursor.moveToFirst();
        byte bytes[] = Base64.decode(cursor.getString(1), Base64.DEFAULT);
        Bitmap pic;
        pic=BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        ImageView imv=findViewById(R.id.imageView2);
        imv.setImageBitmap(pic);
    }
    public void eatclick(View view) {
        try {
            String createTable = "CREATE TABLE eattimes(_id INTEGER PRIMARY KEY, store TEXT,times INTEGER)";
            db.execSQL(createTable);
            //Toast.makeText(getApplicationContext(),"資料表完成",Toast.LENGTH_SHORT).show();
        }catch (Exception ex){

        }
        String stname =foodstradon2.getText().toString().trim();

        try {
            Cursor cu=db.rawQuery("SELECT * FROM eattimes WHERE store = '"+stname+"'",null);
            cu.moveToFirst();
            String st;
            st=cu.getString(1);
            Integer ti;
            ti=cu.getInt(2);
            db.execSQL("DELETE FROM eattimes");
            ContentValues ccv = new ContentValues();
            ccv.put("store",st);
            ti=ti+1;
            ccv.put("times",ti);
            db.insert("eattimes",null,ccv);
            if(ti>=3) {
                Toast.makeText(getApplicationContext(), "已經連續吃" + st + "" + ti+"次了，換點口味吧", Toast.LENGTH_LONG).show();
            }
        }catch (Exception ex){
            db.execSQL("DELETE FROM eattimes");
            ContentValues ccv = new ContentValues();
            ccv.put("store",stname);

            ccv.put("times",1);
            db.insert("eattimes",null,ccv);
        }

    finish();

    }

}
