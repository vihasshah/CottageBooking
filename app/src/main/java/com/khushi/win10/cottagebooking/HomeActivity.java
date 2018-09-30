package com.khushi.win10.cottagebooking;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.khushi.win10.cottagebooking.Helpers.Utils;
import com.khushi.win10.cottagebooking.Model.RentListModel;
import com.khushi.win10.cottagebooking.Webservice.APICall;
import com.khushi.win10.cottagebooking.Webservice.Callback;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private AsyncTask<Void, Void, Void> apiCall = null;

    private ProgressBar progressBar = null;
    private TextView emptyText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView=(ListView)findViewById(R.id.home_list);
        // ref of loading indecator and empty text
        progressBar = (ProgressBar) findViewById(R.id.home_progressbar);
        emptyText = (TextView) findViewById(R.id.home_emptyText);

        cottageListApi();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(apiCall != null && apiCall.getStatus() != AsyncTask.Status.RUNNING){
            apiCall.cancel(true);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            // return true;
            Intent intent=new Intent(HomeActivity.this,SearchActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manageaccount) {
            Intent intent = new Intent(HomeActivity.this, ManageAccountActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_prnews) {
            Intent intent=new Intent(HomeActivity.this,PropertynewsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent=new Intent(HomeActivity.this,SettingActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_logout) {}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cottageListApi(){
        apiCall = new APICall(HomeActivity.this, Utils.COTTAGE_LIST_URL, null, "Loading list", new Callback() {
            @Override
            public void onCallback(final String response) {

                HomeActivity.this.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);

                            if(response != null){
                                RentListModel model = new Gson().fromJson(response,RentListModel.class);
                                if(model.getSuccess() == 1){
                                    if(ObjectHolder.rentListModel.size() > 0){
                                        ObjectHolder.rentListModel.clear();
                                    }
                                    ObjectHolder.rentListModel.addAll(model.getData());

                                    emptyText.setVisibility(View.GONE);
                                    listView.setVisibility(View.VISIBLE);

                                    CustomRentAdapter adapter = new CustomRentAdapter(HomeActivity.this, ObjectHolder.rentListModel);
                                    listView.setAdapter(adapter);
                                }else{
                                    emptyText.setVisibility(View.VISIBLE);
                                    listView.setVisibility(View.GONE);
                                    emptyText.setText(model.getMessage());
                                }
                            }else{
                                Toast.makeText(HomeActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                );



            }
        }).execute();
    }
}
