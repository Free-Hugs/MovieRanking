package com.example.movieranking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        String extra = new String(extras.getString("title"));
        String title = extra.replace(" ", "%20");
        String url = new String("https://imdb-api.com/en/API/SearchTitle/k_wwlhrk36/" + title);
        Log.i("URL", url);


    }
}