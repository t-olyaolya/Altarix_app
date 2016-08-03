package com.tolyaolya.mygoals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TimePicker;

import com.tolyaolya.mygoals.fragment.TabFragmentAll;
import com.tolyaolya.mygoals.fragment.TabFragmentCurrent;
import com.tolyaolya.mygoals.fragment.TabFragmentDone;
import com.tolyaolya.mygoals.fragment.TabFragmentMissing;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 111 on 02.08.2016.
 */
public class SettingActivity extends AppCompatActivity{
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rg) RadioGroup mRadioGroup;
    //@BindView(R.id.timePicker) TimePicker mTimePicker;
    @BindView(R.id.button) Button mButton;
    //@BindView(R.id.button2) Button mButton2;
    @BindView(R.id.switch1) Switch mSwitch;
    @BindView(R.id.radio_null) RadioButton mRadioButton;
    @BindView(R.id.radio_name) RadioButton mRadioButton2;
    SharedPreferences mSettings;
    int index;
    int sw;
    int hour;
    int minute;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        mSettings=getSharedPreferences("set",MODE_PRIVATE);
        index=mSettings.getInt("sort",0);
        sw=mSettings.getInt("sw",0);
        hour=mSettings.getInt("hour",0);
        minute=mSettings.getInt("minute",0);
        setSupportActionBar(toolbar);
        final Intent i=new Intent(this,MainActivity.class);
        if (sw==1) {
            mSwitch.setChecked(true);
        }
        if (sw==0) {
            mSwitch.setChecked(false);
        }
        if (index==0) {
            mRadioButton.setChecked(true);
        }
        if (index==1){
            mRadioButton2.setChecked(true);
        }
        //mTimePicker.setCurrentHour(hour);
        //mTimePicker.setCurrentMinute(minute);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sw=1;
                }

                else {
                    sw=0;
                }
            }
        });

       /* mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hours, int minutes) {
                hour=hours;
                minute=minutes;
            }
        }); */

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch(id) {
                    case R.id.radio_null:
                        index=0;
                        break;
                    case R.id.radio_name:
                        index=1;
                        break;
                    default:
                        index=0;
                        break;
                }
            }});
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sCheck =1;
                // i.putExtra("sort", index);
                startActivity(i);
            }
        });
        }

    @Override
    public void onPause(){
        super.onPause();
        mSettings=getSharedPreferences("set",MODE_PRIVATE);;
        SharedPreferences.Editor ed=mSettings.edit();
        ed.putInt("sort",index);
        ed.putInt("sw",sw);
       // ed.putInt("hour",hour);
       // ed.putInt("minute",minute);
        ed.apply();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_goals:
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.action_calendar:
                intent = new Intent(SettingActivity.this, CalendarActivity.class);
                startActivity(intent);
                break;

            case R.id.action_static:
                intent = new Intent(SettingActivity.this, StatisticActivity.class);
                startActivity(intent);
                break;

            case R.id.action_about:
                intent=new Intent(SettingActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
               // return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
