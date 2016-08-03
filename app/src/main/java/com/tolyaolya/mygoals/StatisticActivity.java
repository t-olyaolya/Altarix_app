package com.tolyaolya.mygoals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 111 on 21.06.2016.
 */
public class StatisticActivity extends AppCompatActivity {

    @BindView(R.id.my_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindString(R.string.Week) String week;
    @BindString(R.string.Month) String month;
    @BindString(R.string.Year) String year;
    @BindString(R.string.total) String total;
    @BindString(R.string.completed) String cmp;
    @BindString(R.string.uncompleted) String uncmp;
    @BindString(R.string.progress) String progress;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    static Context mContext;
    private List<StatisticHandler> statistic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DbHelper mDbHelper=new DbHelper(this,DbHelper.DB_NAME,null,DbHelper.VERSION);
        int i=mDbHelper.getWeekDone()*100/mDbHelper.getWeekAll();
        int j=mDbHelper.getMonthDone()*100/mDbHelper.getMonthAll();
        int k=mDbHelper.getYearDone()*100/mDbHelper.getYearAll();
        statistic = new ArrayList<>();
        statistic.add(new StatisticHandler(week, total+": " +mDbHelper.getWeekAll(),cmp+": " + mDbHelper.getWeekDone(),
                uncmp+": "+mDbHelper.getWeekMissing(),progress+": "+i+" %"));
        statistic.add(new StatisticHandler(month, total+": " +mDbHelper.getMonthAll(),cmp+": " + mDbHelper.getMonthDone(),
                uncmp+": "+mDbHelper.getMonthMissing(),progress+": "+j+" %"));
        statistic.add(new StatisticHandler(year, total+": " +mDbHelper.getYearAll(),cmp+": "+ mDbHelper.getYearDone(),
                uncmp+": "+mDbHelper.getYearMissing(),progress+": "+k+" %"));
        RecyclerAdapter mRecyclerAdapter=new RecyclerAdapter(statistic);
        mRecyclerView.setAdapter(mRecyclerAdapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_statistic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

        if (id == R.id.action_settings) {
            Intent intent=new Intent(StatisticActivity.this,SettingActivity.class);
            startActivity(intent);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static Context getContext() {
        return mContext;
    }

}
