package com.example.adoptapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
//import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button signupButton;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        signupButton = (Button) findViewById(R.id.registerButton);
        signupButton.setOnClickListener(v -> openActivitySignup());
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> openActivityLogin());
    }
    public void openActivitySignup()
    {
        Intent signupIntent = new Intent(this, RegisterActivity.class);
        startActivity(signupIntent);
    }

    public void openActivityLogin()
    {
        Intent loginIntent = new Intent(this,LoginActivity.class);
        startActivity(loginIntent);
    }

}