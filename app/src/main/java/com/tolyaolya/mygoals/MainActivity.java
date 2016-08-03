package com.tolyaolya.mygoals;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tolyaolya.mygoals.fragment.TabFragmentCurrent;
import com.tolyaolya.mygoals.fragment.TabFragmentDone;
import com.tolyaolya.mygoals.fragment.TabFragmentMissing;
import com.tolyaolya.mygoals.fragment.TabFragmentAll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    //NavigationView navigationView;
    TabLayout tabLayout;
    ViewPager viewPager;
    DbHelper mDb;
    Intent i;
    public static int sCheck =0;
    public static int sSw =0;
    public static int sHour =0;
    public static int sMinute =0;
    SharedPreferences mPreferences;
    DbHelper mDbHelper;
    private static final int NOTIFY_ID = 101;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPreferences = getSharedPreferences("set", MODE_PRIVATE);
        sCheck = mPreferences.getInt("sort", 0);
        sSw = mPreferences.getInt("sw", 0);
        //sHour = mPreferences.getInt("hour", 0);
        //sMinute = mPreferences.getInt("minute", 0);
        DbHelper mDbHelper = new DbHelper(this);
        int i=mDbHelper.getTodayItems();
        if((sSw!=0)&&(i!=0)) {
            Intent notificationIntent = new Intent();
            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(),
                    0, notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            Notification.Builder builder = new Notification.Builder(getApplicationContext());
            builder.setContentIntent(contentIntent).setSmallIcon(R.drawable.ic).setTicker(getResources().getString(R.string.ticker)).
                    setWhen(System.currentTimeMillis())
                    .setAutoCancel(true).setContentTitle(getResources().getString(R.string.app_name)).
                    setContentText(getResources().getString(R.string.notif));
            Notification notification = builder.getNotification();
            NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFY_ID, notification);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mDb = new DbHelper(this, DbHelper.DB_NAME, null, DbHelper.VERSION);

    }



    @Override
    public void onBackPressed() {
        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabFragmentAll(),getResources().getString(R.string.all));
        adapter.addFragment(new TabFragmentCurrent(), getResources().getString(R.string.current));
        adapter.addFragment(new TabFragmentDone(), getResources().getString(R.string.done));
        adapter.addFragment(new TabFragmentMissing(), getResources().getString(R.string.missing));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete_all) {
            mDb.deleteAll();
            Intent i=new Intent(this,this.getClass());
            this.startActivity(i);
            return true;
       }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cal) {
            Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(intent);
        } else if (id == R.id.stat) {
           Intent intent = new Intent(MainActivity.this, StatisticActivity.class);
           startActivity(intent);

        } else if (id == R.id.about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);

        } else if(id==R.id.setting) {
            Intent intent=new Intent(MainActivity.this,SettingActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
