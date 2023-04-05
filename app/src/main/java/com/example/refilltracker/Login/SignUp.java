package com.example.refilltracker.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.refilltracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private Button btn;
    private EditText fullname,email,password,cpassword,carname,mob;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullname = findViewById(R.id.etFirstName);
        email = findViewById(R.id.etLastName);
        password = findViewById(R.id.etEmail);
        cpassword = findViewById(R.id.etPassword);
        carname = findViewById(R.id.etConfirmPassword);
        mob = findViewById(R.id.etNumber);
        btn = findViewById(R.id.btnSignUp);

        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
    private void registerUser(){
        final String uName = fullname.getText().toString().trim();
        final String uEmail = email.getText().toString().trim();
        final String uPassword = password.getText().toString().trim();
        final String uCar = carname.getText().toString().trim();
        final String uMobile = mob.getText().toString().trim();

        if (uName.isEmpty()) {
            fullname.setError("Name cannot be empty");
        }
        if (uEmail.isEmpty()) {
            email.setError("Enter a valid email address");
        }
        if (TextUtils.isEmpty(uPassword)){
            password.setError("combination of alphabet and numbers");
        }

        if (TextUtils.isEmpty(uCar)){
            cpassword.setError("combination of alphabet and numbers");
        }

        if ((uMobile.length()<10) || (uMobile.length()>10)){
            mob.setError("enter a valid 10 digit number");
        }

        else {
            mAuth.createUserWithEmailAndPassword(uEmail,uPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignUp.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(SignUp.this, LoginActivity.class));
                    }
                    else {
                        Toast.makeText(SignUp.this,"User Authentication Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}