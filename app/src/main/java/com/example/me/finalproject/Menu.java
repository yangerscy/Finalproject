package com.example.me.finalproject;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class Menu extends AppCompatActivity {
    Uri imgUri;
    ImageView imv;
    //static final String db_name="storelistDB";
    //static final String tb_name="storelist1";
    SQLiteDatabase db;
    Cursor cursor=null;

    String pic;
    EditText storeinput;
    Spinner  typeinput;

    int ID;
    String upstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

       storeinput = (EditText)findViewById(R.id.storein);
       typeinput =(Spinner)findViewById(R.id.typein);



        db=openOrCreateDatabase("storelistDB",Context.MODE_PRIVATE,null);

        try {
            String createTable = "CREATE TABLE storelist1(_id INTEGER PRIMARY KEY, store TEXT,type STRING)";
            db.execSQL(createTable);
         //   Toast.makeText(getApplicationContext(),"資料庫開啟",Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),"資料庫開啟失敗",Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResult) {
        if (requestCode == 200) {
            if (grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                savePhoto();
            } else {
                Toast.makeText(this, "程式需要寫入授權才能運作", Toast.LENGTH_LONG).show();
            }
        }
    }


    void showImg(){
        int iw,ih,vw,vh;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(imgUri),null,options);
        }
        catch (IOException e){
            Toast.makeText(this,"無法讀取圖片",Toast.LENGTH_LONG).show();
            return;
        }
        imv = findViewById(R.id.storepicture);
       iw = options.outWidth;
        ih = options.outHeight;
        vw = imv.getWidth();
        vh = imv.getHeight();
        int scaleFactor = Math.min(iw/vw,ih/vh);
        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(imgUri),null,options);
        }
        catch (IOException e){
            Toast.makeText(this,"無法讀取圖片",Toast.LENGTH_LONG).show();
            return;
        }
        pic=bmp.toString();
        imv.setImageBitmap(bmp);//取圖

    }

    private void savePhoto() {
        imgUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        Intent save = new Intent("android.media.action.IMAGE_CAPTURE");
        save.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        startActivityForResult(save, 100);
    }
    public void onGet(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        } else {
            savePhoto();
        }
       /* Intent catchpicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(catchpicture, 100);*/
    }
    public  void onPick(View view){
        Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
        pick.setType("image/*");
        startActivityForResult(pick,101);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data,Intent listshows) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 100:
                    Intent file = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, imgUri);
                    sendBroadcast(file);
                    break;
                case 101:
                    imgUri = data.getData();
                    break;
            }
            showImg();
        } else {
            Toast.makeText(this, "沒有拍到店家照片", Toast.LENGTH_LONG).show();
        }
        //get intent
        if(requestCode == RESULT_OK){

            Intent listview=getIntent();
            ID = listview.getIntExtra("編號",0);
            upstore = listview.getStringExtra("店家");
            storeinput.setText(upstore);

            Toast.makeText(getApplicationContext(),"傳輸成功",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"傳輸失敗",Toast.LENGTH_SHORT).show();
        }
    }


    protected void onDestroy(){
        super.onDestroy();
        //db.execSQL("DROP TABLE storelist1");
        db.close();
    //    deleteDatabase("storelistDB");
    }
    public void insert(View v) {


        ContentValues cv = new ContentValues();
        cv.put("store",storeinput.getText().toString());
        String[] T = getResources().getStringArray(R.array.store_type);
        int index=typeinput.getSelectedItemPosition();
        cv.put("type",T[index]);

        try{
            db.insert("storelist1",null,cv);


            Toast.makeText(getApplicationContext(),"新增店家成功",Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),"新增店家失敗",Toast.LENGTH_SHORT).show();
        }
        db.close();
        finish();
    }
    protected void update(View v){


        ContentValues cv = new ContentValues();
        cv.put("store",storeinput.getText().toString());
        String[] T = getResources().getStringArray(R.array.store_type);
        int index=typeinput.getSelectedItemPosition();
        cv.put("type",T[index]);


        try{
            db.update("storelist1",cv,"_id="+String.valueOf(ID),null);

            Toast.makeText(getApplicationContext(),"update success",Toast.LENGTH_SHORT).show();

        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),"update error",Toast.LENGTH_SHORT).show();
        }
        finish();
    }


    private void myAlertDialog() {
        AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
        MyAlertDialog.setTitle("標題");
        MyAlertDialog.setMessage("我是內容");
        DialogInterface.OnClickListener OkClick = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // 如果不做任何事情 就會直接關閉 對話方塊
            }
        };

        MyAlertDialog.setNeutralButton("中間按鈕", OkClick);
        MyAlertDialog.setPositiveButton("左邊按鈕", OkClick);
        MyAlertDialog.setNegativeButton("右邊按鈕", OkClick);
        MyAlertDialog.show();

    }


}
