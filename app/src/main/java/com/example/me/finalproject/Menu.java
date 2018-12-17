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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class Menu extends AppCompatActivity {
    Uri imgUri;
    ImageView imv;
    static final String db_name="storelistDB";
    static final String tb_name="storelist";
    SQLiteDatabase db;

    String pic;
    EditText storeinput;
    Spinner  typeinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        storeinput = (EditText)findViewById(R.id.storein);
        typeinput =(Spinner)findViewById(R.id.typein);


        String S =storeinput.getText().toString();//店家的店名輸入
        String[] T = getResources().getStringArray(R.array.store_type);//店家類型輸入
        int index=typeinput.getSelectedItemPosition();

        db=openOrCreateDatabase(db_name,Context.MODE_PRIVATE,null);
        String createTable="CREATE TABLE IF NOT EXISTS " +
                tb_name+
                "(store VARCHAR(32), " +
                "type VARCHAR(16), " +
                "picture VARCHAR(64))";
        db.execSQL(createTable);

        Cursor c=db.rawQuery("SELECT * FROM "+tb_name,null);
/*
        if(c.getCount()==0){

        }*/
        addData(S,T[index],pic);
/*
        c =db.rawQuery("SELECT * FROM "+tb_name,null);


        if(c.moveToFirst()){
            String str ="總共有 "+c.getCount() +"筆資料\n";
            str+="-----\n";

            do {
                str+="store:"+c.getString(0)+"\n";
                str+="type:"+c.getString(1)+"\n";
                str+="picture:"+c.getString(2)+"\n";
                str+="-----\n";

            }while (c.moveToNext());
        }
        */
        db.close();
    }
    private void addData(String store,String type, String picture){
        ContentValues cv =new ContentValues(3);
        cv.put("store",store);
        cv.put("type",type);
        cv.put("picture",picture);

        db.insert(tb_name,null,cv);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

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
    }

    public void finishw(View view) {

        EditText store =(EditText)findViewById(R.id.storein);
        Spinner  type = (Spinner)findViewById(R.id.typein);

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
        ;
        MyAlertDialog.setNeutralButton("中間按鈕", OkClick);
        MyAlertDialog.setPositiveButton("左邊按鈕", OkClick);
        MyAlertDialog.setNegativeButton("右邊按鈕", OkClick);
        MyAlertDialog.show();

    }

}

