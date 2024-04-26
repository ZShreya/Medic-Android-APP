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
import com.example.babun.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity2 extends AppCompatActivity {
    TextView sign_in;
    EditText name,email_reg,password_reg;
    Button signup_btn;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        name=findViewById(R.id.name);
        email_reg=findViewById(R.id.email_reg);
        password_reg=findViewById(R.id.password_reg);
        signup_btn=findViewById(R.id.signup_btn);

        sign_in=findViewById(R.id.sign_in);


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity2.this, LoginActivity2.class));
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();

            }
        });


    }

    private void createUser() {
        String userName=name.getText().toString();
        String userEmail=email_reg.getText().toString();
        String userPassword=password_reg.getText().toString();

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this,"Name is Empty",Toast.LENGTH_SHORT).show();
            return;
        }
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

        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    UserModel userModel=new UserModel(userName,userEmail,userPassword);
                    String id =task.getResult().getUser().getUid();
                    database.getReference().child("Users").child(id).setValue(userModel);
                Toast.makeText(RegistrationActivity2.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity2.this, HomeActivity2.class));
                finish();
                }
                else
                {
                    Toast.makeText(RegistrationActivity2.this,"Error"+task.getException(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}