package com.tolyaolya.mygoals;



import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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


/**
 * Created by 111 on 29.06.2016.
 */
public class AddGoalActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEditText1;
    EditText mEditText2;
    EditText mEditText3;
    EditText mEditText4;
    DbHelper mDbHelper;
    SQLiteDatabase mDb;
    FloatingActionButton mFab;
    int DIALOG_DATE = 1;
    Calendar c=Calendar.getInstance();
    int myYear = c.get(Calendar.YEAR);
    int myMonth = c.get(Calendar.MONTH);
    int myDay = c.get(Calendar.DAY_OF_MONTH);
    ContentValues cv= new ContentValues();
    Toast mToast;




    @Override
    protected void onCreate(Bundle savedInstanteState) {
        super.onCreate(savedInstanteState);
        setContentView(R.layout.add_goal);
        mEditText1=(EditText)findViewById(R.id.edit_goal);
        mEditText2=(EditText)findViewById(R.id.edit_detail);
        mEditText3=(EditText)findViewById(R.id.edit_end);
        mFab=(FloatingActionButton)findViewById(R.id.fab_2);
        mDbHelper = new DbHelper(this);
        mDb=mDbHelper.getReadableDatabase();
        mFab.setOnClickListener(this);
        mEditText3.setOnClickListener(this);


    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //saveState();
        //outState.putSerializable(ToDoDatabase.COLUMN_ID, mRowId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //populateFields();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_end:
                showDialog(DIALOG_DATE);
                break;
            case R.id.fab_2:
               // dbActions();
                String name = mEditText1.getText().toString();
                String details = mEditText2.getText().toString();
                String date="myDay"+"myMonth"+"myYear";
                ContentValues cv = new ContentValues();
                try {
                    cv.put("_id", 1);
                    cv.put("name", name);
                    cv.put("details", details);
                    cv.put("date", date);
                    DbHelper.getInstance().getWritableDatabase().insert("Bd1", null, cv);
                    mToast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_LONG).show();
                }
                catch (Exception ex) {
                    mToast.makeText(getApplicationContext(),"Error! Try Again",Toast.LENGTH_LONG).show();
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
