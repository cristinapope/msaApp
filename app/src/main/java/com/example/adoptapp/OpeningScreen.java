package com.example.adoptapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

public class OpeningScreen extends AppCompatActivity {

    private Button adoptntButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_opening_screen);
        adoptntButton = (Button) findViewById(R.id.adoptntButtonMainScreen);
        adoptntButton.setOnClickListener(v -> openActivityAddPet());
    }

    private void openActivityAddPet() {
        Intent signupIntent = new Intent(this, AddPetActivity.class);
        startActivity(signupIntent);
    }
}