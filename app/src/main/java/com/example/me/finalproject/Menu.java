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
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Menu extends AppCompatActivity {
    Uri imgUri;
    ImageView imv;

    SQLiteDatabase db;
   // Cursor cursor=null;

    Bitmap pic=null;
    EditText storeinput;
    Spinner  typeinput;
    Boolean iffromlist =false;
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
            String createTable = "CREATE TABLE storelist1(_id INTEGER PRIMARY KEY, store TEXT,type STRING, image VALUE_PIC)";
            db.execSQL(createTable);
         //   Toast.makeText(getApplicationContext(),"資料庫開啟",Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),"資料庫開啟失敗",Toast.LENGTH_SHORT).show();
        }
            try{
                Intent it=getIntent();
                String sid =it.getStringExtra("編號");
                ID=Integer.parseInt(sid);
                upstore=it.getStringExtra("店家");
               iffromlist=true;
            }catch (Exception e){
                iffromlist=false;
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

        imv.setImageBitmap(bmp);//取圖
            pic=bmp;
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


    protected void onDestroy(){
        super.onDestroy();
        //db.execSQL("DROP TABLE storelist1");
        db.close();
        //deleteDatabase("storelistDB");
    }

    public void insert(View v) {

        ContentValues cv = new ContentValues();
        cv.put("store",storeinput.getText().toString());
        String[] T = getResources().getStringArray(R.array.store_type);
        int index=typeinput.getSelectedItemPosition();
        cv.put("type",T[index]);

        // 先把 bitmap 轉成 byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        pic.compress(Bitmap.CompressFormat.JPEG, 100, stream );
        byte bytes[] = stream.toByteArray();
        // 把byte變成base64
        String base64 = Base64.encodeToString(bytes, Base64.DEFAULT);

        //放img進去SQLite
        if(pic==null) {
            cv.put("image","404 pic not found");
        }
        else{
            cv.put("image", base64);
        }
        try{
            if(iffromlist) {
                db.update("storelist1",cv,"_id="+String.valueOf(ID),null);
            }else {


                db.insert("storelist1", null, cv);

            }

            Toast.makeText(getApplicationContext(),"新增店家成功",Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),"新增店家失敗",Toast.LENGTH_SHORT).show();
        }
        db.close();
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
