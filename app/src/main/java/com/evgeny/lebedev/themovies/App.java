package com.evgeny.lebedev.themovies;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
    private static API api;
    private Retrofit retrofit;
    public static final String apiKey = "44b20ea2486ad0800f0ea2679227206e";
    public static final String DESC = "created_at.desc";
    public static final String ASC = "created_at.asc";
    public static String sessionId;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);
    }

    public static API getApi() {
        return api;
    }

    public static String formatDate(String date){
        Calendar calendar = Calendar.getInstance();
        if (date == null){
            return "-";
        }

        try {
            Date dt = simpleDateFormat.parse(date);
            calendar.setTime(dt);
        } catch (ParseException e) {
            return "-";
        }
        return calendar.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.US) + " " + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) + ", " + Integer.toString(calendar.get(Calendar.YEAR)) ;

    }

}
