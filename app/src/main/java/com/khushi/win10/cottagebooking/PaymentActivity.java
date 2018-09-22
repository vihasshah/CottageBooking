package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    Button okbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        okbtn=(Button)findViewById(R.id.btn_ok);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PaymentActivity.this,RentDetailActivity.class);
                startActivity(i);
                Toast.makeText(PaymentActivity.this, "Book Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
