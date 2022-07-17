package com.example.movieranking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class SearchResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Database db = new Database(SearchResults.this);

        ListView results = findViewById(R.id.resultList);

        Bundle extras = getIntent().getExtras();
        String extra = new String(extras.getString("title"));
        String title = extra.replace(" ", "%20");
        String url = new String("https://imdb-api.com/en/API/SearchTitle/k_wwlhrk36/" + title);
        Log.i("request", url);

        Button back = findViewById(R.id.resultBackButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResults.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}