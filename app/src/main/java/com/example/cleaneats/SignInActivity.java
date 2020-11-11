package com.example.cleaneats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cleaneats.database.Account;
import com.example.cleaneats.database.AccountRepository;

import java.util.List;

public class SignInActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    private Button signInButton;

    private AccountRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setTitle("Sign In");

        repository = new AccountRepository(getApplication());

        usernameEditText = findViewById(R.id.et_signInActivity_username);
        passwordEditText = findViewById(R.id.et_signInActivity_password);

        signInButton = findViewById(R.id.bt_signInActivity_signIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                LiveData<List<Account>> allAccounts = repository.getAllAccounts();
                allAccounts.observe(SignInActivity.this, new Observer<List<Account>>() {
                    @Override
                    public void onChanged(List<Account> allAccounts) {
                        boolean foundAccount = false;

                        for(int i = 0; i < allAccounts.size(); i++) {

                            if(allAccounts.get(i).getUserName().equals(username) && allAccounts.get(i).getPassword().equals(password) ) {
                                foundAccount = true;
                                Intent intent = new Intent(SignInActivity.this, HomePageActivity.class);
                                intent.putExtra("username", username);
                                startActivity(intent);
                            }
                        }

                        if(!foundAccount) {
                            Toast.makeText(SignInActivity.this, "Incorrect username and/or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}