package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
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

import com.google.gson.Gson;
import com.khushi.win10.cottagebooking.Helpers.Utils;
import com.khushi.win10.cottagebooking.Model.LoginModel;
import com.khushi.win10.cottagebooking.Model.SimpleResModel;
import com.khushi.win10.cottagebooking.Webservice.APICall;
import com.khushi.win10.cottagebooking.Webservice.Callback;

import org.json.JSONException;
import org.json.JSONObject;

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

    int cottageId = 0;
    String startDate = null;
    String endDate = null;
    private AsyncTask<Void, Void, Void> apiCall = null;

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
        cottageId = Integer.valueOf(getIntent().getStringExtra(Utils.INTENT_COTTAGE_ID));
        startDate = getIntent().getStringExtra(Utils.INTENT_START_DATE);
        endDate = getIntent().getStringExtra(Utils.INTENT_END_DATE);

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

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startDate == null || endDate == null) {
                    Toast.makeText(PaymentActivity.this,"Please select dates",Toast.LENGTH_SHORT).show();
                }else{
                    bookingBtnUI();
                    bookApiCall();
                }
            }
        });
    }

    private void bookApiCall() {
        //creating request
        JSONObject object = new JSONObject();
        try {
            object.put("cottage_id",cottageId);
            object.put("start_date",startDate);
            object.put("end_date",startDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String bookJsonStr = object.toString();
        Utils.log(bookJsonStr);
        apiCall = new APICall(PaymentActivity.this, Utils.BOOK_URL, bookJsonStr, null, new Callback() {
            @Override
            public void onCallback(final String response) {

                PaymentActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        defaultBtnUI();
                        if(response != null) {

                            SimpleResModel model = new Gson().fromJson(response,SimpleResModel.class);
                            // storing local data
                            if(model.getSuccess() == 1){
                                    // navigate to home
                                    Intent i = new Intent(PaymentActivity.this, HomeActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                    Toast.makeText(PaymentActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(PaymentActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(PaymentActivity.this, "Booking Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).execute();

    }

    private void bookingBtnUI() {
        bookBtn.setText("Booking...");
        bookBtn.setBackgroundResource(android.R.color.darker_gray);
        bookBtn.setTextColor(Color.parseColor("#ffffff"));
    }

    private void defaultBtnUI() {
        bookBtn.setText("Book");
        bookBtn.setBackgroundResource(R.color.colorPrimaryDark);
        bookBtn.setTextColor(Color.parseColor("#ffffff"));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPause() {
        super.onPause();
        defaultBtnUI();
        if(apiCall != null && apiCall.getStatus() == AsyncTask.Status.RUNNING){
            apiCall.cancel(true);
        }
    }
}
