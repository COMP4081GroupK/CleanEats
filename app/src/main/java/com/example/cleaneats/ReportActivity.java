package com.example.cleaneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.cleaneats.database.Account;
import com.example.cleaneats.database.AccountRepository;
import com.example.cleaneats.database.Report;
import com.google.android.gms.common.internal.AccountType;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private Button submit;
    private TextView restaurantNameTextView;
    private TextView restaurantAddressTextView;
    private EditText repBox;

    private String keyword;
    private String address;
    private String name;
    private int inspectionScore;
    private List<String> observations;
    private String username = "";

    String report = "";


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
            username = intent.getStringExtra("username");
        }

        AccountRepository repository = new AccountRepository(getApplication());

        repBox = findViewById(R.id.et_reportActivity_report);
        submit = findViewById(R.id.bt_reportActivity_submitButton);
        restaurantAddressTextView = findViewById(R.id.tv_reportActivity_address);
        restaurantNameTextView = findViewById(R.id.tv_reportActivity_name);

        restaurantNameTextView.setText(name);
        restaurantAddressTextView.setText(address);

        submit.setOnClickListener(new View.OnClickListener() {  //doesn't actually submit report, but looks like it!
            @Override
            public void onClick(View view) {
                report = repBox.getText().toString();
                repository.getSpecificAccount(username).observe(ReportActivity.this, new Observer<Account>() {
                    @Override
                    public void onChanged(Account account) {
                        int accountId = account.getId();

                        Report newReport = new Report(accountId, name, address, report);
                        repository.insert(newReport);

                        Toast.makeText(ReportActivity.this, "Report Submitted", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ReportActivity.this, ObservationsActivity.class);
                        intent.putExtra("restaurant_name", name);
                        intent.putExtra("restaurant_address", address);
                        intent.putExtra("restaurant_score", inspectionScore);
                        intent.putExtra("keyword", keyword);
                        intent.putExtra("username", username);
                        intent.putStringArrayListExtra("restaurant_observation", (ArrayList)observations);
                        startActivity(intent);
                    }
                });
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
                intent.putExtra("username", username);
                intent.putStringArrayListExtra("restaurant_observation", (ArrayList)observations);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
