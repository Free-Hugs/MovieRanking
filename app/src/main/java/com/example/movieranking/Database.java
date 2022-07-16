package com.example.movieranking;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_FILE_NAME = "movies";

    private static final String DATABASE_TABLE_NAME = "ranking";
    private static final String PKEY = "id";
    private static final String COL1 = "title";
    private static final String COL2 = "director";
    private static final String COL3 = "rate";

    private static final String DATABASE_TABLE2_NAME = "mustwatch";
    private static final String PKEY2 = "id";
    private static final String COL1_2 = "title";
    private static final String COL2_2 = "director";

    Database(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    /**
     * SQL Creation of Database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_NAME + " (" +
                PKEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL1 + " TEXT," +
                COL2 + " TEXT," +
                COL3 + " DOUBLE);";
        String DATABASE_TABLE2_CREATE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE2_NAME + " (" +
                PKEY2 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL1_2 + " TEXT," +
                COL2_2 + " TEXT);";
        db.execSQL(DATABASE_TABLE_CREATE);
        db.execSQL(DATABASE_TABLE2_CREATE);
    }

    /**
     * SQL Update of Database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) { // Upgrade pas très fin
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
            onCreate(db);
        }
    }

    /**
     * SQL Insert info in Database
     */
    public void insertMovieToRanking(String title, String director)
    {
        Log.i("Update wallet","Insert in database");
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(COL1, title);
        values.put(COL2, director);
        values.put(COL3, 21);
        db.insertOrThrow(DATABASE_TABLE_NAME,null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void insertMustWatchMovie(String title, String director)
    {
        Log.i("Insert operation","Insert in database");
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(COL1_2, title);
        values.put(COL2_2, director);
        db.insertOrThrow(DATABASE_TABLE2_NAME,null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void rateAMovie(SQLiteDatabase db, String title, String director, double rate) {
        String RATE_MOVIE = "UPDATE " + DATABASE_TABLE_NAME +
                " SET " + rate +
                " WHERE " +
                COL1 + " " + title + " " +
                COL2 + " " + director + ";";
        db.execSQL(RATE_MOVIE);
    }

    public void highRateTitles(ArrayAdapter<String> list) {
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME + " ORDER BY " + COL3 + " DESC");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(cursor.getString((cursor.getColumnIndex(COL1))));
            } while (cursor.moveToNext());
        }
    }

    public void highRateDirectors(ArrayAdapter<String> list) {
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME + " ORDER BY " + COL3 + " DESC");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(cursor.getString((cursor.getColumnIndex(COL2))));
            } while (cursor.moveToNext());
        }
    }

    public void highRates(ArrayAdapter<String> list) {
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME + " ORDER BY " + COL3 + " DESC");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(cursor.getString((cursor.getColumnIndex(COL3))));
            } while (cursor.moveToNext());
        }
    }
}
