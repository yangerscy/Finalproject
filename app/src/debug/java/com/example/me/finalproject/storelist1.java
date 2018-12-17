package com.example.me.finalproject;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class storelist1 extends AppCompatActivity
implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener
{
    String[] liststore = {} ;
    ListView  lisv;
    ArrayAdapter<String> aa;
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
