package com.example.cleaneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    private Button submit;
    private TextView reportSubmitted;
    private EditText repBox;

    private String keyword;
    private String address;
    private String name;
    private int inspectionScore;
    private List<String> observations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setTitle("Report This Restaurant");

        Intent intent = getIntent();
        if (intent != null) {
            keyword = intent.getStringExtra("keyword");
            address = intent.getStringExtra("restaurant_address");
            name = intent.getStringExtra("restaurant_name");
            inspectionScore = intent.getIntExtra("restaurant_score", -1);
            observations = intent.getStringArrayListExtra("restaurant_observation");
        }

        repBox = findViewById(R.id.reportBox);

        reportSubmitted = findViewById(R.id.reportSubmitted);
        submit = findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener() {  //doesn't actually submit report, but looks like it!
            @Override
            public void onClick(View view) {
                repBox.setText("");
                reportSubmitted.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ReportActivity.this, ObservationsActivity.class);
                intent.putExtra("restaurant_name", name);
                intent.putExtra("restaurant_address", address);
                intent.putExtra("restaurant_score", inspectionScore);
                intent.putExtra("keyword", keyword);
                intent.putStringArrayListExtra("restaurant_observation", (ArrayList)observations);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
