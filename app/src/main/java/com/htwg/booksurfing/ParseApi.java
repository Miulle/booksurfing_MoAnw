package com.htwg.booksurfing;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ParseApi extends AsyncTask<String, Void, String> {
    //TODO implement Google Books API

    private static final String TAG = "ParseApi";

    private EditText mTitleText;
    private EditText mAuthorText;
    private EditText mRatingText;
    private EditText mTLText;
    private EditText mTSText;

    BufferedReader reader;
    InputStream iStream;
    StringBuffer stringBuffer;
    Context mcontext;

    public ParseApi(Context context, EditText authorText, EditText titleText, EditText ratingText, EditText TLText, EditText TSText) {
        this.mcontext = context;
        this.mAuthorText = authorText;
        this.mTitleText = titleText;
        this.mRatingText = ratingText;
        this.mTLText = TLText;
        this.mTSText = TSText;

    }

    @Override
    protected String doInBackground(String... strings) {
        //TODO get request
        String apiUrlString = "https://www.googleapis.com/books/v1/volumes?q=";
        String isbnParameter = "isbn:";
        HttpURLConnection connection = null;

        //TODO sharedPreferences
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(mcontext);
        String apiKey = mSettings.getString(SettingsActivity.PREF_API_KEY, "AIzaSyD2z6y5CfLw3R7dk5-0sJR3Ss2nP7QLd5Q");


        try {

            URL url1 = new URL(apiUrlString + isbnParameter + strings[0]);

            connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            iStream = connection.getInputStream();
            if (iStream == null) {
                return null;
            }
            stringBuffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(iStream));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }

            if (stringBuffer.length() == 0) {
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            if(reader != null) {
                try{
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //Log.d(TAG, stringBuffer.toString());
            return stringBuffer.toString();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        String author = null;
        String title = null;
        String smallThumbNail = null;
        String largeThumbNail = null;
        String rating = null;
        try {
            super.onPostExecute(s);
            JSONObject parseJson = new JSONObject(s);
            JSONArray items = parseJson.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject book = items.getJSONObject(i);
                Log.d(TAG, "BOOK ITEMS: " + book);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                Log.d(TAG, "VOLUMNE ITEMS: " + volumeInfo);

                try {
                    author = volumeInfo.getString("authors");
                    title = volumeInfo.getString("title");
                    rating = volumeInfo.getString("averageRating");
                    smallThumbNail = imageLinks.getString("smallThumbnail");
                    Log.d(TAG, "Small Thumbnail: " + smallThumbNail);
                    largeThumbNail = imageLinks.getString("thumbnail");
                    Log.d(TAG, "Large Thumbnail: " + largeThumbNail);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (title != null && author != null) {
                    Log.d(TAG, "Setting texts " + title + " " + author + " " + rating + " " + smallThumbNail + " " + largeThumbNail);
                    mTitleText.setText(title);
                    mAuthorText.setText(author);
                    mRatingText.setText(rating);
                    mTLText.setText(largeThumbNail);
                    mTSText.setText(smallThumbNail);
                    return;
                }

            }
        } catch (Exception e) {
            mTitleText.setText("");
            e.printStackTrace();
        }

    }


}