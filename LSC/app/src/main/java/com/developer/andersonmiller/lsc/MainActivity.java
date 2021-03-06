package com.developer.andersonmiller.lsc;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
       implements NavigationView.OnNavigationItemSelectedListener {

    private String Final;

    private String MidTerm;

    private String DropDate;

    private String OfficeHour;


    @Override
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action

        }else if (id == R.id.nav_capture){

        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_price) {

        }else if (id == R.id.nav_log) {

        }else if (id == R.id.nav_historyCalendar){

            HistoryCalendar history = new HistoryCalendar();

            try {
                AssetManager assetManager = getAssets();

                InputStream stream = assetManager.open("Sample.txt");
                int size = stream.available();
                byte[] buffer = new byte[size];
                stream.read(buffer);
                stream.close();
                String text = new String(buffer);

                history.setContent(text);


            } catch (IOException e) {
                e.printStackTrace();
            }



                //Adds midterm to calendar.
                history.ImportantDates();
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("title", "Mid Term");
            intent.putExtra("description", "MidTerm");
            intent.putExtra("allDay", true);
            intent.putExtra("beginTime", history.getMidTerm().getTimeInMillis());
            startActivity(intent);


            //Adds final to calendar
            Intent Finalintent = new Intent(Intent.ACTION_EDIT);
            Finalintent.setType("vnd.android.cursor.item/event");
            Finalintent.putExtra("title", "Final");
            Finalintent.putExtra("allDay", true);
            Finalintent.putExtra("description", "Final Exam");
            Finalintent.putExtra("beginTime", history.getFinal().getTimeInMillis());
            startActivity(Finalintent);

        } else if (id == R.id.nav_history_price) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
