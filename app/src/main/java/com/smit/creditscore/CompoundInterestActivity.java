package com.smit.creditscore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.skydoves.powerspinner.PowerSpinnerView;

import java.text.NumberFormat;
import java.util.Locale;

@SuppressWarnings("all")
public class CompoundInterestActivity extends AppCompatActivity {
     private ImageView calculateTextView;
    private EditText et_i_rate;
    private EditText et_m_dpsit;
    private EditText et_period;
    public String f73s;
    private NumberFormat format;
    private PowerSpinnerView freq_spin;
    private TextView maturityAmt;
    private TextView pAmt;
    ImageView resetTextView;
    private TextView totInterest;
    ImageView ic_back;
    

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_compound_interest);

        ic_back = findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CompoundInterestActivity.this, CalculatorActivity.class));
            }
        });

        resetTextView = findViewById(R.id.reset_text_view);
        resetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        calculateTextView = findViewById(R.id.calculate_text_view);
        calculateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calCulateComIntCal();
            }
        });
        this.et_m_dpsit = (EditText) findViewById(R.id.principle_amount_edit_text);
        this.et_i_rate = (EditText) findViewById(R.id.interest_rate_edit_text);
        this.et_period = (EditText) findViewById(R.id.months_edit_text);
        this.freq_spin = (PowerSpinnerView) findViewById(R.id.freq_spin);
        this.pAmt = (TextView) findViewById(R.id.principle_amount_result_text_view);
        this.totInterest = (TextView) findViewById(R.id.simple_interest_result_text_view);
        this.maturityAmt = (TextView) findViewById(R.id.total_amount_result_text_view);
        this.format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        this.freq_spin.setArrowAnimate(true);
        this.freq_spin.setLifecycleOwner(this);
        this.freq_spin.setSpinnerPopupElevation(10);
    }

    public void reset() {
        this.et_m_dpsit.getText().clear();
        this.et_i_rate.getText().clear();
        this.et_period.getText().clear();
        this.pAmt.setText(this.format.format(0.0d));
        this.totInterest.setText(this.format.format(0.0d));
        this.maturityAmt.setText(this.format.format(0.0d));
    }


    public void calCulateComIntCal() {
        String str;
        if (this.et_m_dpsit.getText().toString().isEmpty() || this.et_i_rate.getText().toString().isEmpty() || this.et_period.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter details first", 0).show();
            return;
        }
        int selectedIndex = this.freq_spin.getSelectedIndex();
        if (selectedIndex == 0) {
            str = "12";
        } else if (selectedIndex == 1) {
            str = "4";
        } else if (selectedIndex == 2) {
            str = "2";
        } else {
            str = selectedIndex == 3 ? "1" : "";
            double parseDouble = Double.parseDouble(this.et_m_dpsit.getText().toString().replace(",", ""));
            double parseDouble2 = Double.parseDouble(this.et_i_rate.getText().toString());
            double parseDouble3 = Double.parseDouble(this.et_period.getText().toString());
            double d = ((parseDouble2 / 100.0d) / parseDouble3) + 1.0d;
            double parseDouble4 = (Double.parseDouble(this.et_period.getText().toString()) / 12.0d) * parseDouble3;
            this.pAmt.setText(this.format.format(parseDouble));
            this.totInterest.setText(this.format.format(((Math.pow(d, parseDouble4) * parseDouble) + 0.0d) - parseDouble));
            this.maturityAmt.setText(this.format.format((Math.pow(d, parseDouble4) * parseDouble) + 0.0d));
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
            View currentFocus = getCurrentFocus();
            if (currentFocus == null) {
                currentFocus = new View(this);
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CompoundInterestActivity.this, CalculatorActivity.class));
    }

}
