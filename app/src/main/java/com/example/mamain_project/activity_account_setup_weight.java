package com.example.mamain_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

public class activity_account_setup_weight extends AppCompatActivity {

    Button Continue_button;
    Button Back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup_weight);

        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        if(numberPicker != null)
        {
            numberPicker.setMaxValue(150);
            numberPicker.setMinValue(30);
            numberPicker.setWrapSelectorWheel(true);
            numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
                String text = "Changed from " + oldVal + " to " + newVal;
                Toast.makeText(activity_account_setup_weight.this,text, Toast.LENGTH_SHORT).show();
            });

        }

        Continue_button = findViewById(R.id.Continue_button);
        Back_button = findViewById(R.id.Back_button);

        Continue_button.setOnClickListener(v -> {
            Intent intent = new Intent(activity_account_setup_weight.this,activity_account_setup_height.class);
            startActivity(intent);
        });

        Back_button.setOnClickListener(v -> {
            Intent intent = new Intent(activity_account_setup_weight.this,activity_account_setup_age.class);
            startActivity(intent);
        });

    }
}