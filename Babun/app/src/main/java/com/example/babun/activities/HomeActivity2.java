package com.example.babun.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.babun.MainActivity;
import com.example.babun.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity2 extends AppCompatActivity {
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        auth=FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(HomeActivity2.this, MainActivity.class));
            finish();
        }

    }

    public void login(View view) {
        startActivity(new Intent(HomeActivity2.this, LoginActivity2.class));
    }

    public void registration(View view) {

        startActivity(new Intent(HomeActivity2.this, RegistrationActivity2.class));
    }
}