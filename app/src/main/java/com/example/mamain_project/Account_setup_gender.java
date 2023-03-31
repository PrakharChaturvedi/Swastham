package com.example.mamain_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Account_setup_gender extends AppCompatActivity {


    Button continueButton;
    ImageButton male_Button;
    ImageButton female_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup_gender);

        continueButton = findViewById(R.id.continue_button);
        male_Button = findViewById(R.id.male_button);
        female_Button = findViewById(R.id.female_button);

        String gender = "gender";
        continueButton.setOnClickListener(v -> {
            Intent intent = new Intent(Account_setup_gender.this,activity_account_setup_age.class);
            startActivity(intent);
        });

        male_Button.setOnClickListener(v -> gender.replace("gender","male"));

        female_Button.setOnClickListener(v -> gender.replace("gender","female"));
    }
}