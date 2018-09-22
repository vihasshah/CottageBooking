package com.khushi.win10.cottagebooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainRentListActivity extends AppCompatActivity {

    ListView listView;
    int[] imageView=new int[]{R.drawable.mybg1};
    String name[];
    String location[];
    String rating[];
    String rank[];
    String price[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rent_list);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        name=getResources().getStringArray(R.array.name);
        location=getResources().getStringArray(R.array.location);
        rating=getResources().getStringArray(R.array.rating);
        rank=getResources().getStringArray(R.array.rank);
        price=getResources().getStringArray(R.array.price);

        listView=(ListView)findViewById(R.id.list_one);

    }
}
