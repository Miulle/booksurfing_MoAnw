package com.example.booksurfing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    public Spinner mySpinnerOwner;
    public Spinner mySpinnerBorrowed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listbook_layout);

        databaseHelper = new DatabaseHelper(this);

        mySpinnerOwner = findViewById(R.id.spinnerOwner);
        mySpinnerBorrowed = findViewById(R.id.spinnerBorrowed);

        ArrayAdapter<String> myAdapterOwner = new ArrayAdapter<>(AddBookActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Owner));
        ArrayAdapter<String> myAdapterBorrowed = new ArrayAdapter<>(AddBookActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Borrowed));

        myAdapterOwner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myAdapterBorrowed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinnerOwner.setAdapter(myAdapterOwner);
        mySpinnerBorrowed.setAdapter(myAdapterBorrowed);
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //TODO save into DB
    public void saveDataToDB(View view) {
        TextView textView = findViewById(R.id.editViewAuthor);
        TextView textView2 = findViewById(R.id.editViewTitle);
        String author = textView.getText().toString();
        String title = textView2.getText().toString();
        String owner = mySpinnerOwner.getSelectedItem().toString();
        String borrowed = mySpinnerBorrowed.getSelectedItem().toString();

        if (author.length() != 0 && title.length() != 0) {
            addData(author, title, owner, borrowed);
            textView.setText("");
            textView2.setText("");
        } else {
            toastMessage("You must put something into the text field");
        }
    }

    //TODO scan ISBN
    public void scanIsbn(View view) {
    }

    public void addData(String a, String t, String o, String b) {
        toastMessage(a + t + o + b);
        boolean insertData = databaseHelper.addDataToBookTable(a, t, o, b);
        if (insertData==true) {
            toastMessage("Data successfully added");
        } else {
            toastMessage("Something when wrong");
        }
    }

    public void backToLib(View view) {
        Intent intent = new Intent(AddBookActivity.this, ListDataActivity.class);
        startActivity(intent);
    }
}
