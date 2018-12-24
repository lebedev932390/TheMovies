package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfMovies {
    @SerializedName("results")
    @Expose
    private List<Movie> listOfMovies = null;
    @SerializedName("total_pages")
    private int totalPages;

    public int getTotalPages() {
        return totalPages;
    }

    public List<Movie> getListOfMovies() {
        return listOfMovies;
    }
}
