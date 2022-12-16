package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class mainPageActivity extends AppCompatActivity {


    FloatingActionButton addNewItemBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage_act);
        addNewItemBtn=(FloatingActionButton) findViewById(R.id.addNewItemBtn);


        addNewItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNewItemIntent=new Intent(getApplicationContext(),AddNewTaskActivity.class);
                startActivity(addNewItemIntent);
            }
        });


    }
}