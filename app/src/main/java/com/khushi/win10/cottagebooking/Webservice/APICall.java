package com.khushi.win10.cottagebooking.Webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.khushi.win10.cottagebooking.Helpers.Utils;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;


import java.io.IOException;


public class APICall extends AsyncTask<Void,Void,Void>{
    // interface for response

    private Callback callback;
    private final MediaType URLENCODE = MediaType.parse("application/json;charset=utf-8");
    private ProgressDialog dialog = null;
    private Context context;
    private String dialogMessage = null;
    private String URL;
    private String jsonBody;
    private OkHttpClient client = null;

    public APICall(Context context, String URL, String jsonRequestBody, String dialogMessage,Callback callback){
        this.context = context;
        this.URL = URL;
        this.jsonBody = jsonRequestBody;
        this.dialogMessage = dialogMessage;
        this.callback = callback;
        client = new OkHttpClient();
    }

    /**
     * Web service call okhttp
     * @param params url, request body in string respectively
     * @return String response
     */
    @Override
    protected Void doInBackground(Void... params) {

        // creating okhttp client

        // client.setConnectTimeout(10L, TimeUnit.SECONDS);
        // creating request body
        RequestBody body;
        if(jsonBody != null) {
            body = RequestBody.create(URLENCODE, jsonBody);
        }else{
            body = null;
        };
        // creating request
        Request.Builder builder = new Request.Builder();
        if(jsonBody != null){
            builder.post(body);
        }
        builder.url(URL);
        Utils.log((URL));
        Request request = builder.build();

        // creating webserivce call and get response

        try {
            Response response = client.newCall(request).execute();

            ResponseBody resBody = response.body();
            if(resBody != null) {
                String res = resBody.string();
                callback.onCallback(res);
            }else{
                callback.onCallback(null);
                Utils.log(getClass().getSimpleName()+": response null");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}

