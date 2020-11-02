package com.example.cleaneats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.view.*;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ObservationsActivity extends AppCompatActivity/* implements OnMapReadyCallback*/{

    String address = "";
    String name = "";
    int score = 0;
    List<String> observations = new ArrayList<>();

    private TextView restaurantName;
    private TextView restaurantScore;
    private TextView restaurantAddress;

    private RecyclerView observationRecyclerView;
    private ObservationAdapter adapter;

    private MapView mapView;    //misc mapView stuff if I have time to implement
    private GoogleMap map;
    private LatLng latLng;
    private LayoutInflater inflater;
    private ViewGroup container;

    private Geocoder geocoder;
    private double lat;
    private double lng;
    private List<Address> addresses;
    private TextView link;

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

            //getting location in lat/longitude
            geocoder = new Geocoder(this);
            try {
                addresses = geocoder.getFromLocationName(address, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            lat = addresses.get(0).getLatitude();
            lng = addresses.get(0).getLongitude();

            link =(TextView)findViewById(R.id.textView);
            link.setClickable(true);
            link.setMovementMethod(LinkMovementMethod.getInstance());
            String linkName = name.replace(' ', '+');
            //setting up the google maps link

            String text = "Directions: https://www.google.com/maps/dir//" + linkName + "/@" + lat + "," + lng;
            link.setText(Html.fromHtml(text));


        }

        restaurantName = findViewById(R.id.tv_observationsActivity_restName);
        restaurantName.setText(name);
        restaurantScore = findViewById(R.id.tv_observationsActivity_restScore);
        restaurantScore.setText(score + "");
        restaurantAddress = findViewById(R.id.tv_observationsActivity_restAddress);
        restaurantAddress.setText(address);

        observationRecyclerView = findViewById(R.id.rv_observationsActivity);
        observationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        observationRecyclerView.setHasFixedSize(true);
        adapter = new ObservationAdapter(this);
        observationRecyclerView.setAdapter(adapter);

        adapter.setObservations(observations);


        //and now the map
        //map.addMarker(new MarkerOptions().position(latLng).title(name));
        //onCreateView(inflater, container, savedInstanceState);
        //onMapReady(map);


        }
    /*public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_observations, container, false);

        mapView = (MapView) v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }*/



}