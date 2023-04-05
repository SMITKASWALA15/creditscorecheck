package com.smit.creditscore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;


@SuppressWarnings("all")
@Keep
public class Splash_screen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Splash_screen.this, WelcomeActivity.class));
            }
        }, 3000);

    }


}
