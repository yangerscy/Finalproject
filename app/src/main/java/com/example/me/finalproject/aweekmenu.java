package com.example.me.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class aweekmenu extends AppCompatActivity {
    SQLiteDatabase db;
    Integer count;
    Cursor c;
    TextView sunl,sund,monl,mond,tuel,tued,wedl,wedd,thul,thud,fril,frid,satl,satd;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aweekmenu);
        sunl=(TextView)findViewById(R.id.sunl);
        sund=(TextView)findViewById(R.id.sund);

        monl=(TextView)findViewById(R.id.monl);
        mond=(TextView)findViewById(R.id.mond);

        tuel=(TextView)findViewById(R.id.tuel);
        tued=(TextView)findViewById(R.id.tued);

        wedl=(TextView)findViewById(R.id.wedl);
        wedd=(TextView)findViewById(R.id.wedd);

        thul=(TextView)findViewById(R.id.thul);
        thud=(TextView)findViewById(R.id.thud);

        fril=(TextView)findViewById(R.id.fril);
        frid=(TextView)findViewById(R.id.frid);

        satl=(TextView)findViewById(R.id.satl);
        satd=(TextView)findViewById(R.id.satd);
        db=openOrCreateDatabase("storelistDB",Context.MODE_PRIVATE,null);
        try {
            c = db.rawQuery("SELECT _id, list FROM weekmenu ", null);
            c.moveToFirst();
            ArrayList<String> ans;
            String anslist;
            anslist=c.getString(1);

            Type type = new TypeToken<ArrayList<String>>() {}.getType();
             ans = gson.fromJson(anslist, type);

            sunl.setText(ans.get(0));
            sund.setText(ans.get(1));

            monl.setText(ans.get(2));
            mond.setText(ans.get(3));

            tuel.setText(ans.get(4));
            tued.setText(ans.get(5));

            wedl.setText(ans.get(6));
            wedd.setText(ans.get(7));

            thul.setText(ans.get(8));
            thud.setText(ans.get(9));

            fril.setText(ans.get(10));
            frid.setText(ans.get(11));

            satl.setText(ans.get(12));
            satd.setText(ans.get(13));

        }catch (Exception e){
            String a=e.toString();
        }


    }
    public void cratelist(View view){
       try {
           ArrayList<String> list = new ArrayList<>();
           ArrayList<String> ans = new ArrayList<>();
           c = db.rawQuery("SELECT _id, store FROM storelist1 ", null);
           count = Integer.parseInt(String.valueOf(c.getCount()));
           c.moveToFirst();
           for (int i = 0; i < count; i = i + 1) {
               list.add( c.getString(1));
               c.moveToNext();
           }
           Random x = new Random();
           String a=list.get(x.nextInt(list.size()-1));
           ans.add(a);
           list.remove(list.indexOf(a));
           String b=list.get(x.nextInt(list.size()-1));
           ans.add(b);
           list.add(a);
           String c,d;
           for(int i=1;i<=6;i++) {
               c = list.get(x.nextInt(list.size()));
               ans.add(c);
               if (c == a || c == b) {
                   list.remove(list.indexOf(a));
                   list.remove(list.indexOf(b));
                   d = list.get(x.nextInt(list.size()-1));
                   ans.add(d);
                   list.add(a);
                   list.add(b);
               } else {
                   list.remove(list.indexOf(c));
                   d = list.get(x.nextInt(list.size()-1));
                   ans.add(d);
                   list.add(c);
               }
               a = c;
               b = d;
           }
           sunl.setText(ans.get(0));
           sund.setText(ans.get(1));

           monl.setText(ans.get(2));
           mond.setText(ans.get(3));

           tuel.setText(ans.get(4));
           tued.setText(ans.get(5));

           wedl.setText(ans.get(6));
           wedd.setText(ans.get(7));

           thul.setText(ans.get(8));
           thud.setText(ans.get(9));

           fril.setText(ans.get(10));
           frid.setText(ans.get(11));

           satl.setText(ans.get(12));
           satd.setText(ans.get(13));

           try {
               String createTable = "CREATE TABLE weekmenu(_id INTEGER PRIMARY KEY, list TEXT)";
               db.execSQL(createTable);
           }catch (Exception e){

           }



           String inputString= gson.toJson(ans);

           ContentValues ccv = new ContentValues();
           ccv.put("list",inputString);
           db.execSQL("DELETE FROM weekmenu");
           db.insert("weekmenu",null,ccv);

       }catch (Exception e){
           Toast.makeText(getApplicationContext(),"請建立四間以上店家",Toast.LENGTH_SHORT).show();
       }

    }
}
