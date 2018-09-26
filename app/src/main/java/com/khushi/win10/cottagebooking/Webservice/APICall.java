package com.khushi.win10.cottagebooking.Webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.khushi.win10.cottagebooking.Helpers.Utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APICall extends AsyncTask<Void,Void,String>{
    // interface for response

    private Callback callback;
    private final MediaType URLENCODE = MediaType.parse("application/json;charset=utf-8");
    private ProgressDialog dialog;
    private Context context;
    private String dialogMessage;
    private String URL;
    private String jsonBody;

    public APICall(Context context, String URL, String jsonRequestBody, String dialogMessage,Callback callback){
        this.context = context;
        this.URL = URL;
        this.jsonBody = jsonRequestBody;
        this.dialogMessage = dialogMessage;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(dialogMessage != null) {
            dialog = new ProgressDialog(context);
            dialog.setMessage(dialogMessage);
            dialog.show();
        }
    }

    /**
     * Web service call okhttp
     * @param params url, request body in string respectively
     * @return String response
     */
    @Override
    protected String doInBackground(Void... params) {

        // creating okhttp client
        OkHttpClient client = new OkHttpClient();
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
        Request request = builder.build();

        // creating webserivce call and get response

        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            Utils.log(res);
            return res;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(dialogMessage != null){
            if(dialog.isShowing()){
                dialog.dismiss();
            }
        }
        if(s != null){
            callback.onCallback(s);
        }else{
            callback.onCallback(null);
            Utils.log(getClass().getSimpleName()+": response null");
        }
    }

}

