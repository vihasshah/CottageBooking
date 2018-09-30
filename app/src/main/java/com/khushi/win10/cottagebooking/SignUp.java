package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.khushi.win10.cottagebooking.Helpers.Utils;
import com.khushi.win10.cottagebooking.Model.LoginModel;
import com.khushi.win10.cottagebooking.Webservice.APICall;
import com.khushi.win10.cottagebooking.Webservice.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    private EditText et_fn,et_ln,et_add,et_un,et_email,et_pass,et_cn,et_ans;
    private Spinner sp_type,sp_que;
    private Button btnsignup,btnreset;
    private AsyncTask<Void, Void, Void> apiCall = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_fn=(EditText)findViewById(R.id.ET_fn_ET);
        et_ln=(EditText)findViewById(R.id.ET_ln_ET);
//        et_add=(EditText)findViewById(R.id.ET_address_ET);
//        et_un=(EditText)findViewById(R.id.ET_un_ET);
        et_email=(EditText)findViewById(R.id.ET_email_ET);
        et_pass=(EditText)findViewById(R.id.ET_password_ET);
        et_cn=(EditText)findViewById(R.id.ET_contactno_ET);
//        et_ans=(EditText)findViewById(R.id.ET_answer_ET);
//        sp_type=(Spinner)findViewById(R.id.spinner_type_spinner);
//        sp_que=(Spinner)findViewById(R.id.spinner_securityquestion_spinner);
//        btnreset=(Button)findViewById(R.id.btn_reset);
        btnsignup=(Button)findViewById(R.id.btn_signup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fnstr=et_fn.getText().toString();
                String lnstr=et_ln.getText().toString();
//                String addstr=et_add.getText().toString();
//                String unstr=et_un.getText().toString();
                String emailstr=et_email.getText().toString();
                String passstr=et_pass.getText().toString();
//                String cnstr=et_cn.getText().toString();
//                String ansstr=et_ans.getText().toString();
//                String typestr=String.valueOf(sp_type.getSelectedItem());
//                Log.d("myapp","selected type Spinner: "+typestr);
//                String questr=String.valueOf(sp_que.getSelectedItem());
//                Log.d("myapp","selected Question Spinner: "+questr);

                if (fnstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Firstname cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (lnstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Lastname cannot be empty", Toast.LENGTH_SHORT).show();
//                } else if (addstr.isEmpty()) {
//                    Toast.makeText(SignUp.this, "address cannot be empty", Toast.LENGTH_SHORT).show();
//                } else if (unstr.isEmpty()) {
//                    Toast.makeText(SignUp.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (emailstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(emailstr)) {
                    Toast.makeText(SignUp.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                } else if (passstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please enter password", Toast.LENGTH_SHORT).show();
//                }else if (cnstr.isEmpty()) {
//                    Toast.makeText(SignUp.this, "Please enter contact no", Toast.LENGTH_SHORT).show();
//                } else if (ansstr.isEmpty()) {
//                    Toast.makeText(SignUp.this, "Answer cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    authenticatingBtnUI();
                    signUpApiCall(fnstr,lnstr,emailstr,et_cn.getText().toString(),passstr);
//                    Intent i=new Intent(SignUp.this,HomeActivity.class);
//                    startActivity(i);
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

    private void authenticatingBtnUI() {
        btnsignup.setText("Creating...");
        btnsignup.setBackgroundResource(android.R.color.darker_gray);
        btnsignup.setTextColor(Color.parseColor("#ffffff"));
    }

    private void defaultBtnUI() {
        btnsignup.setText("Signup");
        btnsignup.setBackgroundResource(R.color.colorPrimaryDark);
        btnsignup.setTextColor(Color.parseColor("#ffffff"));
    }

    private void signUpApiCall(String firstname, String lastname, String email, String contact, String password){
        //creating request
        JSONObject object = new JSONObject();
        try {
            object.put("firstname",firstname);
            object.put("lastname",lastname);
            object.put("contact",contact);
            object.put("email",email);
            object.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String signupJsonStr = object.toString();
        Utils.log(signupJsonStr);
        apiCall = new APICall(SignUp.this, Utils.SIGNUP_URL, signupJsonStr, "Creating", new Callback() {
            @Override
            public void onCallback(final String response) {
                SignUp.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        defaultBtnUI();
                        if(response != null) {

                            LoginModel model = new Gson().fromJson(response,LoginModel.class);
                            if(model.getSuccess() == 1) {
                                // storing local data
                                SharedPreferences.Editor editor = getSharedPreferences(Utils.SHARED_PREF_NAME, MODE_PRIVATE).edit();
                                editor.putString(Utils.LOGIN_PREF, response);
                                if (model.getData() != null && model.getData().getId() != null) {
                                    editor.putString(Utils.USER_ID_PREF, model.getData().getId());
                                }
                                editor.apply();

                                // navigate to home
                                Intent i = new Intent(SignUp.this, HomeActivity.class);
                                startActivity(i);
                                Toast.makeText(SignUp.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignUp.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignUp.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }).execute();
    }
}
