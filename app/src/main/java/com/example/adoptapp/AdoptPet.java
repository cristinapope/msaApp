package com.example.adoptapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdoptPet extends AppCompatActivity {

    private Button adoptButton;
    private ImageView petPicture;
    private TextView petName;
    private TextView petType;
    private TextView petDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_pet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adoptButton = (Button) findViewById(R.id.adoptButton);

        //adoptButton.setOnClickListener(v -> openActivityAddPet());
    }
}
