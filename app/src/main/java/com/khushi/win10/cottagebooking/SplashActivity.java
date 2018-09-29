package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.khushi.win10.cottagebooking.Helpers.Utils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String loginCred = SplashActivity.this.getSharedPreferences(Utils.SHARED_PREF_NAME,MODE_PRIVATE).getString(Utils.LOGIN_PREF,null);
                if(loginCred == null) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);

                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);

                    startActivity(intent);
                }
            }
        },1500);

    }
}
