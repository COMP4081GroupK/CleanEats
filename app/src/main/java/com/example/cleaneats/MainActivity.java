package com.example.cleaneats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private EditText keywordEditTextMain;
    private Button searchButton;

    String keyword = "";

    private List<RestaurantInfo> restaurantInfos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Restaurants");

        Intent intent = getIntent();
        if (intent != null) {
            keyword = intent.getStringExtra("keyword").toLowerCase();
        }

        //This lets the user filter results from the main page itself
        keywordEditTextMain = findViewById(R.id.mainPageActivity_keyword);
        searchButton = findViewById(R.id.bt_mainPageActivity_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyword = keywordEditTextMain.getText().toString();

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("keyword", keyword);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.rv_mainActivity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RestaurantAdapter(this);
        recyclerView.setAdapter(adapter);

        readScores();
        adapter.setRestaurants(restaurantInfos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_sort_ascending:
                selectionSortAscending();
                return true;
            case R.id.action_sort_descending:
                selectionSortDescending();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

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

    private void selectionSortAscending() {
        int n = restaurantInfos.size();

        for (int i = 0; i < n-1; i++)
        {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if(restaurantInfos.get(j).getScore() < restaurantInfos.get(minIndex).getScore()) {
                    minIndex = j;
                }

            RestaurantInfo min = restaurantInfos.get(minIndex);
            RestaurantInfo org = restaurantInfos.get(i);

            restaurantInfos.remove(i);
            restaurantInfos.add(i, min);

            restaurantInfos.remove(minIndex);
            restaurantInfos.add(minIndex, org);
            }
        }

        adapter.setRestaurants(restaurantInfos);
    }

    private void selectionSortDescending() {
        int n = restaurantInfos.size();

        for (int i = 0; i < n-1; i++)
        {
            int maxIndex = i;
            for (int j = i+1; j < n; j++) {
                if(restaurantInfos.get(j).getScore() > restaurantInfos.get(maxIndex).getScore()) {
                    maxIndex = j;
                }

                RestaurantInfo min = restaurantInfos.get(maxIndex);
                RestaurantInfo org = restaurantInfos.get(i);

                restaurantInfos.remove(i);
                restaurantInfos.add(i, min);

                restaurantInfos.remove(maxIndex);
                restaurantInfos.add(maxIndex, org);
            }
        }

        adapter.setRestaurants(restaurantInfos);
    }
}