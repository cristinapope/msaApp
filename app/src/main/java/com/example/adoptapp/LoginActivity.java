package com.example.adoptapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;

    private TextView loginButtonLoginScreen;
    private EditText textEmail, textPassword;

    private Button registerbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login6);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAuth= FirebaseAuth.getInstance();

        registerbutton = (Button) findViewById(R.id.registerButtonLoginScreen);
        registerbutton.setOnClickListener(v -> openActivitySignup());

        loginButtonLoginScreen= (Button) findViewById(R.id.loginButtonLoginScreen);
        loginButtonLoginScreen.setOnClickListener(view -> openActivityLogin());

        textEmail=(EditText) findViewById(R.id.textEmail);
        textPassword=(EditText) findViewById(R.id.textPassword);
    }

    private void openActivityLogin() {
        userLogin();
    }

    private void userLogin() {
        //Prepare text fields
        String email= textEmail.getText().toString().trim();
        String password= textPassword.getText().toString().trim();

        if(email.isEmpty()){
            textEmail.setError("Email needed to register");
            textEmail.requestFocus();
        }

        if( !Patterns.EMAIL_ADDRESS.matcher(email).matches() ){
            textEmail.setError("Email not valid format");
            textEmail.requestFocus();
        }

        if(password.isEmpty()){
            textPassword.setError("Password is required to to register");
            textPassword.requestFocus();
            return;
        }
        if(password.length()<8){
            textPassword.setError("Password must be more than 8 characters");
            textPassword.requestFocus();
            return;
        }

        //Actual Logging in with Firebase
        myAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //Login successful
                    startActivity(new Intent(LoginActivity.this, OpeningScreen.class));
                }else{
                    //Login failed
                    Toast.makeText(LoginActivity.this, "Log in failed. Check credentials.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openActivitySignup()
    {
        Intent signupIntent = new Intent(this, RegisterActivity.class);
        startActivity(signupIntent);
    }
}