package com.example.booksurfing;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FourColumnAdapter extends ArrayAdapter<Book> {

    private LayoutInflater mInflater;
    private ArrayList<Book> books;
    private final Activity context;

    public FourColumnAdapter(Activity context, ArrayList<Book> books) {
        super(context, R.layout.list_single, books);
        this.context = context;
        this.books = books;
    }

    public View getView(int position, View View, ViewGroup parent) {
        mInflater = context.getLayoutInflater();
        View rowView = mInflater.inflate(R.layout.list_single, null, true);
        Book book = books.get(position);

        if (book != null) {
            TextView author = rowView.findViewById(R.id.txtAuthor);
            TextView title = rowView.findViewById(R.id.txtTitle);
            TextView owner = rowView.findViewById(R.id.txtOwner);
            TextView borrowed = rowView.findViewById(R.id.txtBorrowed);
            if (author != null) {
                author.setText(book.getAuthor());
            }
            if (title != null) {
                title.setText((book.getTitle()));
            }
            if (owner != null) {
                owner.setText((book.getOwner()));
            }
            if (borrowed != null) {
                borrowed.setText((book.getBorrowed()));
            }

        }

        return rowView;
    }
}
