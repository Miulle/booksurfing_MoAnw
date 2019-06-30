package com.example.booksurfing;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity  extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper databaseHelper;

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        listView = findViewById(R.id.ListView);
        databaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView");

        Cursor data = databaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()) {
            //get data from DB from COL1
            listData.add(data.getString(1));
        }
        //ListAdapter to add data to listView
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void AddBockButton(View view) {
        Intent intent = new Intent(ListDataActivity.this, AddBookActivity.class);
        startActivity(intent);
    }
}
