package com.example.babun.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babun.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity2 extends AppCompatActivity {
    TextView sign_up;
    Button signin_btn;
    EditText password_login,email_login;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        auth=FirebaseAuth.getInstance();
        sign_up=findViewById(R.id.sign_up);
        signin_btn=findViewById(R.id.signin_btn);
        email_login=findViewById(R.id.email_login);
        password_login=findViewById(R.id.password_login);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity2.this, RegistrationActivity2.class));

            }
        });
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });
    }

    private void loginUser() {

        String userEmail=email_login.getText().toString();
        String userPassword=password_login.getText().toString();


        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Email is Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Password is Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length()<6){
            Toast.makeText(this,"Password length must be greater than 6",Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity2.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity2.this, HomeActivity2.class));

                }
                else
                {
                    Toast.makeText(LoginActivity2.this,"Error"+task.getException(),Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}