package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    private EditText et_fn,et_ln,et_add,et_un,et_email,et_pass,et_cn,et_ans;
    private Spinner sp_type,sp_que;
    private Button btnsignup,btnreset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_fn=(EditText)findViewById(R.id.ET_fn_ET);
        et_ln=(EditText)findViewById(R.id.ET_ln_ET);
        et_add=(EditText)findViewById(R.id.ET_address_ET);
        et_un=(EditText)findViewById(R.id.ET_un_ET);
        et_email=(EditText)findViewById(R.id.ET_email_ET);
        et_pass=(EditText)findViewById(R.id.ET_password_ET);
        et_cn=(EditText)findViewById(R.id.ET_contactno_ET);
        et_ans=(EditText)findViewById(R.id.ET_answer_ET);
        sp_type=(Spinner)findViewById(R.id.spinner_type_spinner);
        sp_que=(Spinner)findViewById(R.id.spinner_securityquestion_spinner);
        btnreset=(Button)findViewById(R.id.btn_reset);
        btnsignup=(Button)findViewById(R.id.btn_signup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fnstr=et_fn.getText().toString();
                String lnstr=et_ln.getText().toString();
                String addstr=et_add.getText().toString();
                String unstr=et_un.getText().toString();
                String emailstr=et_email.getText().toString();
                String passstr=et_pass.getText().toString();
                String cnstr=et_cn.getText().toString();
                String ansstr=et_ans.getText().toString();
                String typestr=String.valueOf(sp_type.getSelectedItem());
                Log.d("myapp","selected type Spinner: "+typestr);
                String questr=String.valueOf(sp_que.getSelectedItem());
                Log.d("myapp","selected Question Spinner: "+questr);

                if (fnstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Firstname cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (lnstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Lastname cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (addstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "address cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (unstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (emailstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(emailstr)) {
                    Toast.makeText(SignUp.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                } else if (passstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }else if (cnstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please enter contact no", Toast.LENGTH_SHORT).show();
                } else if (ansstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Answer cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    Intent i=new Intent(SignUp.this,LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(SignUp.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public static boolean isValidEmail(CharSequence target) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(target);
        return matcher.matches();
    }
}
