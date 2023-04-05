package com.smit.creditscore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_start;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_welcome);
        ImageView imageView = (ImageView) findViewById(R.id.iv_start);
        this.iv_start = imageView;
        imageView.setOnClickListener(this);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isButtonPressed = prefs.getBoolean("button_1_pressed", false);
        if (isButtonPressed) {
            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
        }

    }

    public void onClick(View view) {
        if (view.getId() == R.id.iv_start) {
            final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            prefs.edit().putBoolean("button_1_pressed", true).apply();
            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
        }
    }

}
