package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khushi.win10.cottagebooking.Helpers.Utils;
import com.khushi.win10.cottagebooking.Model.RentListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RentDetailActivity extends AppCompatActivity {
    private ImageView bigImage;
    private TextView nameTv,placeTv,categoryTv,ratingsTv,contactTv,priceTv,amenitiesTv,reviewsLabel;
    private Button btnbook;
    private String[] imageList;
    private RecyclerView horizontalList,reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //get clicked position
        int position = getIntent().getIntExtra(Utils.INTENT_POSITION,-1);
        // get data model from object holder
        RentListModel.DataBean bean = ObjectHolder.rentListModel.get(position);

        getSupportActionBar().setTitle(bean.getName());

        initReferences();

        assignData(bean);

        /*
             horizontal list
        */
        if(bean.getImages().length() > 0) {
            String[] pathList = Utils.extractImages(bean.getImages());
            // set default big image to zero
            Picasso.get().load(pathList[0]).into(bigImage);
            HorizontalImageListAdapter adapter = new HorizontalImageListAdapter(pathList, new HorizontalImageListAdapter.Callback() {
                @Override
                public void selectedItem(String path) {
                    Picasso.get().load(path).into(bigImage);
                }
            });
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalList.setLayoutManager(manager);
            horizontalList.setNestedScrollingEnabled(false);
            horizontalList.setAdapter(adapter);
        }else{
            horizontalList.setVisibility(View.GONE);
        }
        /*
            review scroll
         */
        if(bean.getReviews().size() > 0){
            List<RentListModel.DataBean.ReviewsBean> beanList = bean.getReviews();
            ReviewListAdapter adapter = new ReviewListAdapter(beanList);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            reviewList.addItemDecoration(new DividerItemDecoration(this,
                    DividerItemDecoration.VERTICAL));

            reviewList.setLayoutManager(manager);
            reviewList.setAdapter(adapter);

        }else{
            reviewList.setVisibility(View.GONE);
            reviewsLabel.setText("No Reviews");
        }


        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RentDetailActivity.this,PaymentActivity.class);
                startActivity(i);
            }
        });
    }

    private void assignData(RentListModel.DataBean bean) {
        nameTv.setText(bean.getName());
        placeTv.setText(bean.getPlace());
        categoryTv.setText(bean.getCategory());
        String ratingsStr= bean.getRatings()+"/5";
        ratingsTv.setText(ratingsStr);
        priceTv.setText(bean.getPrice());
        contactTv.setText(bean.getContact());
        if(bean.getAvailable().equals("1")){
            btnbook.setText("Book");
            btnbook.setEnabled(true);
        }else{
            btnbook.setText("Booked");
            btnbook.getBackground().setColorFilter(Color.parseColor("#c0c0c0"), PorterDuff.Mode.SRC_IN);
            btnbook.setEnabled(false);
        }
    }

    private void initReferences() {
        bigImage = findViewById(R.id.detail_big_image);
        nameTv = findViewById(R.id.detail_name);
        placeTv = findViewById(R.id.detail_place);
        categoryTv = findViewById(R.id.detail_category);
        ratingsTv = findViewById(R.id.detail_rating);
        contactTv = findViewById(R.id.detail_contact);
        priceTv = findViewById(R.id.detail_price);
        amenitiesTv = findViewById(R.id.detail_amenities);
        horizontalList = findViewById(R.id.rent_detail_horizontal_recyclerview);
        reviewList = findViewById(R.id.detail_reviews_list);
        btnbook = findViewById(R.id.detail_book_btn);
        reviewsLabel = findViewById(R.id.detail_reviews_label);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
