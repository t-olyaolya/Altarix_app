package com.tolyaolya.mygoals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by 111 on 21.06.2016.
 */
public class StatisticActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String[] myDataset= getDataSet();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // если мы уверены, что изменения в контенте не изменят размер layout-а RecyclerView
        // передаем параметр true - это увеличивает производительность
        mRecyclerView.setHasFixedSize(true);

        // используем linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // создаем адаптер
        mAdapter = new RecyclerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id==R.id.action_goals) {
            Intent intent = new Intent(StatisticActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (id==R.id.action_calendar) {
            Intent intent = new Intent(StatisticActivity.this, CalendarActivity.class);
            startActivity(intent);
        }

        if (id==R.id.action_about) {
            Intent intent = new Intent(StatisticActivity.this, AboutActivity.class);
            startActivity(intent);
        }


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private String[] getDataSet() {

        String[] mDataSet = new String[3];
        mDataSet[0]="WEEK";
        mDataSet[1]="MONTH";
        mDataSet[2]="YEAR";

        return mDataSet;
    }
}
