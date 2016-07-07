package com.tolyaolya.mygoals;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 111 on 01.07.2016.
 */
public class DbHelper  extends SQLiteOpenHelper{
  //  private static final String NAME = "mybd.sqlite";
    private static DbHelper ins = null;
    private static final int VERSION = 1;
    public static final String DB_NAME ="Bd1";
    public static final String colId = "_id";
    public static final String colName = "name";
    public static final String colDetails = "details";
    public static final String colDate= "date";
    public static final String colFlag = "flag";

    public DbHelper(Context context) {

      super(context,"Bd.sqlite", null, VERSION);
    }

    public static void init(Context context){

      ins = new DbHelper(context);
    }



  @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Bd1 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, details TEXT, date TEXT NOT NULL, flag INTEGER);");
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

  // курсор с результатами всех записей

 /* public Cursor getAllTodos() {
    SQLiteDatabase db = this.getWritableDatabase();
    return db.query(DB_NAME, new String[] { "_id",
                    "name", "details", "date", "flag" }, null,
            null, null, null, null);
  } */
 public  int getRowsQuantity (String table) throws SQLException {
   String countQuery = "SELECT * FROM " + table;
   SQLiteDatabase db = this.getReadableDatabase();
   Cursor cursor = db.rawQuery(countQuery, null);
   int cnt = cursor.getCount();
   return cnt;
 }
}
