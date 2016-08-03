package com.tolyaolya.mygoals;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tolyaolya.mygoals.fragment.AboutFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 111 on 11.06.2016.
 */
public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_goals:
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.action_calendar:
                intent = new Intent(AboutActivity.this, CalendarActivity.class);
                startActivity(intent);
                break;

            case R.id.action_static:
                intent = new Intent(AboutActivity.this, StatisticActivity.class);
                startActivity(intent);
                break;

            case R.id.action_settings:
                intent=new Intent(AboutActivity.this,SettingActivity.class);
                startActivity(intent);
                break;
               // return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



