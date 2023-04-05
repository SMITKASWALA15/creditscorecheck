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


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;

@SuppressWarnings("all")
public class CompareLoanActivity extends AppCompatActivity {
    private ImageView compare_text_view;
    public String f167s1 = "0";
    public String f168s2 = "0";
    public String f169s3 = "0";
    private NumberFormat format;
    private TextView loanAmt1;
    private TextView loanAmt2;
    private TextView mEmi1;
    private TextView mEmi2;
    private EditText pAmt1;
    private EditText pAmt2;
    private EditText period1;
    private EditText period2;
    private EditText rate1;
    private EditText rate2;
    ImageView reset_text_view;
    public String st1 = "0";
    public String st2 = "0";
    public String st3 = "0";
    private TextView totInterest1;
    private TextView totInterest2;
    private TextView totPay1;
    private TextView totPay2;
    ImageView ic_back;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_compare_loan);


        this.pAmt1 = (EditText) findViewById(R.id.principle_amount_edit_text_one);
        this.pAmt2 = (EditText) findViewById(R.id.principle_amount_edit_text_two);
        EditText editText = this.pAmt1;
        editText.addTextChangedListener(new ontouch(editText));
        EditText editText2 = this.pAmt2;
        editText2.addTextChangedListener(new ontouch(editText2));
        this.rate1 = (EditText) findViewById(R.id.interest_rate_edit_text_one);
        this.rate2 = (EditText) findViewById(R.id.interest_rate_edit_text_two);
        this.period1 = (EditText) findViewById(R.id.months_edit_text_one);
        this.period2 = (EditText) findViewById(R.id.months_edit_text_two);
        this.loanAmt1 = (TextView) findViewById(R.id.loanAmt1);
        this.loanAmt2 = (TextView) findViewById(R.id.loanAmt2);
        this.totInterest1 = (TextView) findViewById(R.id.interest_result_one);
        this.totInterest2 = (TextView) findViewById(R.id.interest_result_two);
        this.mEmi1 = (TextView) findViewById(R.id.emi_result_one);
        this.mEmi2 = (TextView) findViewById(R.id.emi_result_two);
        this.totPay1 = (TextView) findViewById(R.id.total_result_one);
        this.totPay2 = (TextView) findViewById(R.id.total_result_twq);
        this.format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        reset_text_view = findViewById(R.id.reset_text_view);
        reset_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        compare_text_view = findViewById(R.id.calculate_text_view);
        compare_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compareEmi();
            }
        });

        ic_back = findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CompareLoanActivity.this, CalculatorActivity.class));
            }
        });
    }


    public void compareEmi() {
        this.st1 = this.pAmt1.getText().toString();
        this.st2 = this.rate1.getText().toString();
        this.st3 = this.period1.getText().toString();
        this.f167s1 = this.pAmt2.getText().toString();
        this.f168s2 = this.rate2.getText().toString();
        this.f169s3 = this.period2.getText().toString();
        if (this.st1.isEmpty() || this.st2.isEmpty() || this.st3.isEmpty() || this.f167s1.isEmpty() || this.f168s2.isEmpty() || this.f169s3.isEmpty()) {
            Toast.makeText(this, "Please enter details first", 0).show();
            return;
        }
        B(this.st1, this.st2, this.st3, this.mEmi1, this.totInterest1, this.totPay1, this.loanAmt1);
        B(this.f167s1, this.f168s2, this.f169s3, this.mEmi2, this.totInterest2, this.totPay2, this.loanAmt2);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        View currentFocus = getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(this);
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public final void B(String str, String str2, String str3, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        float parseFloat = Float.parseFloat(str.replace(",", ""));
        float parseFloat2 = Float.parseFloat(str2.replace(",", ""));
        float parseFloat3 = Float.parseFloat(str3.replace(",", ""));
        float f = (parseFloat2 / 12.0f) / 100.0f;
        float pow = (float) Math.pow((double) (f + 1.0f), (double) parseFloat3);
        float floatValue = ((f * parseFloat) * pow) / Float.valueOf(pow - 1.0f).floatValue();
        float floatValue2 = (Float.valueOf(parseFloat3).floatValue() * floatValue) - parseFloat;
        textView.setText(getString(R.string.Rs) + String.valueOf(B(floatValue, 2)));
        textView2.setText(getString(R.string.Rs) + String.valueOf(B(floatValue2, 2)));
        textView3.setText(getString(R.string.Rs) + String.valueOf(B(floatValue2 + parseFloat, 2)));
        textView4.setText(getString(R.string.Rs) + String.valueOf(B(parseFloat, 2)));
    }

    public static float B(float f, int i) {
        try {
            return new BigDecimal(Float.toString(f)).setScale(i, 4).floatValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public void reset() {
        this.pAmt1.setText("");
        this.pAmt2.setText("");
        this.rate1.setText("");
        this.rate2.setText("");
        this.period1.setText("");
        this.period2.setText("");
        this.loanAmt1.setText(this.format.format(0.0d));
        this.loanAmt2.setText(this.format.format(0.0d));
        this.mEmi1.setText(this.format.format(0.0d));
        this.mEmi2.setText(this.format.format(0.0d));
        this.totInterest1.setText(this.format.format(0.0d));
        this.totInterest2.setText(this.format.format(0.0d));
        this.totPay1.setText(this.format.format(0.0d));
        this.totPay2.setText(this.format.format(0.0d));
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(CompareLoanActivity.this, CalculatorActivity.class));
    }

    private class ontouch implements TextWatcher {
        public EditText f2182a;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public ontouch(EditText editText) {
            this.f2182a = editText;
        }

        public String a(String str) {
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

        public void afterTextChanged(Editable editable) {
            try {
                this.f2182a.removeTextChangedListener(this);
                String obj = this.f2182a.getText().toString();
                if (obj != null && !obj.equals("")) {
                    if (obj.startsWith(".")) {
                        this.f2182a.setText("0.");
                    }
                    if (obj.startsWith("0") && !obj.startsWith("0.")) {
                        this.f2182a.setText("");
                    }
                    String replaceAll = this.f2182a.getText().toString().replaceAll(",", "");
                    if (!obj.equals("")) {
                        this.f2182a.setText(a(replaceAll));
                    }
                    EditText editText = this.f2182a;
                    editText.setSelection(editText.getText().toString().length());
                }
                this.f2182a.addTextChangedListener(this);
            } catch (Exception e) {
                e.printStackTrace();
                this.f2182a.addTextChangedListener(this);
            }
        }
    }

}
