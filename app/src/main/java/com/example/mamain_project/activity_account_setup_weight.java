package com.example.mamain_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

public class activity_account_setup_weight extends AppCompatActivity {

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
            numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    String text = "Changed from " + oldVal + " to " + newVal;
                    Toast.makeText(activity_account_setup_weight.this,text, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}