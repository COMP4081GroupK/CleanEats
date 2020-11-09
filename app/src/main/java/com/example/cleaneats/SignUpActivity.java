package com.example.cleaneats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cleaneats.database.Account;
import com.example.cleaneats.database.AccountRepository;

public class SignUpActivity extends AppCompatActivity {

    private Button signUpButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;

    private AccountRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        repository = new AccountRepository(getApplication());

        usernameEditText = findViewById(R.id.et_signUpActivity_username);
        passwordEditText = findViewById(R.id.et_signUpActivity_password);
        firstNameEditText = findViewById(R.id.et_signUpActivity_firstName);
        lastNameEditText = findViewById(R.id.et_signUpActivity_lastName);

        signUpButton = findViewById(R.id.bt_signUpActivity_signUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();

                Account newAccount = new Account(username, password, firstName, lastName);
                repository.insert(newAccount);

                finish();
            }
        });
    }
}