package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

public class Crew {
    private final String imageBaseUrlSmall = "https://image.tmdb.org/t/p/w500";
    private final String imageBaseUrlLarge = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    private int id;
    private String job;
    private String name;
    @SerializedName("profile_path")
    private String profilePath;

    public int getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public String getProfilePathSmall() {
        return imageBaseUrlSmall + profilePath;
    }

    public String getProfilePathLarge() {
        return imageBaseUrlLarge + profilePath;
    }
}
