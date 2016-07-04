package com.tolyaolya.mygoals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;
import android.os.Bundle;
/**
 * Created by 111 on 19.06.2016.
 */


public class CalendarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CalendarView mCalendarView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView1);
        //calendarView.setFirstDayOfWeek(100);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {

                // При выборе любой даты отображаем Toast сообщение с данными о выбранной дате (Год, Месяц, День):
                Toast.makeText(getApplicationContext(),
                        "Год: " + year + "\n" +
                                "Месяц: " + month + "\n" +
                                "День: " + dayOfMonth,
                        //добавить события
                        Toast.LENGTH_SHORT).show();
            }});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
