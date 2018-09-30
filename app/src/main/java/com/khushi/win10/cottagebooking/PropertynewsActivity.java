package com.khushi.win10.cottagebooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class PropertynewsActivity extends AppCompatActivity {

    ListView listView;
    int[] imageView=new int[]{R.drawable.news1};
    String title[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertynews);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

//        ObjectHolder.newsModel=new ArrayList<>();
        NewsModel model1=new NewsModel();
        model1.setImagepropertynews(R.drawable.news1);
        model1.setTitle("Ahemdabad is Up-to-date on Infrastructure.");

        NewsModel model2=new NewsModel();
        model2.setImagepropertynews(R.drawable.news3);
        model2.setTitle("The Real Healthy Homes.");

        NewsModel model3=new NewsModel();
        model3.setImagepropertynews(R.drawable.myimg);
        model3.setTitle("Asbestos Cottage tramp worth every effort");

        NewsModel model4=new NewsModel();
        model4.setImagepropertynews(R.drawable.news2);
        model4.setTitle("Mumbai ahead of Washington,Toronto in super-rich population.");

        NewsModel model5=new NewsModel();
        model5.setImagepropertynews(R.drawable.news1);
        model5.setTitle("Iconic Waterford hotel Ard Rí sold");

        NewsModel model6=new NewsModel();
        model6.setImagepropertynews(R.drawable.cottage2_02);
        model6.setTitle("Turkey blocks access to Dutch-based Booking.com.");

        NewsModel model7=new NewsModel();
        model7.setImagepropertynews(R.drawable.au);
        model7.setTitle("An iconic luxury hotel experience in Kerala, India");

        listView=(ListView)findViewById(R.id.listview_propertynews);
        ObjectHolder.newsModel.add(model1);
        ObjectHolder.newsModel.add(model2);
        ObjectHolder.newsModel.add(model3);
        ObjectHolder.newsModel.add(model4);
        ObjectHolder.newsModel.add(model5);
        ObjectHolder.newsModel.add(model6);
        ObjectHolder.newsModel.add(model7);

        CustomNewsAdapter adapter = new CustomNewsAdapter(this,ObjectHolder.newsModel);
        listView.setAdapter(adapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
