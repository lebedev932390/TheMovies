package com.evgeny.lebedev.themovies.Model;

import com.evgeny.lebedev.themovies.App;
import com.google.gson.annotations.SerializedName;

public class Person {
    private final String imageBaseUrlLarge = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    private int id;
    private String biography;
    private String birthday;
    private String deathday;
    private String name;
    @SerializedName("known_for_department")
    private String knownForDepartment;
    @SerializedName("place_of_birth")
    private String placeOfBirth;
    @SerializedName("profile_path")
    private String profilePath;

    public String getBiography() {
        return biography;
    }

    public int getId() {
        return id;
    }

    public String getBirthday() {
        return "Birthday: " + App.formatDate(birthday);
    }

    public String getDeathday() {
        if (deathday==null){
            return "";
        }
        return App.formatDate(deathday);
    }

    public String getName() {
        return name;
    }

    public String getKnownForDepartment() {
        return "Known For " + knownForDepartment;
    }

    public String getPlaceOfBirth() {
        return "Place of birth: " + placeOfBirth;
    }

    public String getProfilePathLarge() {
        return imageBaseUrlLarge + profilePath;
    }


}
