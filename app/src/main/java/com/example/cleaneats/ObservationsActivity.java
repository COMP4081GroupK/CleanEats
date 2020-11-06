package com.example.cleaneats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri.*;

public class ObservationsActivity extends AppCompatActivity{

    String keyword = "";

    String address = "";
    String name = "";
    int inspectionScore = 0;
    List<String> observations = new ArrayList<>();

    private TextView restaurantName;
    private TextView restaurantScore;
    private TextView restaurantAddress;

    private RecyclerView observationRecyclerView;
    private ObservationAdapter adapter;

    private Geocoder geocoder;
    private double lat;
    private double lng;
    private List<Address> addresses;
    private Button link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observations);
        setTitle("Observations");

        Intent intent = getIntent();
        if (intent != null) {
            keyword = intent.getStringExtra("keyword");
            address = intent.getStringExtra("restaurant_address");
            name = intent.getStringExtra("restaurant_name");
            inspectionScore = intent.getIntExtra("restaurant_score", -1);
            observations = intent.getStringArrayListExtra("restaurant_observation");

            //getting location in lat/longitude
            geocoder = new Geocoder(this);
            try {
                addresses = geocoder.getFromLocationName(address, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            lat = addresses.get(0).getLatitude();
            lng = addresses.get(0).getLongitude();

            link =(Button) findViewById(R.id.textView);
            link.setClickable(true);
            link.setMovementMethod(LinkMovementMethod.getInstance());


        }

        restaurantName = findViewById(R.id.tv_observationsActivity_restName);
        restaurantName.setText(name);
        restaurantScore = findViewById(R.id.tv_observationsActivity_restScore);
        restaurantScore.setText(inspectionScore + "");
        restaurantAddress = findViewById(R.id.tv_observationsActivity_restAddress);
        restaurantAddress.setText(address);

        if (inspectionScore < 60) {
            restaurantScore.setBackground(getDrawable(R.drawable.bad_score_circle));
        } else if (inspectionScore < 80) {
            restaurantScore.setBackground(getDrawable(R.drawable.medium_score_circle));
        } else if (inspectionScore <= 100) {
            restaurantScore.setBackground(getDrawable(R.drawable.good_score_circle));
        }

        observationRecyclerView = findViewById(R.id.rv_observationsActivity);
        observationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        observationRecyclerView.setHasFixedSize(true);
        adapter = new ObservationAdapter(this);
        observationRecyclerView.setAdapter(adapter);

        adapter.setObservations(observations);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkName = name.replace(' ', '+');
                String text = "https://www.google.com/maps/dir//" + linkName + "/@" + lat + "," + lng;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(text));
                startActivity(intent);
            }
        });

        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ObservationsActivity.this, MainActivity.class);
                intent.putExtra("keyword", keyword);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }





}