package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieImagesList {
    private int id;
    @SerializedName("backdrops")
    private List<Image> backdropsList;
    @SerializedName("posters")
    private List<Image> postersList;

    public List<Image> getPostersList() {
        return postersList;
    }

    public int getId() {
        return id;
    }

    public List<Image> getBackdropsList() {
        return backdropsList;
    }
}
