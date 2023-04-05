package com.smit.creditscore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;




import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

@SuppressWarnings("all")
public class GstActivity extends AppCompatActivity {
    public float amount;
    public EditText amt;
    public TextView amtTv;
    private RelativeLayout calculateTextView;
    public TextView cgstTv;
    public NumberFormat format;
    public TextView gstAmtTv;
    public float rate;
    ImageView reset_text_view;
    public TextView sgstTv;
    public Spinner spRate;
    public TextView totalTv;
    ImageView calculate_text_view;
    ImageView ic_back;
    

    public final float A(float f, float f2) {
        return (float) ((int) ((f2 / 100.0f) * f));
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_gst);
        


        this.amt = (EditText) findViewById(R.id.amount_edit_text);
        this.spRate = (Spinner) findViewById(R.id.spRate);
        this.amtTv = (TextView) findViewById(R.id.net_price_amount);
        this.gstAmtTv = (TextView) findViewById(R.id.gst_price_result_text_view);
        this.cgstTv = (TextView) findViewById(R.id.cgst);
        this.sgstTv = (TextView) findViewById(R.id.sgst);
        this.totalTv = (TextView) findViewById(R.id.net_price_result_text_view);
        this.format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        ArrayList arrayList = new ArrayList();
        arrayList.add("5%");
        arrayList.add("12%");
        arrayList.add("18%");
        arrayList.add("28%");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367048, arrayList);
        arrayAdapter.setDropDownViewResource(17367049);
        this.spRate.setAdapter(arrayAdapter);
        reset_text_view = findViewById(R.id.reset_text_view);
        reset_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });


        ic_back = findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GstActivity.this, CalculatorActivity.class));
            }
        });
        calculate_text_view = findViewById(R.id.calculate_text_view);
        calculate_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateGST();
            }
        });
    }


    public void reset() {
        this.amt.getText().clear();
        this.amtTv.setText(this.format.format(0.0d));
        this.gstAmtTv.setText(this.format.format(0.0d));
        this.cgstTv.setText(this.format.format(0.0d));
        this.sgstTv.setText(this.format.format(0.0d));
        this.totalTv.setText(this.format.format(0.0d));
    }

    public void calculateGST() {
        try {
            this.amount = Float.valueOf(this.amt.getText().toString()).floatValue();
            this.rate = Float.valueOf(this.spRate.getSelectedItem().toString().replace("%", "")).floatValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        float f = this.amount;
        if (f == 0.0f || this.rate == 0.0f) {
            Toast.makeText(this, "Please enter details first", 0).show();
            return;
        }
        this.amtTv.setText(this.format.format((double) f));
        this.gstAmtTv.setText(this.format.format((double) A(this.amount, this.rate)));
        this.cgstTv.setText(this.format.format((double) (A(this.amount, this.rate) / 2.0f)));
        this.sgstTv.setText(this.format.format((double) (A(this.amount, this.rate) / 2.0f)));
        TextView textView = this.totalTv;
        NumberFormat numberFormat = this.format;
        float f2 = this.amount;
        textView.setText(numberFormat.format((double) (A(f2, this.rate) + f2)));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GstActivity.this, CalculatorActivity.class));
    }

}
