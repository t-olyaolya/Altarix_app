package com.tolyaolya.mygoals;



import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 111 on 29.06.2016.
 */
public class AddGoalActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.edit_goal) EditText mEditText1;
    @BindView(R.id.edit_detail)EditText mEditText2;
    @BindView(R.id.edit_end)EditText mEditText3;
    @BindView(R.id.fab_2) FloatingActionButton mFab;
    @BindString(R.string.successful) String succ;
    @BindString(R.string.error) String err;
    Toast mToast;
    DbHelper mDbHelper;
    SQLiteDatabase mDb;
    Calendar c=Calendar.getInstance();
    int myYear = c.get(Calendar.YEAR);
    int myMonth = c.get(Calendar.MONTH);
    int myDay = c.get(Calendar.DAY_OF_MONTH);
    int DIALOG_DATE = 1;
    static final int VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanteState) {
        super.onCreate(savedInstanteState);
        setContentView(R.layout.add_goal);
        ButterKnife.bind(this);
        mFab.setOnClickListener(this);
        mEditText3.setOnClickListener(this);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_end:
                showDialog(DIALOG_DATE);
                break;
            case R.id.fab_2:
                String name = mEditText1.getText().toString();
                String details = mEditText2.getText().toString();
                String day=String.valueOf(myDay);
                String month=String.valueOf(myMonth);
                String year=String.valueOf(myYear);
                String date=day+"/"+month+"/"+year;
                int day_bd=myDay;
                int month_bd=myMonth;
                int year_bd=myYear;
                mDbHelper=new DbHelper(this,DbHelper.DB_NAME,null,VERSION);
                mDb=mDbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();

                try {
                    //cv.put("_id", 1);
                    cv.put(DbHelper.colName, name);
                    cv.put(DbHelper.colDetails, details);
                    cv.put(DbHelper.colDate, date);
                    cv.put(DbHelper.colFlag,0);
                    cv.put(DbHelper.colDay,day_bd);
                    cv.put(DbHelper.colMonth, month_bd);
                    cv.put(DbHelper.colYear, year_bd);

                    mDb.insert("Bd1", null, cv);
                    mToast.makeText(getApplicationContext(),succ,Toast.LENGTH_LONG).show();
                }
                catch (Exception ex) {
                    mToast.makeText(getApplicationContext(),err,Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(AddGoalActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }


    }


    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    OnDateSetListener myCallBack = new OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            mEditText3.setText(myDay + "/" + myMonth + "/" + myYear);
        }
    };



}
