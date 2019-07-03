package com.htwg.booksurfing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    private static final String TAG = "AddBookActivity";
    DatabaseHelper databaseHelper;
    public Spinner mySpinnerOwner;
    public Spinner mySpinnerBorrowed;

    EditText textViewA;
    EditText textViewT;
    EditText textViewR;
    EditText textViewTL;
    EditText textViewTS;
    EditText textViewPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listbook_layout);
        Intent intent = getIntent();
        String isbn = intent.getStringExtra("ISBN");
        Log.d(TAG, "Extra value: " + isbn);
        textViewA = findViewById(R.id.editViewAuthor);
        textViewT = findViewById(R.id.editViewTitle);
        textViewR = findViewById(R.id.editViewRating);
        textViewTL = findViewById(R.id.editViewThumbnailL);
        textViewTS = findViewById(R.id.editViewThumbnailS);
        textViewPC = findViewById(R.id.editViewPagecount);
        Log.d(TAG, "Starting Api Request");


        databaseHelper = new DatabaseHelper(this);

        mySpinnerOwner = findViewById(R.id.spinnerOwner);
//        mySpinnerBorrowed = findViewById(R.id.spinnerBorrowed);

        ArrayAdapter<String> myAdapterOwner = new ArrayAdapter<>(AddBookActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Owner));
//        ArrayAdapter<String> myAdapterBorrowed = new ArrayAdapter<>(AddBookActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Borrowed));

        myAdapterOwner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        myAdapterBorrowed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinnerOwner.setAdapter(myAdapterOwner);
//        mySpinnerBorrowed.setAdapter(myAdapterBorrowed);
        if(isbn != null) {
            new ParseApi(this, textViewA, textViewT, textViewR, textViewTL, textViewTS, textViewPC).execute(isbn);
        }

    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void addData(String a, String t, String o, String r, String tl, String ts, String pc) {
        toastMessage(a + t + o + r);
        boolean insertData = databaseHelper.addDataToBookable(a, t, o, r, tl, ts, pc);
        if (insertData==true) {
            toastMessage("Data successfully added");
        } else {
            toastMessage("Something when wrong");
        }
    }

    public void returnHome(View view) {
        Intent i = new Intent(AddBookActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void fabBackToLib(View view) {
        Intent intent = new Intent(AddBookActivity.this, ListDataActivity.class);
        startActivity(intent);
    }

    public void fabSaveToDB(View view) {
        String author = textViewA.getText().toString();
        String title = textViewT.getText().toString();
        String owner = mySpinnerOwner.getSelectedItem().toString();
        String rating = textViewR.getText().toString();
        String thumbnailL = textViewTL.getText().toString();
        String thumbnailS = textViewTS.getText().toString();
        String pageCount = textViewPC.getText().toString();

        if (author.length() != 0 && title.length() != 0 && rating.length() != 0) {
            addData(author, title, owner, rating, thumbnailL, thumbnailS, pageCount);
            textViewA.setText("");
            textViewT.setText("");
            textViewR.setText("");
            textViewPC.setText("");
        } else {
            toastMessage("You must put something into the text field");
        }
    }

    public void fabScan(View view) {
        Intent intent = new Intent(AddBookActivity.this, ScanActivity.class);
        startActivity(intent);
    }
}
