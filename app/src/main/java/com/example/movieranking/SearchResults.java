package com.example.movieranking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchResults extends AppCompatActivity {
    private ArrayList<HashMap> movies;

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

        ArrayAdapter<String> titles = new ArrayAdapter<String>(results.getContext(), R.layout.result_list_layout);
        ArrayAdapter<String> ids = new ArrayAdapter<String>(results.getContext(), R.layout.result_list_layout);
        ArrayAdapter<String> directors = new ArrayAdapter<String>(results.getContext(), R.layout.result_list_layout);

        AsyncJSONDataTitle resultTitle = new AsyncJSONDataTitle(titles, ids, SearchResults.this);
        resultTitle.execute(url);

        populateList(titles, directors);
        ResultsAdapter adapter = new ResultsAdapter(this, movies);

        results.setAdapter(adapter);

        Button back = findViewById(R.id.resultBackButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResults.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void populateList(ArrayAdapter<String> titles, ArrayAdapter<String> directors) {
        movies = new ArrayList<HashMap>();
        for(int i = 0; i<titles.getCount(); i++){
            HashMap temp = new HashMap();
            temp.put("First", String.valueOf(titles.getItem(i)));
            temp.put("Second", String.valueOf(directors.getItem(i)));
            movies.add(temp);
        }
    };
}