package com.example.me.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button randonstore,weekmenu,noodleshop,liitleeat,buffee,lunchbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public  void torandomstore (View view){
        Intent renadons = new Intent(this,com.example.me.finalproject.randomshow.class);
        startActivity(renadons);
    }
    public  void toweekmenu (View view){
        Intent weekm = new Intent(this,com.example.me.finalproject.aweekmenu.class);
        startActivity(weekm);
    }
    public  void tolittleeat (View view){
        Intent menustore = new Intent(Main2Activity.this,com.example.me.finalproject.storelist1.class);
        menustore.putExtra("sel","1");
        startActivity(menustore);
    }
    public  void tolaunchbox (View view){
        Intent menustore = new Intent(Main2Activity.this,com.example.me.finalproject.storelist1.class);
        menustore.putExtra("sel","3");
        startActivity(menustore);
    }
    public  void tonoodleshop (View view){
        Intent menustore = new Intent(Main2Activity.this,com.example.me.finalproject.storelist1.class);
        menustore.putExtra("sel","4");
        startActivity(menustore);
    }
    public  void tobuffe (View view){
        Intent menustore = new Intent(Main2Activity.this,com.example.me.finalproject.storelist1.class);
        menustore.putExtra("sel","2");
        startActivity(menustore);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent about  = new Intent(this,com.example.me.finalproject.aboutus.class);
            startActivity(about);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menub) {
            Intent menu1 = new Intent(Main2Activity.this, com.example.me.finalproject.Menu.class);
            Main2Activity.this.startActivity(menu1);
        } else if (id == R.id.storelist) {
            Intent menustore = new Intent(Main2Activity.this,com.example.me.finalproject.storelist1.class);
            startActivity(menustore);
        }
        else if (id == R.id.foodstreet) {
            Intent foodstreet1 = new Intent(Main2Activity.this, com.example.me.finalproject.foodstreet.class);
            startActivity(foodstreet1);
        }  else if (id == R.id.nav_send) {
            Intent emailback = new Intent(Intent.ACTION_VIEW);
            emailback.setData(Uri.parse("mailto:eatwhatlater@gmail.com"));
            emailback.putExtra(Intent.EXTRA_SUBJECT,"要吃甚麼?的意見回覆");
            emailback.putExtra(Intent.EXTRA_TEXT,"我的意見是:\t");
            startActivity(emailback);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
