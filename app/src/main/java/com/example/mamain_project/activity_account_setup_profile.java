package com.example.mamain_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

public class activity_account_setup_profile extends AppCompatActivity {

    Button Continue_button = findViewById(R.id.Continue_button);
    Button Skip_button = findViewById(R.id.Skip_button);
    ImageButton read;
    ImageView profile_dp;
    private Button mAddToDatabase;
    private EditText FullName;
    private EditText NickName;
    private EditText Email;
    private EditText PhoneNumber;
    private static final String TAG = "AddToDataBase";
    //FireBase DataBase Stuff
    private FirebaseDatabase mFireBaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_account_setup_profile);
        FullName = (EditText) findViewById(R.id.full_name_profile_setup);
        NickName = (EditText) findViewById(R.id.Nick_name_profile_setup);
        Email = (EditText) findViewById(R.id.Email_profile_setup);
        PhoneNumber = (EditText) findViewById(R.id.PhoneNumber_profile_setup);

        Continue_button.setOnClickListener(v -> {
            Intent intent = new Intent(activity_account_setup_profile.this,MainActivity.class);
            startActivity(intent);
        });

        Skip_button.setOnClickListener(v -> {
            Intent intent = new Intent(activity_account_setup_profile.this,MainActivity.class);
            startActivity(intent);
        });

        profile_dp = findViewById(R.id.Profile_image);
        read = findViewById(R.id.edit_profile_button);

//        read.setOnClickListener(v -> {
//            ContextWrapper cw = new ContextWrapper(getApplicationContext());
//            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
//            File file = new File(directory,"mario"+".png");
//            profile_dp.setImageDrawable(Drawable.createFromPath(file.toString()));
//        });

        mAddToDatabase = (Button) findViewById(R.id.Continue_button);
        mAuthListener = (FirebaseAuth.AuthStateListener) (FirebaseAuth)-> {
            FirebaseUser user = FirebaseAuth.getCurrentUser();
            if(user != null)
            {
                Log.d(TAG,"onAuthStateChanged:signed_in"+user.getUid());
                Toast.makeText(this, "Successfully signed in with "+user.getEmail(), Toast.LENGTH_SHORT).show();
            }
            else {
                Log.d(TAG,"onAuthStateChanged:signed_out");
                Toast.makeText(this, "Successfully signed out!", Toast.LENGTH_SHORT).show();
            }
        };
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//            String value = snapshot.getValue(String.class);
                Object value = snapshot.getValue();
                Log.d(TAG,"Value is:"+value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG,"Failed to read value"+error.toException());
            }
        });

//        Error Solving Query:-
//        Intent intent = new Intent(activity_account_setup_profile.this,MainActivity.class);
//        startActivity(intent);

        mAddToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FirstName = FullName.getText().toString();
                String NickName = FullName.getText().toString();
                String Email = FullName.getText().toString();
                String PhoneNumber = FullName.getText().toString();
                if (!FirstName.equals("") && !NickName.equals("") &&
                    !Email.equals("") && !PhoneNumber.equals("")) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userId = user.getUid();
                    myRef.child(userId).child("User").child("FullName").child(FirstName).setValue("true");
                    myRef.child(userId).child("User").child("Email").child(Email).setValue("true");
                    myRef.child(userId).child("User").child("NickName").child(NickName).setValue("true");
                    myRef.child(userId).child("User").child("PhoneNumber").child(PhoneNumber).setValue("true");
                }}
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
}