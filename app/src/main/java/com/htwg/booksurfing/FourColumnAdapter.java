package com.htwg.booksurfing;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class FourColumnAdapter extends ArrayAdapter<Book> implements Filterable {

    private LayoutInflater mInflater;
    private ArrayList<Book> books;
    private ArrayList<Book> tmpArray;
    private final Activity context;
    FourColumnAdapterFilter fcFilter;

    public FourColumnAdapter(Activity context, ArrayList<Book> books) {
        super(context, R.layout.list_single, books);
        this.context = context;
        this.books = books;
        this.tmpArray = books;
    }

    public View getView(int position, View View, ViewGroup parent) {
        mInflater = context.getLayoutInflater();
        View rowView = mInflater.inflate(R.layout.list_single, null, true);
        Book book = books.get(position);

        if (book != null) {
            TextView id = rowView.findViewById(R.id.txtID);
            TextView author = rowView.findViewById(R.id.txtAuthor);
            TextView title = rowView.findViewById(R.id.txtTitle);
            TextView owner = rowView.findViewById(R.id.txtOwner);
            TextView rating = rowView.findViewById(R.id.txtRating);
            if (id != null) {
                Integer bookID = book.getId();
                String bookIDString = "" + bookID;
                id.setText(bookIDString);
            }
            if (author != null) {
                author.setText(book.getAuthor());
            }
            if (title != null) {
                title.setText((book.getTitle()));
            }
            if (owner != null) {
                owner.setText((book.getOwner()));
            }
            if (rating != null) {
                rating.setText((book.getRating()));
            }

        }

        return rowView;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Filter getFilter() {
        if (fcFilter == null) {
            fcFilter = new FourColumnAdapterFilter();
        }
        return fcFilter;
    }

    class FourColumnAdapterFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults res = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString();
                ArrayList<Book> filter = new ArrayList<>();


                for (int i = 0; i < tmpArray.size(); i++) {
                    if (tmpArray.get(i).getAuthor().contains(constraint) || tmpArray.get(i).getTitle().contains(constraint)) {
                        Book newBook = new Book(tmpArray.get(i).getId(), tmpArray.get(i).getAuthor(),
                                tmpArray.get(i).getTitle(), tmpArray.get(i).getOwner(), tmpArray.get(i).getRating(),
                                tmpArray.get(i).getThumbnailSmall(), tmpArray.get(i).getThumbnail(), tmpArray.get(i).getPageCount());

                        filter.add(newBook);
                    }
                }

                res.count = filter.size();
                res.values = filter;
            } else {
                res.count = tmpArray.size();
                res.values = tmpArray;
            }
            return res;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            books = (ArrayList<Book>)results.values;
            notifyDataSetChanged();
        }
    }
}
