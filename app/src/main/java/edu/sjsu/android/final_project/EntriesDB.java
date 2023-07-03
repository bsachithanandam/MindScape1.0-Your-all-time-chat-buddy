package edu.sjsu.android.final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EntriesDB extends SQLiteOpenHelper {
    static  String TABLE_NAME = "EntriesDB";
    static String DATABASE_NAME = "EntriesDB";
    // Column names - following four.
    protected static String ID = "_id";
    protected static String DATE = "date";
    protected  static  String MONTH = "month";
    protected static String YEAR = "year";
    protected static String MOOD = "mood";

    public EntriesDB(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sq) {
        String command = String.format("CREATE TABLE %s (" + "%s INTEGER PRIMARY KEY AUTOINCREMENT, " + "%s STRING NOT NULL, " + "%s STRING NOT NULL, "+"%s STRING NOT NULL, "+"%s STRING NOT NULL);",TABLE_NAME,ID,DATE,MONTH,YEAR,MOOD);
        sq.execSQL(command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sq, int i, int i1) {
        sq.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sq);
    }

    public long insert(ContentValues values){
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME,null,values);
    }

    public int deleteAll(){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME,null,null);
    }

    public Cursor getAllLocations(){
        //TODO - Implement to get all data.
        SQLiteDatabase database = getWritableDatabase();
        return database.query(TABLE_NAME,
                new String[]{ID, DATE, MONTH,YEAR,MOOD},
                null, null, null, null, ID);
    }
}
