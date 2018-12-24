package com.evgeny.lebedev.themovies.Model;

import com.evgeny.lebedev.themovies.Model.Image;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LisfOfImages {
    private int id;
    @SerializedName("backdrops")
    private List<Image> listOfBackdrops;
    @SerializedName("posters")
    private List<Image> listOfPosters;

    public List<Image> getListOfPosters() {
        return listOfPosters;
    }

    public int getId() {
        return id;
    }

    public List<Image> getListOfBackdrops() {
        return listOfBackdrops;
    }
}
