package com.evgeny.lebedev.themovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonMovieCreditsList {

    @SerializedName("cast")
    private List<Movie> castList;
    @SerializedName("crew")
    private List<Movie> crewList;



    public List<Movie> getCastList() {
        return castList;
    }

    public List<Movie> getCrewList() {
        return crewList;
    }
}
