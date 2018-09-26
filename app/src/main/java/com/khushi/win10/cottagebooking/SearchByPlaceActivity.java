package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class SearchByPlaceActivity extends AppCompatActivity {

    Spinner sp_place;
    Button searchbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_place);

        sp_place=(Spinner)findViewById(R.id.spinner_search_place);
        searchbtn=(Button)findViewById(R.id.btn_place);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SearchByPlaceActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });
    }
}
