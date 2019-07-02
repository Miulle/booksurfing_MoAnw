package com.htwg.booksurfing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 2/28/2017.
 */

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete;
    private TextView tViewA;
    private TextView tViewT;
    private TextView tViewO;
    private TextView tViewR;
    private TextView tViewTL;
    private TextView tViewTS;


    DatabaseHelper databaseHelper;

    private String author;
    private String title;
    private String owner;
    private String rating;
    private String thumbnail;
    private String thumbnailSmall;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);

        Log.d(TAG, "starting onCreate");

        btnDelete = findViewById(R.id.btnDelete);
        tViewA = findViewById(R.id.tViewA);
        tViewT = findViewById(R.id.tViewT);
        tViewO = findViewById(R.id.tViewO);
        tViewR = findViewById(R.id.tViewR);
        tViewTL = findViewById(R.id.tViewTL);
        tViewTS = findViewById(R.id.tViewTS);
        databaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();
        author = receivedIntent.getStringExtra("author");
        title = receivedIntent.getStringExtra("title");
        owner = receivedIntent.getStringExtra("owner");
        rating = receivedIntent.getStringExtra("rating");
        thumbnail = receivedIntent.getStringExtra("thumbnail");
        thumbnailSmall = receivedIntent.getStringExtra("thumbnailSmall");

        //set the text to show the current selected name
        tViewA.setText(author);
        tViewT.setText(title);
        tViewO.setText(owner);
        tViewR.setText(rating);
        tViewTL.setText(thumbnail);
        tViewTS.setText(thumbnailSmall);
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public void deleteButton(View view) {
        if (tViewT.length() == 0) {
            toastMessage("no book to delete!");
        }
        databaseHelper.delete(author, title);
        tViewA.setText("");
        tViewT.setText("");
        tViewO.setText("");
        tViewR.setText("");
        tViewTL.setText("");
        tViewTL.setText("");
        tViewTS.setText("");
        toastMessage("removed from database");
    }

    public void backButton(View view) {
        Intent i = new Intent(EditDataActivity.this, ListDataActivity.class);
        startActivity(i);
    }

    public void thumbNailL(View view) {
        String urlTL = tViewTL.getText().toString();
        if (urlTL.length() == 0) {
            toastMessage("no url found");
        } else {
//            toastMessage(urlTL);
            Intent i = new Intent(this, WebViewActivity.class);
            i.putExtra("url", urlTL);
            startActivity(i);
        }
    }

    public void thumbnailS(View view) {
        String urlTS = tViewTS.getText().toString();
        if (urlTS.length() == 0) {
            toastMessage("no url found");
        } else {
//            toastMessage(urlTS);
            Intent i = new Intent(this, WebViewActivity.class);
            i.putExtra("url", urlTS);
            startActivity(i);
        }
    }
}