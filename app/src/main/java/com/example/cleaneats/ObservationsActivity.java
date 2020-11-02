package com.example.cleaneats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ObservationsActivity extends AppCompatActivity {

    String address = "";
    String name = "";
    int score = 0;
    List<String> observations = new ArrayList<>();

    private TextView restaurantName;
    private TextView restaurantScore;
    private TextView restaurantAddress;

    private RecyclerView observationRecyclerView;
    private ObservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observations);
        setTitle("Observations");

        Intent intent = getIntent();
        if (intent != null) {
            address = intent.getStringExtra("restaurant_address");
            name = intent.getStringExtra("restaurant_name");
            score = intent.getIntExtra("restaurant_score", -1);
            observations = intent.getStringArrayListExtra("restaurant_observation");
        }

        restaurantName = findViewById(R.id.tv_observationsActivity_restName);
        restaurantName.setText(name);
        restaurantScore = findViewById(R.id.tv_observationsActivity_restScore);
        restaurantScore.setText(score + "");
        restaurantAddress = findViewById(R.id.tv_observationsActivity_restAddress);
        restaurantAddress.setText(address);

/*
        String observation = "This is a test string to show that the ObservationAdapter actually works alongside" +
                "the recyclerview";

        for(int i = 0; i < 10; i++) {
            observations.add(observation);
        }*/

        observationRecyclerView = findViewById(R.id.rv_observationsActivity);
        observationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        observationRecyclerView.setHasFixedSize(true);
        adapter = new ObservationAdapter(this);
        observationRecyclerView.setAdapter(adapter);

        adapter.setObservations(observations);


    }
}