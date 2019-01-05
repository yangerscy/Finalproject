package com.example.me.finalproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
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
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button randonstore,weekmenu,noodleshop,liitleeat,buffee,lunchbox;
    Calendar mCalendar;
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
   /* private void startRemind(){
        //得到日历实例，主要是为了下面的获取时间
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        //获取当前毫秒值
        long systemTime = System.currentTimeMillis();
        //是设置日历的时间，主要是让日历的年月日和当前同步
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        // 这里时区需要设置一下，不然可能个别手机会有8个小时的时间差
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //设置在几点提醒 设置的为13点
        mCalendar.set(Calendar.HOUR_OF_DAY, 12);
        //设置在几分提醒 设置的为25分
        mCalendar.set(Calendar.MINUTE, 0);
        //下面这两个看字面意思也知道
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);
        //上面设置的就是13点25分的时间点
        //获取上面设置的13点25分的毫秒值
        long selectTime = mCalendar.getTimeInMillis();

        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if(systemTime > selectTime) {
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        //.class为广播接受者
        Intent intent = new Intent(Main2Activity.this, .class);
        PendingIntent pi = PendingIntent.getBroadcast(Main2Activity.this, 0, intent, 0);
        //得到AlarmManager实例
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        //**********注意！！下面的两个根据实际需求任选其一即可*********
        /**
         * 单次提醒
         * mCalendar.getTimeInMillis() 上面设置的12点00分的时间点毫秒值

        am.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pi);

        /**
         * 重复提醒
         * 第三个参数是重复周期，也就是下次提醒的间隔 毫秒值 我这里是一天后提醒

        am.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), (1000 * 60 * 60 * 24), pi);
    }
    private void stopRemind(){
        Intent intent = new Intent(Main2Activity.this, .class);
        PendingIntent pi = PendingIntent.getBroadcast(Main2Activity.this, 0,
                intent, 0);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        //取消警报
        am.cancel(pi);
        Toast.makeText(this, "关闭了提醒", Toast.LENGTH_SHORT).show();
    }*/



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
