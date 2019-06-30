package com.example.booksurfing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "book.db";
    private static final String TABLE_NAME = "Book_table";
    private static final String COL0 = "ID";
    private static final String COL1 = "author";
    private static final String COL2 = "title";
    private static final String COL3 = "Owner";
    private static final String COL4 = "Borrowed";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
//        context.deleteDatabase(DATABASE_NAME);
//        context.deleteDatabase(TABLE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " author TEXT, title TEXT, Owner TEXT, Borrowed TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addDataToBookTable(String author, String title, String owner, String borrowed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, author);
        contentValues.put(COL2, title);
        contentValues.put(COL3, owner);
        contentValues.put(COL4, borrowed);


        Log.d(TAG, "addData: Adding " + author + " " + title + " " + owner + " " + borrowed + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //get all Data from DB
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
