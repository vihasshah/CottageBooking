package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.khushi.win10.cottagebooking.Helpers.Utils;
import com.khushi.win10.cottagebooking.Model.LoginModel;
import com.khushi.win10.cottagebooking.Model.SimpleResModel;
import com.khushi.win10.cottagebooking.Webservice.APICall;
import com.khushi.win10.cottagebooking.Webservice.Callback;

import org.json.JSONObject;

public class ManageAccountActivity extends AppCompatActivity {
    private TextInputLayout firstnameTl,lastnameTl,contactTl,emailTl,oldPassTl,newPassTl,newCofPassTl;
    private TextInputEditText firstnameEt,lastnameEt,contactEt,emailEt,oldPassEt,newPassEt,newCofPassEt;
    private Button btnUpdateAcc,btnChangepass;
    private AsyncTask<Void, Void, Void> apiCall = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initRef();
        final String userId = loadUserInfo();


        btnUpdateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserInfo(userId);
            }
        });

        btnChangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCredential(userId);
            }
        });
    }

    private void updateCredential(String userId) {
        String oldPassword = oldPassEt.getText().toString().trim();
        String password = newPassEt.getText().toString().trim();
        String confPassword = newCofPassEt.getText().toString().trim();
        String loginPref = getSharedPreferences(Utils.SHARED_PREF_NAME,MODE_PRIVATE).getString(Utils.LOGIN_PREF,null);
        final LoginModel model = new Gson().fromJson(loginPref,LoginModel.class);
        String email = model.getData().getEmail();

        if(isValidCredential(oldPassword,password,confPassword)){
            JSONObject object = new JSONObject();
            try {
                object.put("user_id",userId);
                object.put("email",email);
                object.put("old_password",oldPassword);
                object.put("password",password);
            }catch (Exception e){
                e.printStackTrace();
            }
            apiCall = new APICall(this, Utils.RESET_PASS_URL, object.toString(), null, new Callback() {
                @Override
                public void onCallback(final String response) {
                    if(response != null){
                        ManageAccountActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SimpleResModel simpleResModel = new Gson().fromJson(response,SimpleResModel.class);
                                Toast.makeText(ManageAccountActivity.this, simpleResModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).execute();
        }
    }

    private boolean isValidCredential(String oldPassword, String password, String confPassword) {
        if(oldPassword.length() <= 0){
            Toast.makeText(this, "old password field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(password.length() <= 0){
            Toast.makeText(this, "password field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(confPassword.length() <= 0){
            Toast.makeText(this, "confirm password field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!password.equals(confPassword)){
            Toast.makeText(this, "passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }

    }

    private void updateUserInfo(String userId) {
        String firstname = firstnameEt.getText().toString().trim();
        String lastname = lastnameEt.getText().toString().trim();
        String contact = contactEt.getText().toString().trim();
        String email = emailEt.getText().toString().trim();

        if(isValidInfo()){
            JSONObject object = new JSONObject();
            try{
                object.put("user_id",userId);
                object.put("firstname",firstname);
                object.put("lastname",lastname);
                object.put("contact",contact);
                object.put("email",email);
            }catch (Exception e){
                e.printStackTrace();
            }
            apiCall = new APICall(ManageAccountActivity.this, Utils.UPDATE_INFO_URL, object.toString(), null, new Callback() {
                @Override
                public void onCallback(final String response) {
                    Utils.log((response));
                    ManageAccountActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(response != null){
                                LoginModel model = new Gson().fromJson(response,LoginModel.class);
                                Toast.makeText(ManageAccountActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                                if(model.getSuccess() == 1){
                                    // storing new data
                                    getSharedPreferences(Utils.SHARED_PREF_NAME,MODE_PRIVATE).edit().putString(Utils.LOGIN_PREF,response).apply();
                                }
                            }
                        }
                    });
                }
            }).execute();
        }
    }

    private String loadUserInfo() {
        String loginPrefStr = getSharedPreferences(Utils.SHARED_PREF_NAME,MODE_PRIVATE).getString(Utils.LOGIN_PREF,null);
        if(loginPrefStr != null){
            LoginModel model = new Gson().fromJson(loginPrefStr,LoginModel.class);
            if(model.getData() != null){
                LoginModel.DataBean userInfo = model.getData();
                firstnameEt.setText(userInfo.getFirstname());
                lastnameEt.setText(userInfo.getLastname());
                contactEt.setText(userInfo.getContact());
                emailEt.setText(userInfo.getEmail());
                return userInfo.getId();
            }
        }
        return "0";
    }

    private void initRef() {
        //textInputLayut
        firstnameTl = findViewById(R.id.account_tl_firstname);
        lastnameTl = findViewById(R.id.account_tl_lastname);
        contactTl = findViewById(R.id.account_tl_contact);
        emailTl = findViewById(R.id.account_tl_email);
        oldPassTl = findViewById(R.id.account_tl_old_password);
        newPassTl = findViewById(R.id.account_tl_new_password);
        newCofPassTl = findViewById(R.id.account_tl_new_cf_password);
        //textInputEdittext
        firstnameEt = findViewById(R.id.account_et_firstname);
        lastnameEt = findViewById(R.id.account_et_lastname);
        contactEt = findViewById(R.id.account_et_contact);
        emailEt = findViewById(R.id.account_et_email);
        oldPassEt = findViewById(R.id.account_et_old_password);
        newPassEt = findViewById(R.id.account_et_new_password);
        newCofPassEt = findViewById(R.id.account_et_new_cf_password);
        // button
        btnUpdateAcc=(Button)findViewById(R.id.account_info_save_btn);
        btnChangepass=(Button)findViewById(R.id.account_cred_save_btn);
    }

    private boolean isValidInfo(){
        if(firstnameEt.getText().toString().trim().length() <= 0){
            Toast.makeText(this, "Invalid firstname", Toast.LENGTH_SHORT).show();
            return false;
        }else if(lastnameEt.getText().toString().trim().length() <= 0){
            Toast.makeText(this, "Invalid lastname", Toast.LENGTH_SHORT).show();
            return false;
        }else if(contactEt.getText().toString().trim().length() <= 0 ){
            Toast.makeText(this, "Invalid contact", Toast.LENGTH_SHORT).show();
            return false;
        }else if(emailEt.getText().toString().trim().length() <= 0) {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(apiCall != null && apiCall.getStatus() == AsyncTask.Status.RUNNING){
            apiCall.cancel(true);
        }
    }
}
