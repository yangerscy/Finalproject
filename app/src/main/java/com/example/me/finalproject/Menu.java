package com.example.me.finalproject;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLData;

public class Menu extends AppCompatActivity {
    Uri imgUri;
    ImageView imv;
    public SQLData DH=null;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


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

        imv.setImageBitmap(bmp);

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
        finish();
    }

}

