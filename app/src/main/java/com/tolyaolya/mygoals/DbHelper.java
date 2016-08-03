package com.tolyaolya.mygoals;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by 111 on 01.07.2016.
 */
public class  DbHelper  extends SQLiteOpenHelper{
    //private static final String NAME = "mybd.sqlite";
    private static DbHelper ins = null;
    public static final int VERSION = 1;
    public static final String DB_NAME ="Bd1";
    public static final String colId = "_id";
    public static final String colName = "name";
    public static final String colDetails = "details";
    public static final String colDate= "date";
    public static final String colFlag = "flag";
    public static  final String colDay="day";
    public static final String colMonth="month";
    public static final String colYear="year";
    public String id;
    Calendar c=Calendar.getInstance();
    int mYear=c.get(Calendar.YEAR);
    int mMonth=c.get(Calendar.MONTH);
    int mDay=c.get(Calendar.DAY_OF_MONTH);
    Date date=new GregorianCalendar(mYear,mMonth,mDay).getTime();
    Cursor mCursor;


    public DbHelper(Context context) {
        super(context,DB_NAME, null, VERSION);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                  int version) {
        super(context, DB_NAME, factory, version);
    }

    public static void init(Context context){
        ins = new DbHelper(context);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Bd1 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, details TEXT, date TEXT NOT NULL, flag INTEGER, day INTEGER, month INTEGER, year INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DBHELPER", "Database somehow need to upgrade!");
        db.execSQL("DROP TABLE IF EXISTS "+ DB_NAME);
        onCreate(db);
    }

    public static DbHelper getInstance(){
        return ins;
  }

    public List<GoalsHandler> getAllItems(int i) {
        List<GoalsHandler> itemList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DB_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        if(i==0) {
            mCursor = db.rawQuery(selectQuery, null);
        }
        if (i==1) {
            mCursor = db.query(DB_NAME, null, null, null, null, null, colName, null);
        }

        if (mCursor.moveToFirst()) {
            do {
                GoalsHandler listDataHandler = new GoalsHandler();
                listDataHandler.setName(mCursor.getString(1));
                listDataHandler.setDetails(mCursor.getString(2));
                listDataHandler.setDate(mCursor.getString(3));
                listDataHandler.setId(mCursor.getInt(0));
                listDataHandler.setFlag(mCursor.getInt(4));
                itemList.add(listDataHandler);
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return itemList;
    }

    public List<GoalsHandler> getCurrentItems(int i) {
        List<GoalsHandler> itemList = new ArrayList<>();
        String selectQuery="SELECT * FROM " + DB_NAME + " WHERE " + colFlag + " = ?";
        if (i==1) {
            selectQuery = "SELECT * FROM " + DB_NAME + " WHERE " + colFlag + " = ? ORDER BY " + colName;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, new String[] {Integer.toString(0)});

        if (mCursor.moveToFirst()) {
            do {
                int day=mCursor.getInt(5);
                int month=mCursor.getInt(6);
                int year=mCursor.getInt(7);
                Date date2=new GregorianCalendar(year,month,day).getTime();
                if((date2.after(date))||(date2.equals(date))) {
                    GoalsHandler listDataHandler = new GoalsHandler();
                    listDataHandler.setName(mCursor.getString(1));
                    listDataHandler.setDetails(mCursor.getString(2));
                    listDataHandler.setDate(mCursor.getString(3));
                    listDataHandler.setId(mCursor.getInt(0));
                    itemList.add(listDataHandler);
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return itemList;
    }

    public List<GoalsHandler> getDoneItems(int i) {
        List<GoalsHandler> itemList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DB_NAME +" WHERE "+colFlag+" = ? ";
        if (i==1) {
            selectQuery = "SELECT * FROM " + DB_NAME +" WHERE "+colFlag+" = ? ORDER BY "+ colName;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, new String[] {Integer.toString(1)});

        if (mCursor.moveToFirst()) {
            do {
                GoalsHandler listDataHandler = new GoalsHandler();
                listDataHandler.setName(mCursor.getString(1));
                listDataHandler.setDetails(mCursor.getString(2));
                listDataHandler.setDate(mCursor.getString(3));
                listDataHandler.setId(mCursor.getInt(0));
                itemList.add(listDataHandler);
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return itemList;
    }

    public List<GoalsHandler> getMissingItems(int i) {
        List<GoalsHandler> itemList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DB_NAME +" WHERE "+ colFlag + " = ?";
        if (i==1) {
            selectQuery = "SELECT * FROM " + DB_NAME +" WHERE "+ colFlag + " = ? ORDER BY "+colName;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, new String[] { Integer.toString(0)});

        if (mCursor.moveToFirst()) {
            do {
                int day = mCursor.getInt(5);
                int month = mCursor.getInt(6);
                int year = mCursor.getInt(7);
                Date date2 = new GregorianCalendar(year, month, day).getTime();
                if (date2.before(date)) {
                    GoalsHandler listDataHandler = new GoalsHandler();
                    listDataHandler.setName(mCursor.getString(1));
                    listDataHandler.setDetails(mCursor.getString(2));
                    listDataHandler.setDate(mCursor.getString(3));
                    listDataHandler.setId(mCursor.getInt(0));
                    itemList.add(listDataHandler);
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return itemList;
    }


    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_NAME, null, null);
        db.close();
    }


    public int getSizeDBList() {
        List<GoalsHandler> list = getAllItems(0);
        return list.size();
    }



    public void delete(int id) {
        SQLiteDatabase mDb=this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + DB_NAME;
        mCursor = mDb.rawQuery(selectQuery, null);
        mDb.delete(DbHelper.DB_NAME,DbHelper.colId + "=" + id, null);
        mCursor.requery();
        mDb.close();


    }

    public int getWeekAll() {
        Calendar c=Calendar.getInstance();
        int i=0;
        int mDay2=mDay-7;
        Date date2=new GregorianCalendar(mYear,mMonth,mDay2).getTime();
        String selectQuery="SELECT * FROM "+ DB_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, null);

        if (mCursor.moveToFirst()) {
            do {
               int day= mCursor.getInt(5);
               int month= mCursor.getInt(6);
               int year=mCursor.getInt(7);
               Date date3=new GregorianCalendar(year,month,day).getTime();
                if ((date3.after(date2))&&(date3.before(date))) {
                    i++;
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return i;
    }

    public int getWeekDone() {
        Calendar c=Calendar.getInstance();
        int i=0;
        int mDay2=mDay-7;
        Date date2=new GregorianCalendar(mYear,mMonth,mDay2).getTime();
        String selectQuery="SELECT * FROM "+ DB_NAME +" WHERE "+ colFlag + " = ?" ;
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, new String []{Integer.toString(1)});

        if (mCursor.moveToFirst()) {
            do {
                int day= mCursor.getInt(5);
                int month= mCursor.getInt(6);
                int year=mCursor.getInt(7);
                Date date3=new GregorianCalendar(year,month,day).getTime();
                if ((date3.after(date2))&&(date3.before(date))) {
                    i++;
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return i;
    }

    public int getWeekMissing() {
        int i=0;
        int mDay2=mDay-7;
        Date date2=new GregorianCalendar(mYear,mMonth,mDay2).getTime();
        String selectQuery="SELECT * FROM "+ DB_NAME+" WHERE "+ colFlag + " = ?" ;
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, new String[] {Integer.toString(0)});

        if (mCursor.moveToFirst()) {
            do {
                int day= mCursor.getInt(5);
                int month= mCursor.getInt(6);
                int year=mCursor.getInt(7);
                Date date3=new GregorianCalendar(year,month,day).getTime();
                if ((date3.after(date2))&&(date3.before(date))) {
                    i++;
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return i;
    }

    public int getMonthAll() {
        int i=0;
        c.add(Calendar.MONTH,-1);
        int mMonth2=c.get(Calendar.MONTH);
        Date date2=new GregorianCalendar(mYear,mMonth2,mDay).getTime();
        String selectQuery="SELECT * FROM "+ DB_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, null);

        if (mCursor.moveToFirst()) {
            do {
                int day= mCursor.getInt(5);
                int month= mCursor.getInt(6);
                int year=mCursor.getInt(7);
                Date date3=new GregorianCalendar(year,month,day).getTime();
                if ((date3.after(date2))&&(date3.before(date))) {
                    i++;
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return i;
    }


    public int getMonthDone() {
        int i=0;
        c.add(Calendar.MONTH,-1);
        int mMonth2=c.get(Calendar.MONTH);
        Date date2=new GregorianCalendar(mYear,mMonth2,mDay).getTime();
        String selectQuery="SELECT * FROM "+ DB_NAME+" WHERE "+ colFlag + " = ?" ;
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, new String[] {Integer.toString(1)});

        if (mCursor.moveToFirst()) {
            do {
                int day= mCursor.getInt(5);
                int month= mCursor.getInt(6);
                int year=mCursor.getInt(7);
                Date date3=new GregorianCalendar(year,month,day).getTime();
                if ((date3.after(date2))&&(date3.before(date))) {
                    i++;
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return i;
    }


    public int getMonthMissing() {
        int i=0;
        c.add(Calendar.MONTH,-1);
        int mMonth2=c.get(Calendar.MONTH);
        Date date=new GregorianCalendar(mYear,mMonth,mDay).getTime();
        Date date2=new GregorianCalendar(mYear,mMonth2,mDay).getTime();
        String selectQuery="SELECT * FROM "+ DB_NAME+" WHERE "+ colFlag + " = ?" ;
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, new String[] {Integer.toString(0)});

        if (mCursor.moveToFirst()) {
            do {
                int day= mCursor.getInt(5);
                int month= mCursor.getInt(6);
                int year=mCursor.getInt(7);
                Date date3=new GregorianCalendar(year,month,day).getTime();
                if ((date3.after(date2))&&(date3.before(date))) {
                    i++;
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return i;
    }


    public int getYearAll() {
        int i=0;
        c.add(Calendar.YEAR,-1);
        int mYear2=c.get(Calendar.YEAR);
        Date date2=new GregorianCalendar(mYear2,mMonth,mDay).getTime();
        String selectQuery="SELECT * FROM "+ DB_NAME ;
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, null);

        if (mCursor.moveToFirst()) {
            do {
                int day= mCursor.getInt(5);
                int month= mCursor.getInt(6);
                int year=mCursor.getInt(7);
                Date date3=new GregorianCalendar(year,month,day).getTime();
                if ((date3.after(date2))&&(date3.before(date))) {
                    i++;
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return i;
    }


    public int getYearDone() {
        int i=0;
        c.add(Calendar.YEAR,-1);
        int mYear2=c.get(Calendar.YEAR);
        Date date2=new GregorianCalendar(mYear2,mMonth,mDay).getTime();
        String selectQuery="SELECT * FROM "+ DB_NAME+" WHERE "+ colFlag + " = ?" ;
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, new String[] {Integer.toString(1)});

        if (mCursor.moveToFirst()) {
            do {
                int day= mCursor.getInt(5);
                int month= mCursor.getInt(6);
                int year=mCursor.getInt(7);
                Date date3=new GregorianCalendar(year,month,day).getTime();
                if ((date3.after(date2))&&(date3.before(date))) {
                    i++;
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return i;
    }


    public int getYearMissing() {
        int i=0;
        c.add(Calendar.YEAR,-1);
        int mYear2=c.get(Calendar.YEAR);
        Date date2=new GregorianCalendar(mYear2,mMonth,mDay).getTime();
        String selectQuery="SELECT * FROM "+ DB_NAME+" WHERE "+ colFlag + " = ?" ;
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, new String[] {Integer.toString(0)});

        if (mCursor.moveToFirst()) {
            do {
                int day= mCursor.getInt(5);
                int month= mCursor.getInt(6);
                int year=mCursor.getInt(7);
                Date date3=new GregorianCalendar(year,month,day).getTime();
                if ((date3.after(date2))&&(date3.before(date))) {
                    i++;
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return i;
    }

    public ArrayList<String> forEdit(int id) {
        ArrayList<String> value = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DB_NAME +" WHERE "+colId+" = ? ";
        SQLiteDatabase db = this.getReadableDatabase();
        mCursor = db.rawQuery(selectQuery, new String[] {Integer.toString(id)});

        if (mCursor.moveToFirst()) {
            do {
                value.add(mCursor.getString(1));
                value.add(mCursor.getString(2));
                value.add(mCursor.getString(3));
                //value.add(mCursor.getInt(0));
                //itemList.add(listDataHandler);
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return value;
    }

    public int getTodayItems() {
        int i=0;
        String selectQuery="SELECT * FROM " + DB_NAME + " WHERE " + colFlag + " = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        mCursor = db.rawQuery(selectQuery, new String[] {Integer.toString(0)});

        if (mCursor.moveToFirst()) {
            do {
                int day=mCursor.getInt(5);
                int month=mCursor.getInt(6);
                int year=mCursor.getInt(7);
                Date date2=new GregorianCalendar(year,month,day).getTime();
                if(date2.equals(date)) {
                    i++;
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        db.close();

        return i;
    }

}
