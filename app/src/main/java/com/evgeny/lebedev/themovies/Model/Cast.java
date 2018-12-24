package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

public class Cast {
    private final String imageBaseUrlSmall = "https://image.tmdb.org/t/p/w500";
    private final String imageBaseUrlLarge = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    private String character;
    private int id;
    private String name;

    @SerializedName("profile_path")
    private String profilePath;

    public String getCharacter() {
        return character;
    }

    public int getId() {
        return id;
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
