package com.example.cleaneats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    private Button searchButton;
    private EditText keywordEditText;
    private Button aboutUs;
    private Button signUp;
    private TextView userName;

    String keyword = "";
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setTitle("Home Page");

        userName = findViewById(R.id.tv_homePageActivity_username);

        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("username");
            userName.setText(username);
        }

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

        signUp = findViewById(R.id.bt_homePageActivity_signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}