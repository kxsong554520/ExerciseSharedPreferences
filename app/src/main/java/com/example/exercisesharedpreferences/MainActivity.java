package com.example.exercisesharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String storedUsername = sharedPreferences.getString("username", "");
        String storedPassword = sharedPreferences.getString("password", "");

        if (storedUsername.isEmpty() || storedPassword.isEmpty()) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveCredentials();
                }
            });
        } else {
            openSuccessPage();
        }
    }

    private void saveCredentials() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();

        openSuccessPage();
    }

    private void openSuccessPage() {
        Intent intent = new Intent(this, SuccessActivity.class);
        startActivity(intent);
        finish();
    }
}
