package com.example.cleaneats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private SearchView search;

    String keyword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Restaurants");

        Intent intent = getIntent();
        if (intent != null) {
            keyword = intent.getStringExtra("keyword").toLowerCase();
        }

        search = findViewById(R.id.search);

        recyclerView = findViewById(R.id.rv_mainActivity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RestaurantAdapter(this);
        recyclerView.setAdapter(adapter);

        readScores();
        adapter.setNumbers(restaurantInfos);
    }

    private List<RestaurantInfo> restaurantInfos = new ArrayList<>();

    private void readScores() {
        InputStream is = getResources().openRawResource(R.raw.shelby);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            // Step over headers
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Log.d("MyActivity", "Line: " + line);
                // Split by ','
                String[] tokens = line.split(",");

                String searchName = tokens[0].toLowerCase();
                RestaurantInfo sample = new RestaurantInfo();

                //Only add the restaurants that have names that match the search keyword
                if(searchName.contains(keyword)) {
                    // Read the data
                    sample.setName(tokens[0]);
                    sample.setAddress(tokens[1]);
                    sample.setDate(tokens[2]);
                    sample.setScore(Integer.parseInt(tokens[3]));
                    restaurantInfos.add(sample);
                } else if (keyword.equals("")) { //If keyword is empty then just display every restaurant
                    // Read the data
                    sample.setName(tokens[0]);
                    sample.setAddress(tokens[1]);
                    sample.setDate(tokens[2]);
                    sample.setScore(Integer.parseInt(tokens[3]));
                    restaurantInfos.add(sample);
                }

            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line " + line, e);
            e.printStackTrace();
        }
    }
}