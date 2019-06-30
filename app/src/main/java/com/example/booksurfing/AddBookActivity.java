package com.example.booksurfing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listbook_layout);

        Spinner mySpinnerOwner = findViewById(R.id.spinnerOwner);
        Spinner mySpinnerBorrowed = findViewById(R.id.spinnerBorrowed);

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

}
