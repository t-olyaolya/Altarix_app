package com.tolyaolya.mygoals;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 111 on 01.07.2016.
 */
public class DbHelper  extends SQLiteOpenHelper{
  //  private static final String NAME = "mybd.sqlite";
    private static DbHelper ins = null;
    private static final int VERSION = 1;

    public DbHelper(Context context) {

      super(context,"Bd.sqlite", null, VERSION);
    }

    public static void init(Context context){
      ins = new DbHelper(context);
    }



  @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Bd1 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, details TEXT, date TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

  public static DbHelper getInstance(){
    return ins;
  }
}
