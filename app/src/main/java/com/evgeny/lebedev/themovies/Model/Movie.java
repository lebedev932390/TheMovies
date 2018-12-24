package com.evgeny.lebedev.themovies.Model;


import com.evgeny.lebedev.themovies.App;
import com.google.gson.annotations.SerializedName;

public class Movie {
    private final String imageBaseUrlSmall = "https://image.tmdb.org/t/p/w500";
    private final String imageBaseUrlLarge = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    private int id;

    private String title;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("release_date")
    private String releaseDate;

    public String getReleaseDate() {
        return App.formatDate(releaseDate);
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getPosterSmall() {
        return imageBaseUrlSmall + poster;
    }
    public String getPosterLarge() {
        return imageBaseUrlSmall + poster;
    }




}
