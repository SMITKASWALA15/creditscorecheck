package com.smit.creditscore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


@SuppressWarnings("all")
public class HomeActivity extends AppCompatActivity {
   ImageView banklist;
   ImageView calculator;
   ImageView checkofflion;
   ImageView checkonlion;
   ImageView maintips;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);

        checkofflion = findViewById(R.id.checkofflion);
        checkofflion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ImageView imageView4 = (ImageView) findViewById(R.id.checkonlion);
        this.checkonlion = imageView4;
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ImageView imageView5 = (ImageView) findViewById(R.id.maintips);
        this.maintips = imageView5;
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ImageView imageView6 = (ImageView) findViewById(R.id.calculator);
        this.calculator = imageView6;
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CalculatorActivity.class));
            }
        });
        ImageView imageView7 = (ImageView) findViewById(R.id.banklist);
        this.banklist = imageView7;
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
