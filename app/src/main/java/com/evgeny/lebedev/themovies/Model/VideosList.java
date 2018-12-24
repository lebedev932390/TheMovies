package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosList {

    @SerializedName("results")
    @Expose
    private List<Video> VideosList = null;

    public List<Video> getVideosList() {
        return VideosList;
    }
}
