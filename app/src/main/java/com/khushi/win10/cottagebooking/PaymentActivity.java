package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.khushi.win10.cottagebooking.Helpers.Utils;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    private EditText cardNoEt, cvvNoEt;
    private TextView cardTypeTv;
    private Spinner expMonth, expYear;
    private Button bookBtn;
    private String visaCardRegex = "^4[0-9]{12}(?:[0-9]{3})?$";
    private String masterCardRegex = "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$";
    String finalCardNo = "";
    int counter = 0;
    String validCard = ""; // reset text if user add wrong number
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().setTitle("Payment Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cardNoEt = findViewById(R.id.payment_et_card_no);
        cardTypeTv = findViewById(R.id.payment_tv_card_type);
        cvvNoEt= findViewById(R.id.payment_et_cvv);
        expMonth = findViewById(R.id.payment_spinner_month);
        expYear = findViewById(R.id.payment_spinner_year);
        bookBtn = findViewById(R.id.payment_book_btn);

        int callFrom = getIntent().getIntExtra(Utils.INTENT_PAYMENT_BY,Utils.BY_CREDIT_CARD);

        if(callFrom == Utils.BY_DEBIT_CARD){
            cvvNoEt.setVisibility(View.GONE);
        }

        ArrayList<Integer> yearList = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            yearList.add(2018+i);
        }
        ArrayAdapter aa = new ArrayAdapter(PaymentActivity.this,android.R.layout.simple_spinner_item,yearList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expYear.setAdapter(aa);
        cardNoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // check for visa card or master card
                if(s.toString().matches(visaCardRegex)){
                    cardTypeTv.setText("Visa");
                    cardTypeTv.setTextColor(Color.GREEN);
                }
                else if(s.toString().matches(masterCardRegex)){
                    cardTypeTv.setText("MasterCard");
                    cardTypeTv.setTextColor(Color.GREEN);
                }else {
                    cardTypeTv.setText("Not Supported Card");
                    cardTypeTv.setTextColor(Color.RED);
                }
                cardNoEt.removeTextChangedListener(this);
                if(s.toString().length() < 19) {
                    if (counter == 3) {
                        cardNoEt.setText(cardNoEt.getText().toString() + "-");
                        if(cardNoEt.getText().toString().length() > 0) {
                            cardNoEt.setSelection(cardNoEt.getText().length());
                        }else{
                            cardNoEt.setSelection(0);
                            counter = 0;
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }
                }
                cardNoEt.addTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
