package com.example.adoptapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;

    private EditText textEmail;
    private TextView buttonConfirmForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        myAuth= FirebaseAuth.getInstance();
        textEmail=(EditText) findViewById(R.id.textEmail);

        buttonConfirmForgotPassword= (Button)findViewById(R.id.buttonConfirmForgotPassword);
        buttonConfirmForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

    }

    private void resetPassword(){
        //Preparing the email text
        String email= textEmail.getText().toString().trim();

        if(email.isEmpty()){
            textEmail.setError("Email needed to register");
            textEmail.requestFocus();
        }

        if( !Patterns.EMAIL_ADDRESS.matcher(email).matches() ){
            textEmail.setError("Email not valid format");
            textEmail.requestFocus();
        }

        //Sending a password reset link to your email
        //The email address has to be an actual email address
        myAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    //Email with passwd reset link set
                    Toast.makeText(ForgotPasswordActivity.this, "Password Reset Email sent!", Toast.LENGTH_LONG).show();
                }
                else{
                    //Somethign went wrong
                    Toast.makeText(ForgotPasswordActivity.this, "Error occured. Try again.!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}