package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.R;

public class ChaoActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_DURATION = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chao_activity);
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ChaoActivity.this, LoginActivity.class);
                ChaoActivity.this.startActivity(mainIntent);
                ChaoActivity.this.finish();
            }
        },SPLASH_DISPLAY_DURATION );
    }
}