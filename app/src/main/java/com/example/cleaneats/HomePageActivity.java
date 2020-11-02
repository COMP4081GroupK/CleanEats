package com.example.cleaneats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomePageActivity extends AppCompatActivity {

    private Button searchButton;
    private EditText keywordEditText;
    private Button aboutUs;

    String keyword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setTitle("Home Page");

        keywordEditText = findViewById(R.id.et_homePageActivity_keyword);

        searchButton = findViewById(R.id.bt_homePageActivity_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyword = keywordEditText.getText().toString();

                Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
                intent.putExtra("keyword", keyword);
                startActivity(intent);
            }
        });

        aboutUs = findViewById(R.id.aboutUsButton);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomePageActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }
}