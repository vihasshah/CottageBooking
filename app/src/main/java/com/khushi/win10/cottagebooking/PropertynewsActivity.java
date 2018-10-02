package com.khushi.win10.cottagebooking;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.khushi.win10.cottagebooking.Helpers.Utils;
import com.khushi.win10.cottagebooking.Model.NewsModel;
import com.khushi.win10.cottagebooking.Webservice.APICall;
import com.khushi.win10.cottagebooking.Webservice.Callback;

public class PropertynewsActivity extends AppCompatActivity {

    private ListView listView;
    private ProgressBar progressBar;
    private TextView emptyText;
    private AsyncTask<Void, Void, Void> apiCall = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertynews);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        listView=(ListView)findViewById(R.id.listview_propertynews);
        progressBar = findViewById(R.id.news_list_progress_bar);
        emptyText = findViewById(R.id.news_list_empty_text);


        callNewsListApi();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void callNewsListApi(){
        apiCall = new APICall(this, Utils.NEWS_LIST_URL, null, null, new Callback() {
            @Override
            public void onCallback(final String response) {
                PropertynewsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        if(response != null){
                            NewsModel model = new Gson().fromJson(response,NewsModel.class);
                            if(model.getSuccess() == 1){
                                if(model.getData() != null && model.getData().size() > 0){
                                    emptyText.setVisibility(View.GONE);
                                    listView.setVisibility(View.VISIBLE);

                                    if(ObjectHolder.newsModel.size() > 0){
                                        ObjectHolder.newsModel.clear();
                                    }
                                    ObjectHolder.newsModel.addAll(model.getData());
                                    CustomNewsAdapter adapter = new CustomNewsAdapter(PropertynewsActivity.this,ObjectHolder.newsModel);
                                    listView.setAdapter(adapter);

                                }else{
                                    emptyText.setVisibility(View.VISIBLE);
                                    emptyText.setText("No News Found");
                                }
                            }
                        }else{
                            emptyText.setText(View.VISIBLE);
                            emptyText.setText("Something goes wrong");
                        }
                    }
                });
            }
        }).execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(apiCall != null && apiCall.getStatus() == AsyncTask.Status.RUNNING){
            apiCall.cancel(true);
        }
    }
}
