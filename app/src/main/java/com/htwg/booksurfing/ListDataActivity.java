package com.htwg.booksurfing;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity  extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper databaseHelper;
    ListView listView;
    Book book;
    ArrayList<Book> bookData;
    EditText eView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "created ListDataActivity");
        setContentView(R.layout.list_layout);
        listView = findViewById(R.id.ListView2);
        databaseHelper = new DatabaseHelper(this);
        populateListView();
    }

    private void populateListView() {
        bookData = new ArrayList<>();
        Cursor data1 = databaseHelper.getAllData();
        int numRows = data1.getCount();
        if(numRows == 0) {
            Toast.makeText(ListDataActivity.this,"The Database is empty  :(.", Toast.LENGTH_LONG).show();
        } else {
            Log.d(TAG, "NUMROWS = " + numRows);
            int i = 0;
            while(data1.moveToNext()) {
                book = new Book(data1.getInt(0), data1.getString(1), data1.getString(2), data1.getString(3), data1.getString(4), data1.getString(5), data1.getString(6));
                bookData.add(i, book);
                i++;
            }


        }
        FourColumnAdapter adapter = new FourColumnAdapter(this, bookData);
        listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Book book = (Book) parent.getItemAtPosition(position);
                    Integer bookId = book.getId();
                    Toast.makeText(ListDataActivity.this, "You Clicked at " + bookId, Toast.LENGTH_SHORT).show();
                    searchBookId(bookId);
                }
            });

    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void AddBookButton(View view) {
        Intent intent = new Intent(ListDataActivity.this, AddBookActivity.class);
        startActivity(intent);
    }

    public void searchBookId(Integer bookid) {
        Log.d(TAG, bookid.toString());

        String author;
        String title;
        String owner;
        String rating;
        String thumbnail;
        String thumbnailSmall;

        Cursor data = databaseHelper.getItemByID(bookid.toString());
        Log.d(TAG, "found item " + data.getCount());
        if (data.getCount() == 0) {
            toastMessage("Book not found!");
        } else {
            while (data.moveToNext()) {
                Log.d(TAG, "INDEX 0 " + data.getString(0));
                Log.d(TAG, "INDEX 1 " + data.getString(1));
                author = data.getString(1);
                Log.d(TAG, "INDEX 2 " + data.getString(2));
                title = data.getString(2);
                Log.d(TAG, "INDEX 3 " + data.getString(3));
                owner = data.getString(3);
                Log.d(TAG, "INDEX 4 " + data.getString(4));
                rating = data.getString(4);
                Log.d(TAG, "INDEX 5 " + data.getString(5));
                thumbnail = data.getString(5);
                Log.d(TAG, "INDEX 6 " + data.getString(6));
                thumbnailSmall = data.getString(6);
                Intent i = new Intent(ListDataActivity.this, EditDataActivity.class);
                i.putExtra("author", author);
                i.putExtra("title", title);
                i.putExtra("owner", owner);
                i.putExtra("rating", rating);
                i.putExtra("thumbnail", thumbnail);
                i.putExtra("thumbnailSmall", thumbnailSmall);

                startActivity(i);
            }
        }
    }

    /*public void searchBook(View view) {

        String author;
        String title;
        String owner;
        String rating;
        String thumbnail;
        String thumbnailSmall;
        eView = findViewById(R.id.eViewSearch);
        String bookID = eView.getText().toString();
        if (bookID.length() == 0) {
            toastMessage("You must put an ID into the text field");
        } else {
            if (bookID.matches("-?\\d+")) {
                Log.d(TAG, bookID);
                //TODO search for ID instead
                Cursor data = databaseHelper.getItemByID(bookID);
                Log.d(TAG, "found item " + data.getCount());
                if (data.getCount() == 0) {
                    toastMessage("Book not found!");
                } else {
                    while (data.moveToNext()) {
                        Log.d(TAG, "INDEX 0 " + data.getString(0));
                        Log.d(TAG, "INDEX 1 " + data.getString(1));
                        author = data.getString(1);
                        Log.d(TAG, "INDEX 2 " + data.getString(2));
                        title = data.getString(2);
                        Log.d(TAG, "INDEX 3 " + data.getString(3));
                        owner = data.getString(3);
                        Log.d(TAG, "INDEX 4 " + data.getString(4));
                        rating = data.getString(4);
                        Log.d(TAG, "INDEX 5 " + data.getString(5));
                        thumbnail = data.getString(5);
                        Log.d(TAG, "INDEX 6 " + data.getString(6));
                        thumbnailSmall = data.getString(6);
                        Intent i = new Intent(ListDataActivity.this, EditDataActivity.class);
                        i.putExtra("author", author);
                        i.putExtra("title", title);
                        i.putExtra("owner", owner);
                        i.putExtra("rating", rating);
                        i.putExtra("thumbnail", thumbnail);
                        i.putExtra("thumbnailSmall", thumbnailSmall);

                        startActivity(i);
                    }
                } //else {
//                toastMessage("More than 1 Book found!");
//            }

            } else {
                Log.d(TAG, bookID);
                //TODO search for ID instead
                Cursor data = databaseHelper.getItemByTitle(bookID);
                Log.d(TAG, "found item " + data.getCount());
                if (data.getCount() == 0) {
                    toastMessage("Book not found!");
                } else {
                    while (data.moveToNext()) {
                        Log.d(TAG, "INDEX 0 " + data.getString(0));
                        Log.d(TAG, "INDEX 1 " + data.getString(1));
                        author = data.getString(1);
                        Log.d(TAG, "INDEX 2 " + data.getString(2));
                        title = data.getString(2);
                        Log.d(TAG, "INDEX 3 " + data.getString(3));
                        owner = data.getString(3);
                        Log.d(TAG, "INDEX 4 " + data.getString(4));
                        rating = data.getString(4);
                        Log.d(TAG, "INDEX 5 " + data.getString(5));
                        thumbnail = data.getString(5);
                        Log.d(TAG, "INDEX 6 " + data.getString(6));
                        thumbnailSmall = data.getString(6);
                        Intent i = new Intent(ListDataActivity.this, EditDataActivity.class);
                        i.putExtra("author", author);
                        i.putExtra("title", title);
                        i.putExtra("owner", owner);
                        i.putExtra("rating", rating);
                        i.putExtra("thumbnail", thumbnail);
                        i.putExtra("thumbnailSmall", thumbnailSmall);

                        startActivity(i);
                    }
                }
            }
        }



    }*/

    public void returnHome(View view) {
        Intent i = new Intent(ListDataActivity.this, MainActivity.class);
        startActivity(i);
    }

//    public void delete(View view) {
//        eView = findViewById(R.id.eViewDelete);
//        String bookToDelete = eView.getText().toString();
//        Log.d(TAG, bookToDelete);
//        databaseHelper.delete(bookToDelete);
//    }
}
