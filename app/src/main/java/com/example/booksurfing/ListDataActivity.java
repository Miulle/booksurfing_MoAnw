package com.example.booksurfing;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity  extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper databaseHelper;
    ListView listView;
    Book book;
    ArrayList<Book> bookData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "created ListDataActivity");
        setContentView(R.layout.list_layout);
        databaseHelper = new DatabaseHelper(this);
        populateListView();
    }

    private void populateListView() {
        bookData = new ArrayList<>();
        Cursor data = databaseHelper.getAllData();
        int numRows = data.getCount();
        if(numRows == 0) {
            Toast.makeText(ListDataActivity.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while(data.moveToNext()) {
                //get data from DB from COL1
                book = new Book(data.getString(1), data.getString(2), data.getString(3), data.getString(4));
                bookData.add(i, book);
                i++;
            }
            FourColumnAdapter adapter = new FourColumnAdapter(this, bookData);
            listView = findViewById(R.id.ListView2);
            listView.setAdapter(adapter);
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void AddBockButton(View view) {
        Intent intent = new Intent(ListDataActivity.this, AddBookActivity.class);
        startActivity(intent);
    }
}
