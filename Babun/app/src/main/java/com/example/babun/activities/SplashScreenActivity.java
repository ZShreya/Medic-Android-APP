package com.example.babun.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.babun.R;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH = 3000;
    Animation animation;
    private ImageView imageView;
    private TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        animation = AnimationUtils.loadAnimation(this,R.anim.animation);

        imageView = findViewById(R.id.mediclogo);
        textview = findViewById(R.id.appname);
        imageView.setAnimation(animation);
        textview.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, RegistrationActivity2.class);
                startActivity(intent);
                finish();
            }
        },SPLASH);
    }
}