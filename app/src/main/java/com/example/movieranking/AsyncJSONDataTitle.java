package com.example.movieranking;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncJSONDataTitle extends AsyncTask<String, Void, JSONObject> {
    private ArrayAdapter<String> myTitleAdapter;
    private ArrayAdapter<String> myIdAdapter;
    private AppCompatActivity myActivity;

    public AsyncJSONDataTitle(ArrayAdapter adapter, ArrayAdapter idAdapter, AppCompatActivity myActivity_){
        myActivity = myActivity_;
        myIdAdapter = idAdapter;
        myTitleAdapter = adapter;
    }

    protected JSONObject doInBackground(String... strings){
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = readStream(in); // Read stream
        urlConnection.disconnect();
        JSONObject json = null;
        try {
            json = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    protected void onPostExecute(JSONObject j) {
        try {
            JSONArray titles = j.getJSONArray("results");
            for (int i = 0; i < titles.length(); i++)
            {
                myTitleAdapter.add(titles.getJSONObject(i).getString("title"));
                myIdAdapter.add(titles.getJSONObject(i).getString("id"));
            }
            for(int k=0; k<myTitleAdapter.getCount(); k++){
                Log.i("title", myTitleAdapter.getItem(k));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
