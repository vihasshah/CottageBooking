package com.khushi.win10.cottagebooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextView tvsignup;
    private Button btnlogin;
    private TextView tvforgotpassword;
    private EditText etusername,etpass;
    View dialogView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etusername=(EditText)findViewById(R.id.main_et_username);
        etpass=(EditText)findViewById(R.id.main_et_password);
        btnlogin=(Button)findViewById(R.id.main_btn_login);
        tvforgotpassword=(TextView)findViewById(R.id.main_tv_forgotpassword);
        tvsignup = (TextView) findViewById(R.id.main_tv_signup);
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);

                startActivity(intent);


            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strusername = etusername.getText().toString();
                String strpass = etpass.getText().toString();
                if (strusername.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                }else if (strpass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }else {
                    Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(i);
                    Toast.makeText(LoginActivity.this, "Login Successfully..", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForgetPwdDialog();
            }
        });
    }

    private void showForgetPwdDialog() {

        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_forget_pwd, null);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Your email")
                .setView(dialogView)
                .setPositiveButton("Verify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        handleTextDialog();
                        Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .create();
        dialog.show();
        final EditText emailET = (EditText) findViewById(R.id.main_et_username);
        String emailstr = emailET.getText().toString();
    }

    private void handleTextDialog() {

        if (dialogView != null) {
            EditText editText = (EditText) dialogView.findViewById(R.id.forget_et_email);
            String str = editText.getText().toString();

        }
    }

}
