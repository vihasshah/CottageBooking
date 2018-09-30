package com.khushi.win10.cottagebooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.khushi.win10.cottagebooking.Helpers.Utils;
import com.khushi.win10.cottagebooking.Model.LoginModel;
import com.khushi.win10.cottagebooking.Webservice.APICall;
import com.khushi.win10.cottagebooking.Webservice.Callback;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private TextView tvsignup;
    private Button btnlogin;
    private TextView tvforgotpassword;
    private EditText etusername,etpass;
    private View dialogView;
    private AsyncTask<Void, Void, Void> apiCall = null;

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
                    Toast.makeText(LoginActivity.this, "email cannot be empty", Toast.LENGTH_SHORT).show();
                }else if (strpass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }else {
                    authenticatingBtnUI();
                    loginAPICall(strusername,strpass);
                    // temp
//                    Intent i=new Intent(LoginActivity.this,HomeActivity.class);
//                    startActivity(i);
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

    private void authenticatingBtnUI() {
        btnlogin.setText("Authenticating");
        btnlogin.setBackgroundResource(android.R.color.darker_gray);
        btnlogin.setTextColor(Color.parseColor("#ffffff"));
    }

    private void defaultBtnUI() {
        btnlogin.setText("Login");
        btnlogin.setBackgroundResource(R.color.colorPrimaryDark);
        btnlogin.setTextColor(Color.parseColor("#ffffff"));
    }
    private void handleTextDialog() {
        if (dialogView != null) {
            EditText editText = (EditText) dialogView.findViewById(R.id.forget_et_email);
            String str = editText.getText().toString();
        }
    }


    private void loginAPICall(String email,String password){
        //creating request
        JSONObject object = new JSONObject();
        try {
            object.put("email",email);
            object.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String loginJsonStr = object.toString();
        Utils.log(loginJsonStr);
        apiCall = new APICall(LoginActivity.this, Utils.LOGIN_URL, loginJsonStr, "Authenticating", new Callback() {
            @Override
            public void onCallback(final String response) {

                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        defaultBtnUI();
                        if(response != null) {

                            LoginModel model = new Gson().fromJson(response,LoginModel.class);
                            // storing local data
                            if(model.getSuccess() == 1) {
                                SharedPreferences.Editor editor = getSharedPreferences(Utils.SHARED_PREF_NAME, MODE_PRIVATE).edit();
                                editor.putString(Utils.LOGIN_PREF, response);
                                if (model.getData() != null && model.getData().getId() != null) {
                                    editor.putString(Utils.USER_ID_PREF, model.getData().getId());
                                }
                                editor.apply();

                                // navigate to home
                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(i);
                                Toast.makeText(LoginActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LoginActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).execute();

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
