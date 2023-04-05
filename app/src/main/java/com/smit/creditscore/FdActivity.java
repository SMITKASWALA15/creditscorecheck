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


import com.skydoves.powerspinner.PowerSpinnerView;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;

@SuppressWarnings("all")
public class FdActivity extends AppCompatActivity {
     private ImageView calculateTextView;
    private EditText et_i_rate;
    private EditText et_m_dpsit;
    private EditText et_period;
    public String f170s;
    private NumberFormat format;
    private PowerSpinnerView freq_spin;
    private TextView maturityAmt;
    private ImageView  resetTextView;
    private TextView totDeposit;
    private TextView totInterest;
    ImageView ic_back;
    

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fd);
        


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
                startActivity(new Intent(FdActivity.this, CalculatorActivity.class));
            }
        });
        calculateTextView = findViewById(R.id.calculate_text_view);
        calculateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateFD();
            }
        });
        EditText editText = (EditText) findViewById(R.id.yearly_investment_amount_edit_text);
        this.et_m_dpsit = editText;
        editText.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                try {
                    FdActivity.this.et_m_dpsit.removeTextChangedListener(this);
                    String obj = FdActivity.this.et_m_dpsit.getText().toString();
                    if (obj != null && !obj.equals("")) {
                        if (obj.startsWith(".")) {
                            FdActivity.this.et_m_dpsit.setText("0.");
                        }
                        if (obj.startsWith("0") && !obj.startsWith("0.")) {
                            FdActivity.this.et_m_dpsit.setText("");
                        }
                        String replaceAll = FdActivity.this.et_m_dpsit.getText().toString().replaceAll(",", "");
                        if (!obj.equals("")) {
                            FdActivity.this.et_m_dpsit.setText(FdActivity.a(replaceAll));
                        }
                        EditText editText = FdActivity.this.et_m_dpsit;
                        editText.setSelection(editText.getText().toString().length());
                    }
                    FdActivity.this.et_m_dpsit.addTextChangedListener(this);
                } catch (Exception e) {
                    e.printStackTrace();
                    FdActivity.this.et_m_dpsit.addTextChangedListener(this);
                }
            }
        });
        this.et_i_rate = (EditText) findViewById(R.id.interest_rate_edit_text);
        this.et_period = (EditText) findViewById(R.id.months_edit_text);
        this.freq_spin = (PowerSpinnerView) findViewById(R.id.freq_spin);
        this.totInterest = (TextView) findViewById(R.id.total_interest_amount_result_text_view);
        this.totDeposit = (TextView) findViewById(R.id.investment_amount_result_text_view);
        this.maturityAmt = (TextView) findViewById(R.id.maturity_amount_result_text_view);
        this.format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        this.freq_spin.setArrowAnimate(true);
    }

    public static String a(String str) {
        String str2;
        StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
        String str3 = "";
        if (stringTokenizer.countTokens() > 1) {
            str = stringTokenizer.nextToken();
            str2 = stringTokenizer.nextToken();
        } else {
            str2 = str3;
        }
        int length = str.length() - 1;
        if (str.charAt(str.length() - 1) == '.') {
            length--;
            str3 = ".";
        }
        int i = 0;
        while (length >= 0) {
            if (i == 3) {
                str3 = SumTotalb.a(",", str3);
                i = 0;
            }
            str3 = str.charAt(length) + str3;
            i++;
            length--;
        }
        return str2.length() <= 0 ? str3 : SumTotalb.a(str2, ".", str3);
    }

    public void reset() {
        this.et_m_dpsit.getText().clear();
        this.et_i_rate.getText().clear();
        this.et_period.getText().clear();
        this.totInterest.setText(this.format.format(0.0d));
        this.totDeposit.setText(this.format.format(0.0d));
        this.maturityAmt.setText(this.format.format(0.0d));
    }


    public void calculateFD() {
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
            str = selectedIndex == 3 ? "1" : null;
            double parseDouble = Double.parseDouble(this.et_m_dpsit.getText().toString().replace(",", ""));
            double parseDouble2 = Double.parseDouble(this.et_i_rate.getText().toString());
            double parseDouble3 = Double.parseDouble(this.f170s);
            double d = ((parseDouble2 / 100.0d) / parseDouble3) + 1.0d;
            double parseDouble4 = (Double.parseDouble(this.et_period.getText().toString()) / 12.0d) * parseDouble3;
            this.totInterest.setText(this.format.format(((Math.pow(d, parseDouble4) * parseDouble) + 0.0d) - parseDouble));
            this.totDeposit.setText(this.format.format(parseDouble));
            this.maturityAmt.setText(this.format.format((Math.pow(d, parseDouble4) * parseDouble) + 0.0d));
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
            View currentFocus = getCurrentFocus();
            if (currentFocus == null) {
                currentFocus = new View(this);
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        this.f170s = str;
        double parseDouble5 = Double.parseDouble(this.et_m_dpsit.getText().toString().replace(",", ""));
        double parseDouble6 = Double.parseDouble(this.et_i_rate.getText().toString());
        double parseDouble7 = Double.parseDouble(this.f170s);
        double d2 = ((parseDouble6 / 100.0d) / parseDouble7) + 1.0d;
        double parseDouble8 = (Double.parseDouble(this.et_period.getText().toString()) / 12.0d) * parseDouble7;
        this.totInterest.setText(this.format.format(((Math.pow(d2, parseDouble8) * parseDouble5) + 0.0d) - parseDouble5));
        this.totDeposit.setText(this.format.format(parseDouble5));
        this.maturityAmt.setText(this.format.format((Math.pow(d2, parseDouble8) * parseDouble5) + 0.0d));
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FdActivity.this, CalculatorActivity.class));
    }

}
