package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.bson.Document;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class MainActivity extends AppCompatActivity {

    String appid="application-0-iiowf";

    private static final String TAG = "MainActivity";
    private EditText emailTxt;
    private EditText passTxt;
    private Button signInBtn;
    private Button signUpBtn;
    private Button forgetPassBtn;
    Connection connect=null;

    User  mUser;

    static io.realm.mongodb.User monUser ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen_act);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

         emailTxt = (EditText) findViewById(R.id.EmailAddressTxt);
         passTxt = (EditText) findViewById(R.id.passwordTxt);
         signInBtn=(Button) findViewById(R.id.btnSignin);
         signUpBtn=(Button) findViewById(R.id.btnSignUp);

        Realm.init(this);//initialize realm
        App mApp=new App(new AppConfiguration.Builder(appid).build());

         mUser=new User();




        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser.setEmail(emailTxt.getText().toString());
                mUser.setPassword(passTxt.getText().toString());

                //values not empty
                if(!mUser.getEmail().equals("")&&!mUser.getPassword().equals(""))
                {
                    try {
                        AuthenticateUser(mApp,getApplicationContext());


                    } catch (SQLException e) {
                        Log.d(TAG, "onClick: "+e.getMessage());
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  mAuth.signOut();
                Toast.makeText(MainActivity.this, "signing outt", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void AuthenticateUser(App mApp, Context x) throws SQLException {
        Credentials CRED = Credentials.emailPassword(mUser.getEmail(), mUser.getPassword());

        mApp.loginAsync(CRED, new App.Callback<io.realm.mongodb.User>() {
            @Override
            public void onResult(App.Result<io.realm.mongodb.User> result) {
                if (result.isSuccess()) {
                    Log.d(TAG, "onResult: logged in");
                    Toast.makeText(x, "logged in ", Toast.LENGTH_SHORT).show();


                    monUser=mApp.currentUser();

                    Log.d(TAG, "onResult: ");

                    AddNewTaskActivity.mUser=monUser;

                    Intent intent = new Intent(x, mainPageActivity.class);
                    startActivity(intent);



                } else {
                    Log.d(TAG, "onResult: failed to logged in ann");

                }

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
      //  mAuth.addAuthStateListener(mAuthListener);


    }

    @Override
    public void onStop() {
        super.onStop();
      //  mAuth.removeAuthStateListener(mAuthListener);

    }



    public void signUp(App mApp, Context x) {
        /* mApp.getEmailPassword().registerUser(email,password, it->{
         if(it.isSuccess()){
          Toast.makeText(x, "registered successfully", Toast.LENGTH_SHORT).show();

         }
         else{
          Toast.makeText(x, "cant register ", Toast.LENGTH_SHORT).show();
        }


         });
            */

           }




}


    /*StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*/
