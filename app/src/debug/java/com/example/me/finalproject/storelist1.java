package com.example.me.finalproject;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class storelist1 extends AppCompatActivity
implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener
{
    String[] liststore = {} ;
    ListView  lisv;
    ArrayAdapter<String> aa;

    static final String db_name="storelistDB";
    static final String tb_name="storelist";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storelist1);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        lisv = findViewById(R.id.lists);
        aa= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,liststore);
        lisv.setAdapter(aa);
        lisv.setOnItemClickListener(this);
        lisv.setOnItemLongClickListener(this);

        /*List<Member> memberList = new ArrayList<>();
        memberList.add(new Member(1, R.drawable.lauchbox, "光華飯包"));
        memberList.add(new Member(2, R.drawable.landi, "藍迪義大利麵"));
        memberList.add(new Member(3, R.drawable.trashnoddle, "光華垃圾面"));
        memberList.add(new Member(4, R.drawable.beefnoddle579, "伍柒玖牛肉麵"));
        memberList.add(new Member(5, R.drawable.sakadon, "佐賀丼飯"));
        memberList.add(new Member(6, R.drawable.beefnoodle95, "玖伍牛肉麵"));
        memberList.add(new Member(7, R.drawable.satoseiniku, "佐藤精肉店"));
       /* RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));*/
// MemberAdapter 會在步驟7建立
        /*recyclerView.setAdapter(new MemberAdapter(this, memberList));*/
        db=openOrCreateDatabase(db_name,Context.MODE_PRIVATE,null);

        String createTable="CREATE TABLE IF NOT EXISTS " +
                tb_name+
                "(store VARCHAR(32), " +
                "type VARCHAR(16), " +
                "picture VARCHAR(64))";
        db.execSQL(createTable);
        Cursor c=db.rawQuery("SELECT * FROM "+tb_name,null);

        if(c.getCount()==0){

        }
        //addData(S,T[index],pic);

        c =db.rawQuery("SELECT * FROM "+tb_name,null);


        if(c.moveToFirst()){
            String str ="總共有 "+c.getCount() +"間店家\n";
            str+="-----\n";

            do {
                str+="store:"+c.getString(0)+"\n";
                str+="type:"+c.getString(1)+"\n";
                str+="picture:"+c.getString(2)+"\n";
                str+="-----\n";

            }while (c.moveToNext());
        }

        db.close();
    }
    public  void newdata1 (View view){
       Intent newdata = new Intent(this,Menu.class);
       startActivity(newdata);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       Intent listshow = new Intent(this,Menu.class);
      startActivity(listshow);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        liststore[position] = (position+1) + ".";
        aa.notifyDataSetChanged();
        return true;
    }
}
