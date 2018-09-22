package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {
    private Button btnchange,btncancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        final EditText usernameET = (EditText) findViewById(R.id.change_et_username);
        final EditText oldpasspET = (EditText) findViewById(R.id.change_et_oldpassword);
        final EditText newpasspET = (EditText) findViewById(R.id.change_et_newpassword);
        final EditText confirmpasspET = (EditText) findViewById(R.id.change_et_confirmpass);


        btnchange=(Button)findViewById(R.id.changepasswordbtn_change);
        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamestr = usernameET.getText().toString();
                String oldpassstr = oldpasspET.getText().toString();
                String newpassstr = newpasspET.getText().toString();
                String confirmpassstr = confirmpasspET.getText().toString();

                if (usernamestr.isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "Please enter Username", Toast.LENGTH_SHORT).show();
                } else if (oldpassstr.isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "Please enter Old password", Toast.LENGTH_SHORT).show();
                }else if (newpassstr.isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "Please enter New password", Toast.LENGTH_SHORT).show();
                }else if (!confirmpassstr.equals(newpassstr)) {
                    Toast.makeText(ChangePasswordActivity.this, "Password dont match", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent=new Intent(ChangePasswordActivity.this,HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(ChangePasswordActivity.this, "Change Successfully", Toast.LENGTH_SHORT).show();
                }

                }
        });
        btncancle=(Button)findViewById(R.id.cangepassword_btn_cancel);
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(ChangePasswordActivity.this,ManageAccountActivity.class);
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
