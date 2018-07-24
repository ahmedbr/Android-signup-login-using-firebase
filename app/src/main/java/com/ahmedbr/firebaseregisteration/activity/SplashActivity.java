package com.ahmedbr.firebaseregisteration.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahmedbr.firebaseregisteration.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(this, AuthActivity.class);
        navigateDelayed(intent);
    }

    private void navigateDelayed(final Intent intent) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
