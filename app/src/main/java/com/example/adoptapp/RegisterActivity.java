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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth myAuth;

    private TextView buttonConfirmRegister;
    private EditText textEmail, textUsername, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAuth= FirebaseAuth.getInstance();

        //Button to confirm the sign up process init
        buttonConfirmRegister= (Button) findViewById(R.id.buttonConfirmRegister);
        buttonConfirmRegister.setOnClickListener(this);

        //Textboxes init
        textEmail=(EditText) findViewById(R.id.textEmail);
        textUsername=(EditText) findViewById(R.id.textUsername);
        textPassword=(EditText) findViewById(R.id.textPassword);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.buttonConfirmRegister)
            registerUser();
    }

    private void registerUser() {
        //Prepare text fields
        String email= textEmail.getText().toString().trim();
        String username= textUsername.getText().toString().trim();
        String password= textPassword.getText().toString().trim();

        //Warnings & errors
        if(email.isEmpty()){
            textEmail.setError("Email needed to register");
            textEmail.requestFocus();
        }

        if( !Patterns.EMAIL_ADDRESS.matcher(email).matches() ){
            textEmail.setError("Email not valid format");
            textEmail.requestFocus();
        }

        if(username.isEmpty()){
            textUsername.setError("Username is required to register");
            textUsername.requestFocus();
        }

        if(password.isEmpty()){
            textPassword.setError("Password is required to to register");
            textPassword.requestFocus();
        }
        if(password.length()<8){
            textPassword.setError("Password must be more than 8 characters");
            textPassword.requestFocus();
        }

        //Account creation in Firebase
        myAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user= new User(username, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        //register Success
                                        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                                        Toast.makeText(RegisterActivity.this, "Successful Registration! check email for verification!",
                                                Toast.LENGTH_LONG).show();
                                    } else{
                                        //register Fail
                                        Toast.makeText(RegisterActivity.this, "Register failed!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });
    }
}