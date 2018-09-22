package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.khushi.win10.cottagebooking.SignUp.isValidEmail;

public class UpdateAccountActivity extends AppCompatActivity {
    private Button btnupdate, btncancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        final EditText ETfirstname = (EditText) findViewById(R.id.update_et_firstname);
        final EditText ETlastname = (EditText) findViewById(R.id.update_et_lastname);
        final EditText ETaddress = (EditText) findViewById(R.id.update_et_address);
        final EditText ETemail = (EditText) findViewById(R.id.update_et_email);
        final EditText ETcontactnumber = (EditText) findViewById(R.id.update_et_contactnumber);

        btnupdate = (Button) findViewById(R.id.btn_update);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strfn = ETfirstname.getText().toString();
                String strln = ETlastname.getText().toString();
                String straddress = ETaddress.getText().toString();
                String stremail = ETemail.getText().toString();
                String strcontactno = ETcontactnumber.getText().toString();

                if (strfn.isEmpty()) {
                    Toast.makeText(UpdateAccountActivity.this, "Firstname cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (strln.isEmpty()) {
                    Toast.makeText(UpdateAccountActivity.this, "Lastname cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (straddress.isEmpty()) {
                    Toast.makeText(UpdateAccountActivity.this, "address cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (stremail.isEmpty()) {
                    Toast.makeText(UpdateAccountActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(stremail)) {
                    Toast.makeText(UpdateAccountActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                } else if (strcontactno.isEmpty()) {
                    Toast.makeText(UpdateAccountActivity.this, "Please enter pass", Toast.LENGTH_SHORT).show();
                }else {
                    Intent i=new Intent(UpdateAccountActivity.this,HomeActivity.class);
                    startActivity(i);
                    Toast.makeText(UpdateAccountActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btncancle=(Button)findViewById(R.id.btn_cancel);
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(UpdateAccountActivity.this,ManageAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

