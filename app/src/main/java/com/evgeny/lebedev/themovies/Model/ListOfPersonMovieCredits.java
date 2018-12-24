package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfPersonMovieCredits {

    @SerializedName("cast")
    private List<Movie> listOfCast;
    @SerializedName("crew")
    private List<Movie> listOfCrew;



    public List<Movie> getListOfCast() {
        return listOfCast;
    }

    public List<Movie> getListOfCrew() {
        return listOfCrew;
    }
}
