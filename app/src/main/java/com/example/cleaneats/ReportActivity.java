package com.example.cleaneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {
    private Button submit;
    private TextView reportSubmitted;
    private EditText repBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setTitle("Report This Restaurant");

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
}
