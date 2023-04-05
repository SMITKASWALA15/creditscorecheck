package com.smit.creditscore;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import java.text.NumberFormat;
import java.util.Locale;

@SuppressWarnings("all")
public class PPFActivity extends AppCompatActivity {
     private ImageView calculateTextView;
    private EditText et_i_rate;
    private EditText et_m_dpsit;
    private EditText et_period;
    private NumberFormat format;
    private TextView maturityAmt;
    private ImageView  resetTextView;
    private TextView totDeposite;
    private TextView totInterest;
    ImageView ic_back;
    


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ppfactivity);

        

        EditText editText = (EditText) findViewById(R.id.yearly_investment_amount_edit_text);
        this.et_m_dpsit = editText;
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.et_i_rate = (EditText) findViewById(R.id.interest_rate_edit_text);
        this.et_period = (EditText) findViewById(R.id.Investment_Period);
        this.totInterest = (TextView) findViewById(R.id.total_interest_result_text_view);
        this.totDeposite = (TextView) findViewById(R.id.total_investment_amount_result_text_view);
        this.maturityAmt = (TextView) findViewById(R.id.maturity_amount_result_text_view);
        this.format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        resetTextView = findViewById(R.id.reset_text_view);
        resetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        ic_back = findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PPFActivity.this, CalculatorActivity.class));
            }
        });
        calculateTextView = findViewById(R.id.calculate_text_view);
        calculateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatePPF();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PPFActivity.this, CalculatorActivity.class));
    }

    public void reset() {
        this.et_m_dpsit.getText().clear();
        this.et_i_rate.getText().clear();
        this.et_period.getText().clear();
        this.totInterest.setText("");
        this.totDeposite.setText("");
        this.maturityAmt.setText("");
    }


    public void calculatePPF() {
        if (this.et_m_dpsit.getText().toString().isEmpty() || this.et_i_rate.getText().toString().isEmpty() || this.et_period.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter details first", 0).show();
            return;
        }
        double parseDouble = Double.parseDouble(this.et_m_dpsit.getText().toString().replace(",", ""));
        double parseDouble2 = Double.parseDouble(this.et_i_rate.getText().toString());
        int parseInt = Integer.parseInt(this.et_period.getText().toString());
        double d = parseDouble2 / 100.0d;
        double d2 = 0.0d;
        double d3 = parseDouble;
        for (int i = 0; i < parseInt; i++) {
            double d4 = d3 * d;
            d2 += d4;
            d3 += d4;
            if (i < parseInt - 1) {
                d3 += parseDouble;
            }
        }
        this.totInterest.setText(this.format.format(d2));
        TextView textView = this.totDeposite;
        NumberFormat numberFormat = this.format;
        double d5 = (double) parseInt;
        Double.isNaN(d5);
        Double.isNaN(d5);
        Double.isNaN(d5);
        Double.isNaN(d5);
        textView.setText(numberFormat.format(parseDouble * d5));
        this.maturityAmt.setText(this.format.format(d3));
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        View currentFocus = getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(this);
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

}
