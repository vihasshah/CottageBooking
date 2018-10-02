package com.khushi.win10.cottagebooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.khushi.win10.cottagebooking.Helpers.Utils;
import com.khushi.win10.cottagebooking.Model.NewsModel;
import com.squareup.picasso.Picasso;

public class PropertyNewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_news_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ImageView imageView = findViewById(R.id.news_detail_image);
        TextView titleTv = findViewById(R.id.news_detail_title);
        TextView contentTv = findViewById(R.id.news_detail_content);

        int position = getIntent().getIntExtra(Utils.INTENT_POSITION,-1);
        if(position > -1){
            NewsModel.DataBean bean = ObjectHolder.newsModel.get(position);
            String imgUrl;
            if(bean.getImage().contains("http:")){
                imgUrl = bean.getImage();
            }else {
                imgUrl = "http://" + Utils.SERVER_URL + bean.getImage();
                Utils.log(imgUrl.trim());
            }
            Picasso.get().load(imgUrl.trim()).into(imageView);
            titleTv.setText(bean.getTitle());
            contentTv.setText(bean.getDetails());
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
