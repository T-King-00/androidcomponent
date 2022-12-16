package com.example.project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddNewTaskActivity extends AppCompatActivity {


    Button btn;
    EditText TaskTitleTxt;
    EditText NoteTxt;
//firebase stuff
    FirebaseDatabase mFirebaseDb;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        btn=(Button) findViewById(R.id.addItemBtnToDb);
        TaskTitleTxt=(EditText)findViewById(R.id.TaskNameTxt);
        NoteTxt=(EditText)findViewById(R.id.NoteTxt);

        mAuth=FirebaseAuth.getInstance();
        mFirebaseDb=FirebaseDatabase.getInstance();
        myRef=mFirebaseDb.getReference("note");


        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    Log.d(TAG, "onAuthStateChanged: signed in "+user.getUid());
                    Toast.makeText(getApplicationContext(), "Signed in successfully "+ user.getEmail().toString(), Toast.LENGTH_LONG).show();
                    Intent newIntent=new Intent(getApplicationContext(),mainPageActivity.class);
                    startActivity(newIntent);


                }
                else {
                    Log.d(TAG, "onAuthStateChanged: signed out");
                    Toast.makeText(getApplicationContext(), "Signed out successfully", Toast.LENGTH_SHORT).show();
                }
            }
        };
// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Attemting to add to db");
                String title=TaskTitleTxt.getText().toString();
                String note=NoteTxt.getText().toString();
                if (!note.equals(""))
                {
                    FirebaseUser user=mAuth.getCurrentUser();
                    user.getUid();
                    myRef.child(user.getUid()).push().setValue(note);
                    Toast.makeText(AddNewTaskActivity.this, "add new item db", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
}