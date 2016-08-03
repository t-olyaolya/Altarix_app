package com.tolyaolya.mygoals;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;
import android.os.Bundle;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 111 on 19.06.2016.
 */


public class CalendarActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindString(R.string.nogoals) String ngls;
    CalendarView mCalendarView;
    private String date;
    DbHelper mDbHelper;
    SQLiteDatabase mDb;
    String[] columns = null;
    String selection = null;
    String[] selectionArgs = null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView1);
        setSupportActionBar(toolbar);
        mDbHelper=new DbHelper(this);
        mDb=mDbHelper.getWritableDatabase();
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {
                date = dayOfMonth + "/" + month + "/" + year;
                int i = 0;
                Cursor c;
                ArrayList<String> name = new ArrayList<String>();
                selection = "date=?";
                selectionArgs = new String[]{date};
                c = mDb.query(DbHelper.DB_NAME, columns, selection, selectionArgs, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        name.add(c.getString(c.getColumnIndexOrThrow(DbHelper.colName)));
                        i++;
                    }
                    while (c.moveToNext());
                } else {
                    Toast.makeText(getApplicationContext(), ngls, Toast.LENGTH_SHORT).show();
                }
                String names = "";
                for (String item : name) {
                    names = names + item + "\n";
                }
                if (i != 0) {
                    Toast.makeText(getApplicationContext(),
                            names,
                            Toast.LENGTH_LONG).show();
                    c.close();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_about) {
            Intent intent = new Intent(CalendarActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_goals) {
            Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_static) {
            Intent intent = new Intent(CalendarActivity.this, StatisticActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_settings) {
            Intent intent=new Intent(CalendarActivity.this,SettingActivity.class);
            startActivity(intent);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
