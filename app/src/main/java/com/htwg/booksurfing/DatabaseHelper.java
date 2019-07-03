package com.htwg.booksurfing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ScrollView;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "book.db";
    private static final String TABLE_NAME = "Book_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "author";
    private static final String COL3 = "title";
    private static final String COL4 = "owner";
    private static final String COL5 = "rating";
    private static final String COL6 = "thumbnail";
    private static final String COL7 = "thumbnailSmall";
    private static final String COL8 = "pageCount";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
//        context.deleteDatabase(DATABASE_NAME);
//        context.deleteDatabase(TABLE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " author TEXT, title TEXT, Owner TEXT, Rating TEXT, Thumbnail TEXT, ThumbnailSmall TEXT, " +
                "pageCount TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addDataToBookable(String author, String title, String owner, String rating, String thumbnail, String thumbnailSmall, String pageCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, author);
        contentValues.put(COL3, title);
        contentValues.put(COL4, owner);
        contentValues.put(COL5, rating);
        contentValues.put(COL6, thumbnail);
        contentValues.put(COL7, thumbnailSmall);
        contentValues.put(COL8, pageCount);


        Log.d(TAG, "addData: Adding " + author + " " + title + " " + owner + " " + rating + " " + thumbnail + " to " + TABLE_NAME);

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

    public Cursor getItemByTitle(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL3 + " = '" + title + "'";
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL3 + " = '" + title + "'", null);
        return data;
    }

    public Cursor getItemByID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'";
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'", null);
        return data;
    }

    public void delete(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "Deleting " + title);
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL3 + " = '" + title + "'";
        Log.d(TAG, "Executing " + query);
        db.execSQL(query);
    }

    public void delete(String author, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "Deleting " + author +  " " + title);
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL3 + " = '" + title + "' AND " + COL2 +" = '" + author + "'";
        Log.d(TAG, "Executing " + query);
        db.execSQL(query);
    }
}
