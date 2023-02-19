package com.example.mamain_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
{

//    TextView userName;
    Button logout;
    GoogleSignInClient gClient;
    GoogleSignInOptions gOptions;

    //Java Code for logout of a user direct to login activity
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = findViewById(R.id.logout);
//        userName = findViewById(R.id.userName);
        gOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gClient = GoogleSignIn.getClient(this, gOptions);
        GoogleSignInAccount gAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (gAccount != null) {
            String gName = gAccount.getDisplayName();
//            userName.setText(gName);
        }
        logout.setOnClickListener(view -> gClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        }));

        //Bottom Navigation View:-
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.home_navBar,homeFragment);
        navView.getBackground().setAlpha(122);

        navView.setOnItemSelectedListener(item -> {
                switch (item.getItemId())
                {
                    case R.id.home_navBar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_navBar,homeFragment).commit();

                    case R.id.goal_navBar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.goal_navBar,goalFragment).commit();

                    case R.id.insight_navBar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.insight_navBar,insightFragment).commit();

                    case R.id.user_navBar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.user_navBar,profileFragment).commit();
                }
            return true;
        });
    }

    HomeFragment homeFragment = new HomeFragment();
    GoalFragment goalFragment = new GoalFragment();
    InsightFragment insightFragment = new InsightFragment();
    ProfileFragment profileFragment = new ProfileFragment();

}