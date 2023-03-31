package com.example.mamain_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

public class activity_account_setup_profile extends AppCompatActivity {

    Button Continue_button = findViewById(R.id.Continue_button);
    Button Skip_button = findViewById(R.id.Skip_button);

    ImageButton read;
    ImageView profile_dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup_profile);

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
        read.setOnClickListener(v -> {
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File file = new File(directory,"mario"+".png");
            profile_dp.setImageDrawable(Drawable.createFromPath(file.toString()));
        });

    }
}