package com.example.adoptapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button registerbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login6);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        registerbutton = (Button) findViewById(R.id.registerButtonLoginScreen);
        registerbutton.setOnClickListener(v -> openActivitySignup());
    }
    public void openActivitySignup()
    {
        Intent signupIntent = new Intent(this, RegisterActivity.class);
        startActivity(signupIntent);
    }
}