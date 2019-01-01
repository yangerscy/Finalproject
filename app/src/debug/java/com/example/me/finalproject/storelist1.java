package com.example.me.finalproject;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class storelist1 extends AppCompatActivity
implements AdapterView.OnItemClickListener,View.OnClickListener,AdapterView.OnItemLongClickListener
{
    String[] liststore = {} ;
    ListView  lisv;
    ArrayAdapter<String> aa;
    SQLiteDatabase db;
    TextView tv_UID,tv_store;
    EditText et_type;
    Cursor cursor=null;

    Button bt_update,bt_query,bt_queryall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storelist1);

        tv_store = (TextView)findViewById(R.id.tv_store);
        et_type = (EditText)findViewById(R.id.et_type);
        tv_UID=(TextView)findViewById(R.id.tv_UID);
        bt_query=(Button)findViewById(R.id.bt_query);
        bt_queryall=(Button)findViewById(R.id.bt_queryall);
        bt_update=(Button)findViewById(R.id.bt_update);

        bt_queryall.setOnClickListener(this);
        bt_update.setOnClickListener(this);
        bt_query.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        lisv = (ListView)findViewById(R.id.lists);
        lisv.setOnItemClickListener(this);
        lisv.setOnItemLongClickListener(this);

        db=openOrCreateDatabase("storelistDB",Context.MODE_PRIVATE,null);

        try {
            Cursor c=db.rawQuery("SELECT * FROM storelist1 ",null);
            UpdateListView(c);

            Toast.makeText(getApplicationContext(),"資料庫開啟",Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_SHORT).show();
            //for debug
            tv_store.setText(ex.toString());
        }


        /*recyclerView.setAdapter(new MemberAdapter(this, memberList));*/

    }

    public  void newdata1 (View view){
       Intent newdata = new Intent(this,Menu.class);
       startActivity(newdata);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Cursor c = db.rawQuery("SELECT _id,store,type FROM storelist1 WHERE _id=" + id ,null);

        c.moveToFirst();
        tv_UID.setText(""+c.getInt(0));
        tv_store.setText(""+c.getString(1));
        et_type.setText(""+c.getString(2));


    }

    @Override
    public void onClick(View v) {
        try{
            switch (v.getId()){
                case R.id.bt_update:{

                    Intent listshow = new Intent(this,Menu.class);

                    listshow.putExtra("編號",tv_UID.getText().toString());
                    listshow.putExtra("店家",tv_store.getText().toString());
                    //setResult(RESULT_OK,listshow);
                    startActivity(listshow);
                db.close();
                break;
                }

                case R.id.bt_query:{
                    try{
                        cursor = db.rawQuery("SELECT * FROM storelist1 WHERE type"+
                                String.valueOf(et_type.getText()),null);
                        UpdateListView(cursor);

                        Toast.makeText(getApplicationContext(),"query success",Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception ex){
                        Toast.makeText(getApplicationContext(),"query error",Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case R.id.bt_queryall:{
                        try {
                            db=openOrCreateDatabase("storelistDB",Context.MODE_PRIVATE,null);
                        }catch (Exception e){

                        }
                        try{
                            cursor = db.rawQuery("SELECT * FROM storelist1",null);
                            UpdateListView(cursor);
                            Toast.makeText(getApplicationContext(),"query success",Toast.LENGTH_SHORT).show();

                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(),"query error",Toast.LENGTH_SHORT).show();
                        }
                    break;
                }
            }
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        db.close();

    }
//更新ListView
    public void UpdateListView(Cursor cursor){
        if(cursor!= null && cursor.getCount() >0){
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,
                    cursor,new String[]{"store","type"},
                    new int[]{android.R.id.text1,android.R.id.text2},0 );
            lisv.setAdapter(adapter);
        }
    }
//刪除店家
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor c = db.rawQuery("SELECT _id,store,type FROM storelist1 WHERE _id=" + id ,null);

        c.moveToFirst();
        tv_UID.setText(""+c.getInt(0));
        tv_store.setText(""+c.getString(1));
        et_type.setText(""+c.getString(2));

        AlertDialog.Builder builder = new AlertDialog.Builder(storelist1.this);
        builder.setTitle("確定刪除");
        builder.setMessage("確定要刪除"+tv_store.getText()+"這家店家?");
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    db.delete("storelist1","_id="+String.valueOf(tv_UID.getText()),null);
                    cursor=db.rawQuery("SELECT _id,_id|| '.'|| store store, type FROM storelist1",null);
                    UpdateListView(cursor);
                    Toast.makeText(getApplicationContext(),"刪除資料成功!",Toast.LENGTH_SHORT).show();
                    tv_UID.setText("");
                    tv_store.setText("");
                    et_type.setText("");
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"資料刪除失敗!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
        return true;
    }

}
