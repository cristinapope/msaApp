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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;

    private TextView loginButtonLoginScreen, textForgotPassword;
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

        textForgotPassword= (TextView) findViewById(R.id.textForgotPassword);
        textForgotPassword.setOnClickListener(view -> openActivityForgotPassword());
    }

    private void openActivityForgotPassword() {
        Intent forgotPasswordIntent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(forgotPasswordIntent);
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

                    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                    //Check if user has their email verified.
                    if(user.isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this, OpeningScreen.class));
                    }
                    else{
                        //If not yet verified, send another verification email
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Please verify your email!",
                                Toast.LENGTH_LONG).show();
                    }

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