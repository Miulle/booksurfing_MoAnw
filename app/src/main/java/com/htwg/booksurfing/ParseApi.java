package com.htwg.booksurfing;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

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
    private EditText mPCText;

    BufferedReader reader;
    InputStream iStream;
    StringBuffer stringBuffer;

    Context mcontext;

    public ParseApi(Context context, EditText authorText, EditText titleText, EditText ratingText, EditText tLText, EditText tSText, EditText pCText) {
        this.mcontext = context;
        this.mAuthorText = authorText;
        this.mTitleText = titleText;
        this.mRatingText = ratingText;
        this.mTLText = tLText;
        this.mTSText = tSText;
        this.mPCText = pCText;
    }

    @Override
    protected String doInBackground(String... strings) {
        //TODO get request

        String apiUrlString = "https://www.googleapis.com/books/v1/volumes?q=";
        String isbnParameter = "isbn:";
        HttpURLConnection connection = null;

        //TODO sharedPreferences
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(mcontext);
        String apiKey = mSettings.getString("pref_api_key", "@string/pref_default_display_name");
//        MyPreferenceFragment myf = new MyPreferenceFragment();
//        String apiKey = myf.PREF_API_KEY;
        Log.d(TAG, apiKey);

        try {

            // Api-key sometimes not working...
            URL apiUrl = new URL(apiUrlString + isbnParameter + strings[0]); // + "&key=" + apiKey);

            Log.d(TAG, "URL: "+ apiUrl);
            connection = (HttpURLConnection) apiUrl.openConnection();
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
            if (stringBuffer == null) {
//                mAuthorText.setText("No book found on Google Books Api...");
                return null;
            }
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
        String pageCount = null;
        try {
            super.onPostExecute(s);
            JSONObject parseJson = new JSONObject(s);
            if (parseJson.has("items") != true) {
                mAuthorText.setText("No book found on Google Books Api...");
                return;
            }
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
                    pageCount = volumeInfo.getString("pageCount");

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
                    mPCText.setText(pageCount);
                    return;
                }

            }
        } catch (Exception e) {
            mTitleText.setText("");
            e.printStackTrace();
        }

    }
}