package com.khushi.win10.cottagebooking.Helpers;

import android.util.Log;

public class Utils {
    // shared preference constants
    public static final String SHARED_PREF_NAME = "CottageBooking";
    public static final String LOGIN_PREF = "login_pref";
    public static final String USER_ID_PREF = "user_id";

    // URLs
    public static final String SERVER_URL = "192.168.1.6";
    public static final String LOGIN_URL = "http://"+SERVER_URL+"/cottage/api/login.php";
    public static final String SIGNUP_URL = "http://"+SERVER_URL+"/cottage/api/signup.php";
    public static final String COTTAGE_LIST_URL ="http://"+SERVER_URL+"/cottage/api/cottage_list.php";
    public static final String NEWS_LIST_URL ="http://"+SERVER_URL+"/cottage/api/news_list.php";
    public static final String UPDATE_INFO_URL ="http://"+SERVER_URL+"/cottage/api/update_info.php";
    public static final String RESET_PASS_URL ="http://"+SERVER_URL+"/cottage/api/reset_password.php";

    // intent constants
    public static final String INTENT_POSITION = "INTENT_POSITION";
    public static final String INTENT_PAYMENT_BY = "intent_payment_by";
    public static final int BY_CREDIT_CARD = 0;
    public static final int BY_DEBIT_CARD = 1;

    // general static methods
    public static void log(String message){
        Log.d("myapp",message);
    }

    public static String[] extractImages(String csvImageList){
        String[] images = csvImageList.split(",");
        // creating full url
        for(int i = 0 ; i < images.length ; i++){
            images[i] = ("http://"+Utils.SERVER_URL+images[i]).trim();
        }
        return images;
    }
}
