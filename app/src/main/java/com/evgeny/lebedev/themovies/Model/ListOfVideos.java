package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfVideos {

    @SerializedName("results")
    @Expose
    private List<Video> listOfVideos = null;

    public List<Video> getListOfVideos() {
        return listOfVideos;
    }
}
