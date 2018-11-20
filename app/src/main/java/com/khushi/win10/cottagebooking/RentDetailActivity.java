package com.khushi.win10.cottagebooking;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khushi.win10.cottagebooking.Helpers.Utils;
import com.khushi.win10.cottagebooking.Model.RentListModel;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RentDetailActivity extends AppCompatActivity {
    private ImageView bigImage;
    private TextView nameTv,placeTv,categoryTv,ratingsTv,contactTv,priceTv,amenitiesTv,reviewsLabel;
    private Button btnbook;
    private String[] imageList;
    private RecyclerView horizontalList,reviewList;
    private RentListModel.DataBean bean = null;
    private String dbStartDate = null;
    private String dbEndDate = null;
    private String finalMoneyToPay = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //get clicked position
        int position = getIntent().getIntExtra(Utils.INTENT_POSITION,-1);
        // get data model from object holder
        bean = ObjectHolder.rentListModel.get(position);

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
//                Intent i=new Intent(RentDetailActivity.this,PaymentActivity.class);
//                startActivity(i);
                showBookSelections();
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

    //payment dialog
    private void showPaymentOptions(){
        View paymentDialogView = LayoutInflater.from(RentDetailActivity.this).inflate(R.layout.dialog_payment_method, null);
        final TextView creditOptTv = paymentDialogView.findViewById(R.id.dialog_payment_credit);
        final TextView debitOptTv = paymentDialogView.findViewById(R.id.dialog_payment_debit);
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(paymentDialogView);
        if (dialog.getWindow().getAttributes() != null){
            dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;
            dialog.getWindow().getAttributes().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.show();

        creditOptTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(RentDetailActivity.this,PaymentActivity.class);
                intent.putExtra(Utils.INTENT_PAYMENT_BY,Utils.BY_CREDIT_CARD);
                intent.putExtra(Utils.INTENT_COTTAGE_ID,bean.getId());
                intent.putExtra(Utils.INTENT_START_DATE,dbStartDate);
                intent.putExtra(Utils.INTENT_END_DATE,dbEndDate);
                startActivity(intent);
            }
        });

        debitOptTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(RentDetailActivity.this,PaymentActivity.class);
                intent.putExtra(Utils.INTENT_PAYMENT_BY,Utils.BY_DEBIT_CARD);
                intent.putExtra(Utils.INTENT_COTTAGE_ID,bean.getId());
                intent.putExtra(Utils.INTENT_START_DATE,dbStartDate);
                intent.putExtra(Utils.INTENT_END_DATE,dbEndDate);
                startActivity(intent);
            }
        });

    }

    // show booking pickers dialog
    private void showBookSelections(){
        View bookSelDialogView = LayoutInflater.from(RentDetailActivity.this).inflate(R.layout.dialog_booking_selection, null);
        final TextView startDateTv = bookSelDialogView.findViewById(R.id.book_sel_start_date);
        final TextView endDateTv = bookSelDialogView.findViewById(R.id.book_sel_end_date);
        final TextView finalPriceTv = bookSelDialogView.findViewById(R.id.book_sel_price);
        final TextView payBtn = bookSelDialogView.findViewById(R.id.book_sel_btn_pay_now);

        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(bookSelDialogView);
        if (dialog.getWindow().getAttributes() != null){
            dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;
            dialog.getWindow().getAttributes().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.show();

        startDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(finalPriceTv,startDateTv,true);
            }
        });

        endDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(finalPriceTv,endDateTv,false);
            }
        });

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showPaymentOptions();
            }
        });
    }

    // startDate Variable to set db date according to variable
    private void showDatePicker(final TextView priceTv,final TextView textView, final boolean startDate){
        Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String displayDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        if(startDate) {
                            dbStartDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        }else{
                            dbEndDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        }

                        //calculate days of stay and show final price
                        if(dbStartDate != null && dbEndDate != null){
                            try {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Date startDate = sdf.parse(dbStartDate);
                                Date endDate = sdf.parse(dbEndDate);
                                int days = Utils.getDaysDifference(startDate,endDate);
                                // handle zero value
                                if(days == 0){
                                    days = 1;
                                }
                                String updatedPrice = String.valueOf(Integer.valueOf(bean.getPrice()) * days);

                                Utils.log(String.valueOf(days));
                                priceTv.setText(updatedPrice);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                        textView.setText(displayDate);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
