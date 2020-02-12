package com.example.tipcalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity implements TextWatcher {
    EditText editTextBillAmount;
    TextView textViewBillAmount;
    TextView textViewTipAmount;
    TextView textViewTotalAmount;
    TextView textViewTextTip;
    TextView textViewTextTotal;
    TextView textSeekBarPercent;
    SeekBar seekBarPercentage;

    private double billAmount = 0.0;
    private double seekBarPercent = 0.15;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBillAmount = (EditText) findViewById(R.id.editText_BillAmount);
        textViewBillAmount = (TextView) findViewById(R.id.text_View_BillAmount);
        textViewTipAmount = (TextView) findViewById(R.id.show_textView_tip);
        textViewTotalAmount = (TextView) findViewById(R.id.show_textView_total);
        textViewTextTip = (TextView) findViewById(R.id.show_text_tip);
        textViewTextTotal = (TextView) findViewById(R.id.show_text_total);
        textSeekBarPercent = (TextView) findViewById(R.id.show_seekBar_percent);
        seekBarPercentage = (SeekBar) findViewById(R.id.show_seekBar);

        editTextBillAmount.addTextChangedListener((TextWatcher) this);
        seekBarPercentage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSeekBarPercent.setText("" + progress + "%");
                seekBarPercent = ((double) progress) /100;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d("MainActivity", "Inside onTextChange method: charSequence = " + charSequence);

        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);

        try {
            billAmount = Double.parseDouble(charSequence.toString()) / 100;
        }
        catch (Exception e){
            toast.show();
        }
        textViewBillAmount.setText(currencyFormat.format(billAmount));
        calculate();

        Log.d("MainActivity", "Bill Amount = " + billAmount);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void calculate(){

        Log.d("MainActivity", "Inside calculate method");

        textViewTipAmount.setText(percentFormat.format(seekBarPercent));

        double tip = billAmount * seekBarPercent;
        double total = billAmount + tip;

        textViewTipAmount.setText(currencyFormat.format(tip));
        textViewTotalAmount.setText(currencyFormat.format(total));
    }
}
