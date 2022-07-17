package com.example.movieranking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class SearchResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Database db = new Database(SearchResults.this);

        ListView results = findViewById(R.id.resultList);

        Bundle extras = getIntent().getExtras();
        String title = new String(extras.getString("title"));

        String url = new String("https://imdb-api.com/en/API/SearchTitle/k_wwlhrk36/" + title);
    }
}