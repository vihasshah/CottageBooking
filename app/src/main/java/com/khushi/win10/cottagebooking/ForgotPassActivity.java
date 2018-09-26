package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassActivity extends AppCompatActivity {

    private Button btnreset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        final EditText passET = (EditText) findViewById(R.id.forgot_et_password);
        final EditText confirmpasspET = (EditText) findViewById(R.id.forgot_et_confirmpassword);
        btnreset=(Button)findViewById(R.id.btn_reset);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passstr = passET.getText().toString();
                String confirmpassstr = confirmpasspET.getText().toString();

                if (passstr.isEmpty()) {
                    Toast.makeText(ForgotPassActivity.this, "Please enter new password", Toast.LENGTH_SHORT).show();
                } else if (confirmpassstr.isEmpty()) {
                    Toast.makeText(ForgotPassActivity.this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
                } else if (!confirmpassstr.equals(passstr)) {
                    Toast.makeText(ForgotPassActivity.this, "Password dont match", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgotPassActivity.this, "Successfull!!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ForgotPassActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
