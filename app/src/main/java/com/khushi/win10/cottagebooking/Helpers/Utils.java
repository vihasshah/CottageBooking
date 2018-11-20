package com.khushi.win10.cottagebooking.Helpers;

import android.util.Log;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    public static final String BOOK_URL ="http://"+SERVER_URL+"/cottage/api/book_cottage.php";

    // intent constants
    public static final String INTENT_POSITION = "INTENT_POSITION";
    public static final String INTENT_PAYMENT_BY = "intent_payment_by";
    public static final String INTENT_COTTAGE_ID = "INTENT_COTTAGE_ID";
    public static final String INTENT_START_DATE = "INTENT_START_DATE";
    public static final String INTENT_END_DATE = "INTENT_END_DATE";
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



    public static int getDaysDifference(Date fromDate,Date toDate)
    {
        if(fromDate==null||toDate==null)
            return 0;

        return (int)( (toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
    }


}
