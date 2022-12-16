package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Debug;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private EditText emailTxt;
    private EditText passTxt;
    private Button signInBtn;
    private Button signUpBtn;
    private Button forgetPassBtn;








    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen_act);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        /*StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*/
         emailTxt = (EditText) findViewById(R.id.EmailAddressTxt);
         passTxt = (EditText) findViewById(R.id.passwordTxt);
         signInBtn=(Button) findViewById(R.id.btnSignin);
         signUpBtn=(Button) findViewById(R.id.btnSignUp);


        mAuth=FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    Log.d(TAG, "onAuthStateChanged: signed in "+user.getUid());
                    Toast.makeText(MainActivity.this, "Signed in successfully "+ user.getEmail().toString(), Toast.LENGTH_LONG).show();
                    Intent newIntent=new Intent(getApplicationContext(),mainPageActivity.class);
                    startActivity(newIntent);


                }
                else {
                    Log.d(TAG, "onAuthStateChanged: signed out");
                    Toast.makeText(MainActivity.this, "Signed out successfully", Toast.LENGTH_SHORT).show();
                }
            }
        };

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailTxt.getText().toString();
                String pass=passTxt.getText().toString();
                //values not empty
                if(!email.equals("")&&!pass.equals(""))
                {
                    mAuth.signInWithEmailAndPassword(email,pass);
                    //Toast.makeText(MainActivity.this, "Connecting", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(MainActivity.this, "signing outt", Toast.LENGTH_SHORT).show();
            }
        });









    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);


    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);

    }

}